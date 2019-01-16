package subsetsum;

import cs1c.SongEntry;

import java.util.ArrayList;

/**
 * The data structure for SubsetSum.findSubsetOfSongs
 */
public class SongSublist implements Cloneable 
{
    private double duration = 0;
    private ArrayList<SongEntry> originalObjects;
    private ArrayList<SongEntry> subs;

    /**
     * constructor creates an empty SongSublist (no subs)
     * @param orig  the master set
     */
    public SongSublist(ArrayList<SongEntry> orig)
    {
        duration = 0;
        originalObjects = orig;
        subs = new ArrayList<SongEntry>();
    }

    double getDuration()
    { return duration; }

    void setDuration(double newduration)
    {
        duration = newduration;
    }

    // I have done the clone() for you, since you will need clone() inside addItem().

    /**
     *
     * @return deep copy of SongSublist subsets
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException
    {
        // shallow copy
        SongSublist newObject = (SongSublist)super.clone();
        // deep copy
        newObject.subs = (ArrayList<SongEntry>)subs.clone();

        return newObject;
    }

    /**
     * L + x operation
     * @param itemToAdd x item to be added to L
     * @return L + x
     */
    SongSublist addItem( SongEntry itemToAdd )
    {
        try {
            SongSublist newSub = (SongSublist)this.clone();
            newSub.subs.add(itemToAdd);
            newSub.setDuration(this.duration + itemToAdd.getDuration());
            return newSub;
        }
        catch (CloneNotSupportedException ex)
        {
            System.out.println("Clone not supported");
            return null;
        }
    }

    /**
     * Prints out the subset
     */
    void showSublist()
    {
        System.out.println(subs);
    }

    /**
     * fetches the subset
     * @return subset
     */
    ArrayList<SongEntry> getSubset()
    {
        return subs;
    }
}
