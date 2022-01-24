import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileNotFoundException;

// HOW TO CLEAR THE SCREEN:
// System.out.print("\033[H\033[2J");
// System.out.flush();

public class guitarPractice {

        // Number of strings and upper and lower fret numbers
        public int numOfStrings, lowerLimit, upperLimit;
        public long sleepyTime;
        public String[][] noteNames;
        public int stringNum;

        // Establish clear code
        public String clear = "\033[H\033[2J";

        // Array to store string names in
        public char[] stringNames;

        // Constructor method
        guitarPractice() {
                numOfStrings = 0;
                lowerLimit = 0;
                upperLimit = 0;
                sleepyTime = 0;
                stringNum = 0;

                noteNames = new String[6][40];

                stringNames = new char[6];
                for (int i = 0; i < 6; i++) {
                        stringNames[i] = 'z';
                }
        }

        public void readNotes() {

                try {

                        // Declare Scanner
                        String fileName = "/Users/sierrabasic/Desktop/Coding/Guitar Notes Generator/Guitar_Notes.txt";

                        File textFile = new File(fileName);
                        Scanner fileReader = new Scanner(textFile);

                        // Use character array 'noteNames' to store note letters
                        for (int i = 0; i < 6; i++) {
                                for (int j = 0; j < 20; j++) {

                                        noteNames[i][j] = fileReader.next();

                                }
                        }

                        // DEBUG: DISPLAYS THE NOTES READ IN TO ARRAY
                        // for (int i = 0; i < 6; i++) {
                        // for (int j = 0; j < 20; j++) {
                        // System.out.print(noteNames[i][j] + " ");
                        // }
                        // System.out.println();
                        // }

                        fileReader.close();

                } catch (FileNotFoundException e) {
                        System.out.println("ERROR - File not found!");
                }

        }

        // Have user input how many strings to use and what string names
        public void setStrings(Scanner in) {
                // Prompt user to input the number of strings to use
                System.out.print("Enter the number of strings to use: ");
                numOfStrings = Integer.parseInt(in.nextLine());

                for (int i = 0; i < numOfStrings; i++) {

                        // Prompt user to enter the names of each string
                        System.out.print("Enter the name of string " + i + ": ");
                        String name = in.nextLine();

                        // Make each letter uppercase
                        if (name.charAt(0) != 'e') {
                                name = name.toUpperCase();
                        }

                        // Put the first character entered into a character array of the string names
                        stringNames[i] = name.charAt(0);
                }

                // Clear screen
                System.out.print(clear);
                System.out.flush();
        }

        public void setFrets(Scanner in) {

                // Have user enter lower and upper fret limits
                System.out.print("Enter the lowest fret: ");
                lowerLimit = Integer.parseInt(in.nextLine());

                System.out.print("Enter the highest fret: ");
                upperLimit = Integer.parseInt(in.nextLine());

                // Clear screen
                System.out.print(clear);
                System.out.flush();
        }

        public void setSleep(Scanner in) {
                // Prompt user to enter amount of buffer
                System.out.print("Enter the buffer time: ");

                double sleeepyTimeDouble = Double.parseDouble(in.nextLine());
                sleepyTime = (long) (sleeepyTimeDouble * 1000);

                // Clear screen
                System.out.print(clear);
                System.out.flush();

        }

        public void pickNotes() {

                // Set random seed
                Random rand = new Random(System.currentTimeMillis());

                for (int i = 0; i < 10; i++) {

                        // Pick random fret number and random string
                        int currentFret = rand.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;

                        int randomStringNum = rand.nextInt(numOfStrings);
                        char randomNote = stringNames[randomStringNum];

                        System.out.format("%c %d\n", randomNote, currentFret);

                        // Determine the string number
                        switch (stringNames[randomStringNum]) {
                                case 'e':
                                        stringNum = 1;
                                        break;
                                case 'B':
                                        stringNum = 2;
                                        break;
                                case 'G':
                                        stringNum = 3;
                                        break;
                                case 'D':
                                        stringNum = 4;
                                        break;
                                case 'A':
                                        stringNum = 5;
                                        break;
                                case 'E':
                                        stringNum = 6;
                                        break;
                        }

                        // Sleep for desired amount of time
                        try {
                                Thread.sleep(sleepyTime);

                                // Display the actual note
                                System.out.println("\n" + noteNames[stringNum - 1][currentFret]);

                                // Keep the position and actual note on the screen for 1.5 seconds
                                Thread.sleep(1500);

                        } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                        }

                        // Clear screen
                        System.out.print(clear);
                        System.out.flush();

                }

        }

        public static void main(String[] args) {

                Scanner keyboard = new Scanner(System.in);

                // Declare class object
                guitarPractice guitar = new guitarPractice();

                // Clear screen
                System.out.print(guitar.clear);
                System.out.flush();

                // Call function to read in the note names from text file
                guitar.readNotes();

                // Call function to prompt user to enter string number/names
                guitar.setStrings(keyboard);

                // Call function to prompt user to enter upper and lower frets
                guitar.setFrets(keyboard);

                // Call function to prompt user to enter the buffer time
                guitar.setSleep(keyboard);

                // Call function to pick the notes to play
                guitar.pickNotes();

                // Determine what the user wants to do after picking 10 notes
                boolean rerun = true;

                while (rerun == true) {
                        // Have user decide if they want to continue, and if so how
                        System.out.println("Do you want to continue?\n");
                        System.out.println("Continue with same data(1)\nEnter new strings/frets(2)");
                        System.out.print("Change buffer time(3)\nQuit(4)\n\nEnter Choice: ");
                        int userChoice = Integer.parseInt(keyboard.nextLine());

                        // Clear screen
                        System.out.print(guitar.clear);
                        System.out.flush();

                        switch (userChoice) {
                                case 1:
                                        // Call function to display notes
                                        guitar.pickNotes();
                                        break;
                                case 2:
                                        // Call funtion to input strings and frets
                                        guitar.setStrings(keyboard);
                                        guitar.setFrets(keyboard);

                                        // Call function to display notes
                                        guitar.pickNotes();
                                        break;
                                case 3:
                                        guitar.setSleep(keyboard);
                                        guitar.pickNotes();
                                        break;
                                case 4:
                                        // Quit the loop
                                        rerun = false;
                                        break;
                        }

                }

                // Clear screen
                System.out.print(guitar.clear);
                System.out.flush();

                System.out.println("\n\nGoodbye!!");

                guitar.readNotes();

                keyboard.close();

        }
}
