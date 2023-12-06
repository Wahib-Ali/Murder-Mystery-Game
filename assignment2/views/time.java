package views;
public class time {
    int minute;
    int second;

    /**
     *
     * @param minute The amount of minutes left in the countdown
     * @param second The amount of seconds left in the countdown
     */
    public time(int minute, int second) {
        this.minute = minute;
        this.second = second;
    }

    /**
     * getCurrentTime:
     * ___________________________
     *
     * Getter method more the time remaining
     *
     * @return a string that represents the amount of time left to complete the game
     */

    public String getCurrentTime(){
        return minute + ":" + second;
    }

    /**
     * oneSecondPassed
     * ___________________________
     *
     * This method simulates one second passing in the countdown.
     */
    public void oneSecondPassed(){
        second--;
        if(second == -1){
            minute--;
            second = 59;
        }
    }
}
