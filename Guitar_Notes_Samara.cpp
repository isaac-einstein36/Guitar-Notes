 #include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <unistd.h>

//To Run Program: 
// cd /Users/sierrabasic/Desktop/Coding
//g++ Random_Number.cpp -o run.out
//./run.out
//Control C to quit 

//main program
int main() {

    //clear screen
    system("clear");

    //declare variables
    int num=0, start_time=0, lower, upper, sleepy_time, num_used_strings, str_num;
    char str_letters[10];

    //CODE THIS - initialize string letters for use (get rid of \\ in front of ones you are using)
    str_letters[0] = 'E';
    str_letters[1] = 'e';
    str_letters[2] = 'D';
    // str_letters[3] = 'A';
    // str_letters[4] = 'G';
    // str_letters[5] = 'B';

    //CODE THIS - Number of strings you are using (change number)
    num_used_strings = 3;

    //set upper and lower limits for random number generation
    printf("\nWhat is the lower limit? ");
    scanf("%i",&lower);

    printf("\nWhat is the upper limit? ");
    scanf("%i", &upper);

    //set sleep time for random number display
    printf("\nHow long would you like to sleep for (in seconds)? ");
    scanf("%i", &sleepy_time);

    //seed random function
    srand(time(NULL));

    //run program while true
    while (true){
        //clear screen
        system("clear");

        //generate random number
        num = (rand()%(upper-lower+1))+1;

        //generate random string
        str_num = (rand()%(num_used_strings))+1;

        //print random string and number
        printf("%c%i\n",str_letters[str_num-1],num);

        //sleep for desired # of seconds
        sleep(sleepy_time);
    } //end while

} //end int main