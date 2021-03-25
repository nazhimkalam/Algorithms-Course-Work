/*
   Name: Nazhim Kalam
   Student ID:2019281
   UoW: w1761265
   Algorithms - Coursework 01
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// This class is used to read data from a txt file and return the result
public class ReadDataFile {

    public static ArrayList<String> readingData(String filePath){
        // Stores collection of lines from the input file
        ArrayList<String> inputData = new ArrayList<>();

        /* This block of code is used to read the data from the input file
           Also add every sentence one by one into the collection (inputData) */
        System.out.println(" Reading data from file...");
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                inputData.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(" An error occurred.");
            System.out.println(" File not found!");
        }

        return inputData;
    }

}
