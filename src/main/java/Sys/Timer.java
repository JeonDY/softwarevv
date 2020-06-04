package Sys;

import java.util.*;
import java.time.*;

/**
 *
 */
public class Timer implements  Mode{

    /**
     * Default constructor
     */
    private boolean isActivated;
    @Override
    public void setActive(boolean act) {
        this.isActivated=act;
    }

    @Override
    public boolean getActive() {
        return this.isActivated;
    }

    public Timer() {
        isActivated=true;
        timerTime= LocalDateTime.of(2000,1,1,0,0,0);
        pauseTimerFlag=true;


    }

    //cancel 용 Timer 변수 저장
    private LocalDateTime settingTimer;

    //시간 을 99 까지 표현해야 하므로 Display할때 day까지 묶어서 계산해야함
    // 추가적으로 Day는 0값을 가질수 없어 1으로 초기값을 설정 그래서 Hour계산할때
    // (Day-1)*24 + Hour 이 Display될 시간
    private LocalDateTime timerTime;

    private Boolean saveTimerFlag;

    private Boolean pauseTimerFlag;

    //추가한 변수
    private int timerCursor;
    //추가한 함수
    public void enterEditTimer(){
        pauseTimerFlag=true;
        saveTimerFlag=false;
    }

    public void changeCursor() {
        // TODO implement here
        timerCursor= (timerCursor+1)%3;
    }

    // 0~59 처리 포함 되어야 함 분을 59->0 해도 시간 증가 안하게
    public void increaseData() {
        // TODO implement here
        switch (timerCursor){
            case 0:
                if(timerTime.getDayOfMonth() >= 4 && timerTime.getHour() >=3 ){
                    timerTime= LocalDateTime.of(2000,1,1,0,timerTime.getMinute(),timerTime.getSecond());
                }
                else{
                    timerTime.plusHours(1);
                }
                break;
            case 1:
                if(timerTime.getMinute() >= 59){
                    timerTime.minusMinutes(timerTime.getMinute());
                }
                else{
                    timerTime.plusMinutes(1);
                }
                break;
            case 2:
                if(timerTime.getSecond() >= 59){
                    timerTime.minusSeconds(timerTime.getSecond());
                }
                else{
                    timerTime.plusSeconds(1);
                }
                break;
        }
    }


    public void decreaseData() {
        // TODO implement here
        switch (timerCursor){
            case 0:
                if(timerTime.getDayOfMonth() == 1 && timerTime.getHour() ==0 ){
                    timerTime= LocalDateTime.of(2000,1,5,3,timerTime.getMinute(),timerTime.getSecond());
                }
                else{
                    timerTime.plusHours(-1);
                }
                break;
            case 1:
                if(timerTime.getMinute() == 0){
                    timerTime.plusMinutes(59);
                }
                else{
                    timerTime.plusMinutes(-1);
                }
                break;
            case 2:
                if(timerTime.getSecond() == 0){
                    timerTime.plusSeconds(59);
                }
                else{
                    timerTime.plusSeconds(-1);
                }
                break;
        }
    }


    public void saveTimer() {
        // TODO implement here
        this.settingTimer=this.timerTime;
        saveTimerFlag=true;
    }

    //public으로 바꿈
    public boolean decreaseTimer() {
        // TODO implement here
        if(pauseTimerFlag){
            return false;
        }

        //
        timerTime.minusNanos(10000000);

        if(!pauseTimerFlag){
            LocalDateTime defaulTime=LocalDateTime.of(2000,1,1,0,0,0);
            if(defaulTime.isAfter(timerTime) || defaulTime.isEqual(timerTime)){
                //ModeManager.beepbuzzer()
                pauseTimerFlag=true;
                timerTime=LocalDateTime.of(2000,1,1,0,0,0);
                return true;
            }
        }
        return false;


    }

    public void start_pauseTimer(){
        if(pauseTimerFlag){
            if(timerTime.getDayOfMonth()==1 && timerTime.getHour()==0 && timerTime.getSecond()==0 && timerTime.getMinute()==0){
                //0 리셋 상태일 때 아무작동 x
                return;
            }
            else{
                pauseTimerFlag=false;
            }
        }
        else{ //pauseTimer;
            pauseTimerFlag=true;
        }
    }

    public void cancelTimer() {
        // TODO implement here
        timerTime= this.settingTimer;
    }

    //get cursor
    public int getCurrentCursor() {
        return this.timerCursor;
    }
    //get Timer time
    public LocalDateTime getTimerTime() {return this.timerTime;}

}
     //밑의 함수들은 삭제해도 될듯
//    public void startTimer() {
//        // TODO implement here
//
//        pauseTimerFlag=false;
//    }
//
//
//    public void pauseTimer() {
//        // TODO implement here
//        pauseTimerFlag=true;
//    }
//
//
//    public void resumeTimer() {
//        // TODO implement here
//        pauseTimerFlag=false;
//    }

