package views;
public class time {
    int minute;
    int second;

    public time(int minute, int second) {
        this.minute = minute;
        this.second = second;
    }

    public String getCurrentTime(){
        return minute + ":" + second;
    }

    public void oneSecondPassed(){
        second--;
        if(second == -1){
            minute--;
            second = 59;
        }
    }
}
