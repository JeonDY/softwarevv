package Sys;
import java.util.*;
import java.util.concurrent.Callable;
import java.time.*;

public class Tick implements Callable<Void>{
    private static boolean endFlag;

    Tick(){
        endFlag = false;
    }

    public void setEndFlag(boolean flag){endFlag = flag;}

    public Void call(){
        LocalDateTime currentTime,prevTime;
        currentTime = LocalDateTime.now();
        prevTime = currentTime;

        Duration duration;
        int elapsedTime;
        int checkSecond;
        int checkMinute;
        while(true){
            if(endFlag){
                break;
            }

             duration = Duration.between(prevTime, currentTime);

             elapsedTime = duration.getNano()*1000000;
             checkSecond = 0;
             checkMinute = 0;
            //tick per 10millisecond
            if(elapsedTime == 10) {
                //stopwatch
                ((StopWatch)ModeManager.SingletonModeManager.getmodes()[3]).increaseCurrentTime();

                ModeManager.SingletonModeManager.plus_ElapsedTime(0.01f);//10ms 에 한번씩 더해주게
                //when duration = 1 second
                if (checkSecond == 100) {
                    checkSecond = 0;
                    //time
                    ((Time)ModeManager.SingletonModeManager.getmodes()[0]).timeflow();
                    //calorie check

                    //Timer
                    ((Timer)ModeManager.SingletonModeManager.getmodes()[2]).decreaseTimer();

                    //alarm
                    //Buzzer를 여기서 울리는것이 아니라 Alarm객체 내부에서 울리도록 함.
                    //구현 방식의 차이에 따라 추후 바뀔 수 있는 부분입니다.
                    ((Alarm)ModeManager.SingletonModeManager.getmodes()[1]).isAlarmTimeCheck();
                }
                //not yet 1 second
                else {
                    checkSecond++;
                }

                //when duration = 1 minute
                /*사용하지 않을 부분..?*/
                if(checkMinute == 6000){
                    checkMinute = 0;
                    //alarm

                }
                //not yet 1 minute
                else{
                    checkMinute++;
                }
            }
        }
        return null;
    }
}
