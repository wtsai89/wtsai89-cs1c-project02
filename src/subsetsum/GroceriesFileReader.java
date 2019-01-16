package subsetsum;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * Reads a csv file containing grocery prices and stores it in an ArrayList
 */
public class GroceriesFileReader {
    private ArrayList<Double> groceryList;

    /**
     * a simple constructor
     */
    public GroceriesFileReader()
    {
        groceryList = new ArrayList<Double>();
    }

    /**
     *
     * @param filepath  The text file to read
     * @return  the list of prices in ArrayList format
     */
    public ArrayList<Double> readFile(String filepath)
    {
        try {
            File file = new File(filepath);
            Scanner input = new Scanner(file);

            while(input.hasNextLine())
            {
                String[] line = ((input.nextLine()).split(","));    //split line into item and price
                groceryList.add(Double.parseDouble(line[1]));
            }
            return groceryList;
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File not found");
            return null;
        }
    }
}
