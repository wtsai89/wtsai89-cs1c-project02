package subsetsum;

import java.util.ArrayList;

/**
 * The data structure for SubsetSum.findSubset
 */
class Sublist implements Cloneable
{
    private double sum = 0;
    private ArrayList<Double> originalObjects;
    private ArrayList<Double> subs;

    /**
     * constructor creates an empty Sublist (no subs)
     * @param orig  the master set
     */
    public Sublist(ArrayList<Double> orig)
    {
        sum = 0;
        originalObjects = orig;
        subs = new ArrayList<Double>();
    }

    /**
     * Fetches the sum
     * @return sum
     */
    double getSum()
    { return sum; }

    /**
     * Sets the sum
     * @param newSum
     */
    void setSum(double newSum)
    {
        sum = newSum;
    }

    // I have done the clone() for you, since you will need clone() inside addItem().
    /**
     *
     * @return deep copy of Sublist subsets
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException
    {
        // shallow copy
        Sublist newObject = (Sublist)super.clone();
        // deep copy
        newObject.subs = (ArrayList<Double>)subs.clone();

        return newObject;
    }

    /**
     * L + x operation
     * @param itemToAdd x item to be added to L
     * @return L + x
     */
    Sublist addItem( double itemToAdd )
    {
        try {
            Sublist newSub = (Sublist)this.clone();
            newSub.subs.add(itemToAdd);
            newSub.setSum(this.sum + itemToAdd);
            return newSub;
        }
        catch (CloneNotSupportedException ex)
        {
            System.out.println("Clone not supported");
            return null;
        }
    }

    /**
     * prints out the subset
     */
    void showSublist()
    {
        System.out.println(subs);
    }

    /**
     * fetches the subset
     * @return subset
     */
    ArrayList<Double> getSubset()
    {
        return subs;
    }
};
