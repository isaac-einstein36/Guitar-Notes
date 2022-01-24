package Timing_Practice;

public class SimpleString {

        // Variables stored within each string
        private char name;
        private int timesPracticed;
        private double bestTime;

        // Constructor Method
        public SimpleString() {
                this.name = 'Z';
                this.timesPracticed = 0;
                this.bestTime = 0.0;
        }

        // Set string name
        public void setName(char name) {
                this.name = name;
        }

        // Get string name
        public char getName() {
                return this.name;
        }

        // Set times practiced on that string
        public void setTimesPraciced(int timesPracticed) {
                this.timesPracticed = timesPracticed;
        }

        // Get times practiced on that string
        public int getTimesPracticed() {
                return this.timesPracticed;
        }

        // Set best time on that string
        public void setBestTime(double bestTime) {
                this.bestTime = bestTime;
        }

        // Get best time on that string
        public double getBestTime() {
                return this.bestTime;
        }

}
