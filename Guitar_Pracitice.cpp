#include <iostream>
#include <fstream>
#include <string.h>
#include <time.h>
#include <unistd.h>

using namespace std;

class guitarData
{
public:
        //Variables
        int numOfStrings, upperFret, lowerFret;
        float sleepyTime;
        char stringsUsed[6];
        string noteNames[6][40];
        int stringNum;

        //Function Prototypes
        guitarData();
        void frets();
        void sleepAmount();
        void inputStrings();
        void pickNote();
        void readNotes();
};

//Constructor function
guitarData::guitarData()
{
        numOfStrings = 0;
        upperFret = 0;
        lowerFret = 0;
        stringNum = 0;

        for (int i = 0; i < 6; i++)
        {
                stringsUsed[i] = 'z';
        }
}

void guitarData::readNotes()
{
        //Declare file reader
        ifstream fileReader;

        //Open notes file
        fileReader.open("Guitar_Notes.txt");

        //Use character array 'noteNames' to store note letters
        for (int i = 0; i < 6; i++)
        {
                for (int j = 0; j < 20; j++)
                {
                        fileReader >> noteNames[i][j];
                }
        }

        fileReader.close();

        //DEBUG: DISPLAYS THE NOTES READ IN TO ARRAY
        // for (int i = 0; i < 6; i++)
        // {
        //         for (int j = 0; j < 20; j++)
        //         {
        //                 cout << noteNames[i][j] << " ";
        //         }
        //         cout << endl;
        // }
}

void guitarData::inputStrings()
{

        //Have the user enter the strings they'll use into the new array
        char inputBuffer[500];
        for (int i = 0; i < numOfStrings; i++)
        {
                //Prompt user to enter the string to use (clear input buffer too)
                cout << "Enter a string to use: ";
                cin >> inputBuffer;
                stringsUsed[i] = inputBuffer[0];

                //Converts the letter to uppercase if a string besides E/e is entered
                if (stringsUsed[i] != 'e')
                {
                        stringsUsed[i] = toupper(stringsUsed[i]);
                }
        }
}

void guitarData::frets()
{
        //Have user enter the upper and lower limits for the frets
        cout << "\nEnter lowest fret: ";
        cin >> lowerFret;
        cout << "Enter highest fret: ";
        cin >> upperFret;
        cout << endl;
}

void guitarData::sleepAmount()
{
        //Have user enter buffer time
        cout << "Enter buffer time: ";
        cin >> sleepyTime;
        cout << endl;
}

void guitarData::pickNote()
{
        //Seed a random number
        srand(time(NULL));

        for (int i = 0; i < 10; i++)
        {
                // Clear screen each time
                system("clear");

                // Pick a random fret number based on the upper and lower limit
                int fretNum = rand() % (upperFret - lowerFret + 1) + lowerFret;

                // Pick a random string number based on the number of strings to go through
                int currentString = rand() % numOfStrings;

                //Display the string and fret number
                cout << stringsUsed[currentString] << " " << fretNum << endl;

                //Determine the actual note by reading from noteNames array
                switch (stringsUsed[currentString])
                {
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

                //Pause (usleep is measured in microseconds (1e-6 seconds))
                usleep(sleepyTime * 1000000);

                //Display the actual note
                cout << endl
                     << noteNames[stringNum - 1][fretNum] << endl;

                //Keep position and actual note on the screen for 1.5 seconds
                usleep(1500000);
        }
}

int main()
{

        //Declare class object/instance
        guitarData guitar1;

        //Read guitar notes in from text file
        guitar1.readNotes();

        //Clear the screen
        system("clear");

        //Prompt user to enter the number of strings used
        cout << "How many strings do you want to use? ";
        cin >> guitar1.numOfStrings;

        //Clear the screen
        system("clear");

        // Call funtion to input strings
        guitar1.inputStrings();

        //Clear the screen
        system("clear");

        // Call function to input fret bounds and sleep amount
        guitar1.frets();
        guitar1.sleepAmount();

        // Call function to pick a random note (ends after displaying 10 notes)
        guitar1.pickNote();
        system("clear");

        bool rerun = true;
        int userChoice;
        char numBuffer[500];
        do
        {
                // Have user decide if they want to continue, and if so how
                cout << "Do you want to continue?\n\n"
                     << "Continue with same data(1)\nEnter new strings/frets(2)\nChange buffer time(3)\nQuit(4)\n\nEnter Choice: ";

                //Reset input buffer
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
                cin >> userChoice;

                switch (userChoice)
                {
                case 1:
                        guitar1.pickNote();
                        break;
                case 2:
                        system("clear");
                        //Prompt user to enter the number of strings used
                        cout << "How many strings do you want to use? ";
                        cin >> guitar1.numOfStrings;

                        // Call funtion to input strings and frets
                        guitar1.inputStrings();
                        guitar1.frets();
                        guitar1.pickNote();
                        break;
                case 3:
                        system("clear");
                        guitar1.sleepAmount();
                        guitar1.pickNote();
                        break;
                case 4:
                        system("clear");
                        rerun = false;
                        break;

                default:
                        cout << "ERROR! Enter a valid number\n\n";
                }

        } while (rerun == true);

        cout << "\nGoodbye!!\n";
}