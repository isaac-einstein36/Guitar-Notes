package Timing_Practice;

import java.util.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class Timing_Naming_Progress {

        // Global Variables

        private String stringName;
        private double bestTime;

        // Constructor Method
        Timing_Naming_Progress() {

                this.stringName = "E";
                this.bestTime = 0.00;

        }

        public static void timerMethod(Scanner in) {
                // Have program pause so User can initiate timer
                System.out.print("Press enter when ready to start\n");
                in.nextLine();

                // Create a variable to store starting time of timer
                long startTime = System.currentTimeMillis();

                // Have user tell the program when they are done
                System.out.println("Press enter when finished");
                in.nextLine();

                // Determine and display elapsed time
                // Have program determine the elapsed time
                long elapsedTime = System.currentTimeMillis() - startTime;

                // Convert elapsed milliseconds into seconds
                double elapsedSeconds = ((double) elapsedTime) / 1000;

                // Display elapsed time in seconds
                System.out.printf("Time elapsed: %.3f\n", elapsedSeconds);
        }

        public static void readFile() throws FileNotFoundException {

                // PrintWriter writer = new PrintWriter(new File("GuitarNaming.csv"));

                File file = new File("NamingProgressRecord.txt");

                PrintWriter writer = new PrintWriter(file);

                writer.write("hello!");

                writer.close();


        }

        public static void main(String[] args) throws FileNotFoundException {

                // Create a scanner to read user input
                Scanner keyboard = new Scanner(System.in);

                // timerMethod(keyboard);

                // writingToExcel(keyboard);

                SimpleString firstString = new SimpleString();

                // char name = firstString.getName();

                // System.out.println(name);

                System.out.println("HELLO");

                firstString.setName('E');

                System.out.println(firstString.getName());

                readFile();

                // Close scanner
                keyboard.close();
        }

}
