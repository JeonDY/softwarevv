package Sys;

import java.time.*;
import java.util.*;

/*
import GUI class;
import Time class;
 */
public class CalorieCheck implements Mode{

    /**
     * Default constructor
     */
    public CalorieCheck() {
        pauseCalorieCheckFlag = true;
        isActivated = false;
        cursor = false;
        Speed = 5;
        Weight = 60;
        Calorie = 0;

        //stopwatch 개념이므로 0시0분0초에서 시작
        CalorieTime= LocalTime.of(0,0,0,0);
    }
    LocalTime overflowStopWatchTime;

    /**
     *
     */
    private int Speed;
    private int tempSpeed;
    public int getSpeed() {return Speed;};
    public void setSpeed(int Speed) {this.Speed = Speed;}
    /**
     *
     */
    private int Weight;
    private int tempWeight;
    public int getWeight() {return Weight;}
    public void setWeight(int Weight){this.Weight = Weight;}
    /**
     *
     */
    private int Calorie;
    public int getCalorie() {
        calculateCalorie();
        return Calorie;
    }
    public void setCalorie(int Calorie){this.Calorie = Calorie;}

    /**
     stopwatch 개념의 시계
     0시0분0초 ~ 23시 59분 59초까지 측정 가능하게 할 것
     */
    private LocalTime CalorieTime;
    public LocalTime getCalorieTime() {return CalorieTime;}
    public int getHour(){return CalorieTime.getHour();}
    public int getMinute(){return CalorieTime.getMinute();}
    public int getSecond(){return CalorieTime.getSecond();}

    //    flag
    private boolean pauseCalorieCheckFlag;
    private boolean isActivated;

    //    false = speed, true = weight
    private boolean cursor;



    /**
     *
     */
    public void changeCursor() {
        cursor = !cursor;
    }

    public void setActive(boolean act) {isActivated = act;};
    public boolean getActive() {return isActivated;};
    /**
     *
     */
    public void increaseData() {
        if(cursor){
            if(tempWeight == 999){
                tempWeight = 0;
            } else{
                tempWeight += 1;
            }
        }else{
            if(Speed == 99){
                tempSpeed = 0;
            } else {
                tempSpeed += 1;
            }
        }
    }

    /**
     *
     */
    public void decreaseData() {
        if(cursor){
            if(tempWeight == 0){
                tempWeight = 999;
            } else{
                tempWeight -= 1;
            }
        }else{
            if(tempSpeed == 0){
                tempSpeed = 99;
            } else {
                tempSpeed -= 1;
            }
        }
    }

    /**
     임시변수에 저장해놓은 값을 실제 speed, weigth 변수에 저장
     */
    public void saveCalorieSetting() {
        tempSpeed = Speed;
        tempWeight = Weight;
    }

    /**
     *
     */
    public void enterSetSpeedandWeight() {
//        do some display logic
        tempWeight = Weight;
        tempSpeed = Speed;
    }

    /**
     *
     */
    public void startCalorieCheck() {
        pauseCalorieCheckFlag = false;
        cursor = false;

    }

    /**
     *
     */
    public void resumeCaloreCheck() {
        pauseCalorieCheckFlag = false;
        startCalorieCheck();
    }

    /**
     *
     */
    public void pauseCalorieCheck() {
        pauseCalorieCheckFlag = true;
    }

    public void endCalorieCheck(){
        pauseCalorieCheckFlag = true;
    }
    /**
     *
     */
    public void increaseCalorieCheckTimer() {
        //10ms마다 호출된다
        //하지만 기본단위는 초이다.

        //calorie check가 pause 상태가 아닐 때
        if(!pauseCalorieCheckFlag){
            //23시 59분 59초가 되면 시간측정 및 계산 종료
            if(CalorieTime.getHour() == 23 && CalorieTime.getMinute() == 59
                    && CalorieTime.getSecond() == 59){
                endCalorieCheck();
            }
            else{
                CalorieTime.plusNanos(10000000);
            }
        }
    }

    public void resetCalorieCheck(){
//        pauseCalorieCheck = true;
        cursor = false;
        Speed = 5;
        Weight = 60;
        Calorie = 0;
        CalorieTime = LocalTime.of(0,0,0,0);
    }

    private void calculateCalorie(){
        int allSeconds = CalorieTime.getHour()*3600 + CalorieTime.getMinute()*60
                + CalorieTime.getSecond();
        Calorie = (int) (0.0157 * ( ( 0.1 * Speed + 3.5 ) /3.5 ) * Weight * allSeconds);
    }
}
