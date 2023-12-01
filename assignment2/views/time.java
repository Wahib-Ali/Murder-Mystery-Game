package views;
public class time {
    private static int minute;
    private static int second;

    public time(int minute, int second) {
        this.minute = minute;
        this.second = second;
    }

    public static String getCurrentTime(){
        return minute + ":" + second;
    }

    public static void oneSecondPassed(){
        second--;
        if(second == -1){
            minute--;
            second = 59;
        }
    }
}
