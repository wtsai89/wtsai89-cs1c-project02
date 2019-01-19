package subsetsum;

import java.util.ArrayList;
import cs1c.SongEntry;

/**
 * Subset algorithms
 */
public class SubsetSum {
    public static final double CRASHPREVENTION = 100000;

    /**
     * Takes a list of items and calculates a sublist that is as close to the limit as possible
     * @param list     list of items to process
     * @param limit    upper limit
     * @return
     */
    static ArrayList<Double> findSubset(ArrayList<Double> list, double limit)
    {
        if(limit > getTotalSum(list))                                           //a special test to quickly dispose of the case in which the target is larger than the sum of all elements in the master set
        {
            System.out.println("The limit is greater than all items combined. Returning all items...");
            return list;
        }
        ArrayList<Sublist> col = new ArrayList<Sublist>();                      //Initialize the collection Col
        col.add(new Sublist(list));                                             //with one sub-list: the empty sub-list.
        Sublist largest = new Sublist(list);
        for(int i = 0; i < list.size(); i++)                                    //Loop over all elements x in S
        {
            int innerSize = col.size();         //prevent infinite looping
            for(int j = 0; j < innerSize; j++)                                 //Loop over all subsets, L, that are already members of Col
            {
                Sublist sub = (col.get(j)).addItem(list.get(i));
                if(sub.getSum() == limit)                                       //if sum(L) + x == t, break from both loops!
                    return sub.getSubset();
                else if(sub.getSum() < limit)                                   //if sum(L) + x ≤ t, add the sub-list L + x to Col
                {
                    col.add(sub);
                    if(sub.getSum() > largest.getSum())                         //Of all the subsets that end up in Col, find the sub-list L' with the largest sum()
                        largest = sub;
                }
            }
        }
        return largest.getSubset();
    }

    /**
     * Takes a list of songs and calculates a playlist that is as close to the duration as possible
     * @param songList
     * @param duration
     * @return
     */
    static ArrayList<SongEntry> findSubsetOfSongs(ArrayList<SongEntry> songList, double duration)
    {
        if(duration > getTotalDuration(songList))                               //a special test to quickly dispose of the case in which the target is larger than the sum of all elements in the master set
        {
            System.out.println("The duration is greater than all items combined. Returning all items...");
            int z = 1;
            return songList;
        }

        ArrayList<SongSublist> col = new ArrayList<SongSublist>();                      //Initialize the collection Col
        col.add(new SongSublist(songList));                                             //with one sub-list: the empty sub-list.
        SongSublist largest = new SongSublist(songList);
        // double longestDurationSong = findLongestDuration(songList);
        double averageSongDuration = findAverageDuration(songList);
        for(int i = 0; i < songList.size(); i++)                                    //Loop over all elements x in S
        {
            int innerSize = col.size();         //prevent infinite looping
            for(int j = 0; j < innerSize; j++)                                 //Loop over all subsets, L, that are already members of Col
            {
                if((largest.getDuration() - (col.get(j)).getDuration()) > averageSongDuration)       //discard unnecessary subsets that are smaller than the current largest viable subset
                {
                    col.remove(j);
                    j--;
                    innerSize--;
                }
                else if((duration - (col.get(j)).getDuration()) > CRASHPREVENTION)          //If leftover duration is still massive just keep adding songs and don't bother with keeping old subsets
                {
                    col.set(j, (col.get(j)).addItem(songList.get(i)));
                }
                else{
                    SongSublist sub = (col.get(j)).addItem(songList.get(i));
                    if (sub.getDuration() == duration)                                       //if sum(L) + x == t, break from both loops!
                    {
                        System.out.println("Actual Playlist duration is " + largest.getDuration());
                        return sub.getSubset();
                    }
                    else if (sub.getDuration() < duration)                                   //if sum(L) + x ≤ t, add the sub-list L + x to Col
                    {
                        col.add(sub);
                        if (sub.getDuration() > largest.getDuration())                         //Of all the subsets that end up in Col, find the sub-list L' with the largest sum()
                            largest = sub;
                    }
                }
            }
        }
        System.out.println("Actual playlist duration is " + largest.getDuration());
        return largest.getSubset();
    }

    /**
     * gets the total sum of the entire list of items
     * @param list
     * @return
     */
    static double getTotalSum(ArrayList<Double> list)
    {
        double sum = 0;
        for(int i = 0; i < list.size(); i++)
            sum += list.get(i);

        return sum;
    }

    /**
     * gets the total duration of the entire list of songs
     * @param list
     * @return
     */
    static double getTotalDuration(ArrayList<SongEntry> list)
    {
        double sum = 0;
        for(int i = 0; i < list.size(); i++)
            sum += (list.get(i)).getDuration();

        //System.out.println("Total duration of all songs combined is " + sum);
        return sum;
    }

    /**
     * finds the longest duration song in the songlist
     * @param list
     * @return
     */
    static double findLongestDuration(ArrayList<SongEntry> list)
    {
        double longest = 0;
        for(int i = 0; i < list.size(); i++)
        {
            double duration = (list.get(i)).getDuration();
            if(duration > longest)
                longest = duration;
        }

        //System.out.println("The song with the longest duration is " + longest);
        return longest;
    }

    /**
     * finds the average duration of songs in the songlist
     * @param list
     * @return
     */
    static double findAverageDuration(ArrayList<SongEntry> list)
    {
        double average = getTotalDuration(list) / list.size();

        //System.out.println("The average song duration is " + average);
        return average;
    }
}
