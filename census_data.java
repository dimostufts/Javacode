/*
 * CensusCalculatorExtended.java
 *
 * This program performs computations on census data stored in a text file.
 * It uses arrays in several ways, including for storage of the results of
 * the computations.
 * 
 * modified by: Dimos Anagnostopoulos
 *        date: 12/July/2014
 */

import java.util.*;
import java.io.*;

public class CensusCalculatorExtended {
    /* 
     * A class-constant array of Strings containing the names of the states
     * in the data file.
     * 
     * The index of a given state name in the array is the
     * same as the numeric code of the state in the data file.
     * For example, Alaska has a state code of 1 in the data file, 
     * so its name is at position 1 in this array.
     */
    public static final String[] STATE_NAMES = {"Alabama", "Alaska",
        "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
        "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois",
        "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine",
        "Maryland", "Massachusetts", "Michigan", "Minnesota",
        "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada",
        "New Hampshire", "New Jersey", "New Mexico", "New York",
        "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", 
        "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
        "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
        "West Virginia", "Wisconsin", "Wyoming", "District of Columbia"};
    
    /* 
     * A class-constant array of Strings containing the names of 
     * the nine divisions used by the Census Bureau.
     * 
     * The Census Bureau also numbers the divisions, and we have
     * used a division's number as its index in this array.
     * For example, New England is Division 1, so its name is at
     * position 1 in this array.
     * 
     * Note that there is no division 0, so we have put the name
     * "none" in position 0 of the array.
     */
    public static final String[] DIVISION_NAMES = {
        "none", "New England", "Mid-Atlantic", "East North Central", "West North Central", 
        "South Atlantic", "East South Central", "West South Central", 
        "Mountain", "Pacific"};  
    
    
    /*
     * A class-constant array of integers that allows you to
     * determine the number of the division to which each
     * state belongs.
     * 
     */
    public static final int[] DIVISION_FOR_STATE = {6, 9,
        8, 7, 9, 8, 1, 5, 5, 5, 9, 8, 3, 3, 4, 4, 6, 7, 1, 5, 
        1, 3, 4, 6, 4, 8, 4, 8, 1, 2, 8, 2, 5, 4, 3, 7, 9, 2,
        1, 5, 4, 6, 7, 8, 1, 5, 9, 5, 3, 8, 5};
    
    /*
     * getStateCode - finds and returns the state code for
     * the specified state name, and -1 if the specified
     * state name is not found.
     * 
     */
    
    public static int getStateCode(String stateName) {
        for (int i = 0; i<STATE_NAMES.length; i++) {
            if (STATE_NAMES[i].equals(stateName)) {
                return i;
            }
        }
        return -1;
    }
    
    /**** PUT THE ADDITIONAL METHODS THAT YOU WRITE BELOW. ****/
    
    
    // method that tells you if user has entered valid division
    public static boolean validDivision(String divisionName) {
        for (int i=1; i<DIVISION_NAMES.length; i++) {
            if (DIVISION_NAMES[i].equals(divisionName)) {
                return true;
            }
        }
        return false;
    }
    
    // boolean method that tells you if user has entered valid state
    public static boolean validState(String stateName) {
        for (int i=0; i<STATE_NAMES.length; i++) {
            if (STATE_NAMES[i].equals(stateName)) {
                return true;
            }
        }
        return false;
    }
    
    // find out in which Division the given State belongs
    public static String getDivision(String stateName) {
        int division = DIVISION_FOR_STATE[getStateCode(stateName)];
        String nameDivision = DIVISION_NAMES[division];
        return nameDivision;
    }
    
    // method that prints population totals
    public static void print(int[] firstInt, int[] count, String[] lengthh) {
        for (int i=0; i<lengthh.length; i++) {
            System.out.printf(firstInt[i]+": "+"%,d",count[i]);
            System.out.println();
        }
    }
    
    // method that finds division code
    public static int getDivisionCode(String division) {
        for (int i=0; i<DIVISION_NAMES.length; i++) {
            if (DIVISION_NAMES[i].equals(division)) {
                return i;
            }
        }
        return -1;
    }
    
    // method that calculates the population for each year for the state given
    public static void calculator(String[] fieldFirstStr, Scanner input, int[] count, int getStateCode){
        while (input.hasNextLine()) { // scans each line of the file
            String[] fieldRestStr = new String[fieldFirstStr.length+3];
            int[] fieldRestInt = new int[fieldFirstStr.length+3];
            String record = input.nextLine(); // creates a string with the contents of each line/row
            fieldRestStr = record.split(","); // splits the row into fields
            int stateCode = Integer.parseInt(fieldRestStr[2]); // converts fields with strings to fields with integers
            for (int j = 3; j<fieldRestStr.length; j++) {
                fieldRestInt[j]= Integer.parseInt(fieldRestStr[j]);
            }
            if (stateCode==getStateCode){
                System.out.println(fieldRestStr[0]);
                for (int k=0; k<fieldRestStr.length-3; k++){ //adds up population counts for each year on the count of the previous line
                    count[k] = count[k] +fieldRestInt[k+3]; // from previous iteration of while loop
                }
            }
        }
    }
    
    // method that prints the population totals for state given
    public static void print(String[] fieldFirstStr, int[] count) {
        System.out.println();
        System.out.println("Population totals: ");
        for (int i=0; i<count.length; i++) {
            System.out.printf(fieldFirstStr[i]+": "+"%,d",count[i]);
            System.out.println();
        }
    }
    
    
    // method that calculates population total for given division
    public static void calculatorDiv(String[] fieldFirstStr, Scanner input, int[]count, int getDivisionCode) {
        while (input.hasNextLine()) { // scans each line of the file
            String[] fieldRestStr = new String[fieldFirstStr.length+3];
            int[] fieldRestInt = new int[fieldFirstStr.length+3];
            String record = input.nextLine(); // creates a string with the contents of each line/row
            fieldRestStr = record.split(","); // splits the row into fields
            int stateCode = Integer.parseInt(fieldRestStr[2]); // converts fields with strings to fields with integers
            for (int j = 3; j<fieldRestStr.length; j++) {
                fieldRestInt[j]= Integer.parseInt(fieldRestStr[j]);
            }
            if (getDivisionCode==DIVISION_FOR_STATE[stateCode]) {
                for (int k=0; k<fieldRestStr.length-3; k++){ //adds up population counts for each year on the count of the previous line
                    count[k] = count[k] +fieldRestInt[k+3]; // from previous iteration of while loop
                }
            }
        }
    }
    
    
    // main method of program 
    public static void main(String[] args) 
        throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the Census Calculator!");
        System.out.print("NAME OF DATA FILE: ");
        String fileName = console.nextLine(); // asks user to enter file name
        System.out.println();
        System.out.println("STATE OR DIVISION NAME (or q to quit): ");
        String stateName = console.nextLine(); // asks user to enter which state or division they want information for
        System.out.println();
        while (!(stateName.equals("q"))) {
            Scanner input = new Scanner(new File(fileName)); //imports the file with given file name
            String years = input.nextLine();  // scans first line of the file
            String[] fieldFirstStr = years.split(",");  //splits string of first line into fields
            int[] count = new int[fieldFirstStr.length];
            String[] fieldRestStr = new String[fieldFirstStr.length+3];
            int[] fieldRestInt = new int[fieldFirstStr.length+3];
            if (validState(stateName)) {
                int code = getStateCode(stateName);
                System.out.println(stateName+" is in the "+getDivision(stateName)+" division.");
                System.out.println();
                System.out.println("Counties:");
                calculator(fieldFirstStr,input, count, code);
                print(fieldFirstStr,count);
            } else if (validDivision(stateName)) {
                int divisionCode = getDivisionCode(stateName);
                calculatorDiv(fieldFirstStr,input,count,divisionCode);
                print(fieldFirstStr,count);
                
            } else {
                System.out.println(stateName+" is not a valid state name.\n");
            }
            System.out.println("STATE OR DIVISION NAME (or q to quit): ");
            stateName = console.nextLine();
            System.out.println();
        }
        System.out.println();
    }
    
    
}

