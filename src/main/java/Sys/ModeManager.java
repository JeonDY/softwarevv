package Sys;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 */
public class ModeManager {

    /**
     * Default constructor
     */
    public ModeManager() {

        buzzer = new Buzzer();


        modes = new Mode[5];
        modes[0] = new Time();
        modes[1] = new Alarm(buzzer, modes[0]);
        modes[2] = new Timer();
        modes[3] = new StopWatch();
        modes[4] = new CalorieCheck();
        modes[5] =new WorldTime();
        SingletonModeManager=this;
        isEditMode = false;
    }
    //모드매니저
    public static ModeManager SingletonModeManager;

    //새로 추가 함수 Mode배열
    public Mode[] getmodes(){return modes;}

    //Time Alarm Timer Stopwatch Calorie Check 순
    //0     1       2       3       4       5
    private Boolean[] isModeActive;
    private Boolean[] editStatus;//set mode 중 return to default screen시 저장안하기 위해 이 변수로 편집.  //천민수 : 저희 default시 저장 안하는 부분도 있는건가요?

    private Mode[] modes;
    private int currentMode;//0~5 Time Alarm Timer Stopwatch Calorie Check 순으로    *8일경우 setMode
    private int[] activeList;//활성된거 4개가 들어가는 배열 4개가 활성화되어있을 때 비활성화된걸 활성화시킬때 관리하기위함
    //각 mode에 대한 index값이 들어가게 됨.

    //private Button clickedButton;
    private Boolean longClickedFlag;
    private Boolean buzzerFlag;
    private int elapsedTime;
    private int ActiveModeCounter;//setMode때 4개의 mode가 활성화되어야만 탈출가능 그때 참조할 변수 active된 mode의 개수
    private int currentCursor;//setMode시에 status값을 바꿀 때 참조할 index

    //Mode  Adjust  Forward Reverse
    //0     1       2       3
    private int Button;
    private boolean isEditMode;

    //객체 생성 ms
    private Buzzer buzzer;

    public void setButton(int Button){this.Button = Button;}
    public int getButton() {return this.Button;}

    public void setLongClickedFlag(boolean flag){longClickedFlag = flag;}


    public void makeThread(){
        Tick task = new Tick();
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Void> future = service.submit(task);
    }

    public void changeMode() {
        while(true){
            currentMode = (currentMode+1)%6;
            if(modes[currentMode].getActive() == true){
                break;
            }
        }
    }

    public void clickButton() {
        //이건 뭔가요..?
        if(currentMode==0 && Button==0 && longClickedFlag==false && isEditMode==false){

        }
        if(buzzerFlag){// 버저 울릴때
            //아무 버튼이나 들어오면
            if(Button != -1)
                buzzer.stopBuzzer();
        }
        else{
            switch (currentMode){
                case 0://Time 모드  일때
                    if(isEditMode){
                        //button 1234
                    }
                    else{
                        //button 1234
                    }
                    break;
                
                case 1://알람 모드  일때
                    if(isEditMode){
                        //button 1234
                        if(elapsedTime >= 5) {    //elapsedTime이 5초 이상일 때 저장하고 defaultScreen으로.
                           ((Alarm) modes[1]).saveAlarm();
                           isEditMode = !isEditMode;
                        }
                        else if(Button == 0) {  //Mode를 눌렀을 때 저장하고 defualtScreen으로.
                            ((Alarm) modes[1]).saveAlarm();
                            isEditMode = !isEditMode;
                        }
                        else if(Button == 1)    //Adjust : Cursor 옮김
                            ((Alarm)modes[1]).changeCursor();
                        else if(Button == 2)    //Forward : Cursor의 데이터를 증가시킴.
                            ((Alarm)modes[1]).increaseAlarmTime();
                        else if(Button == 3)    //Reverse : Cursor의 데이터를 감소시킴.
                            ((Alarm)modes[1]).decreaseAlarmTime();
                    }
                    else{
                        if(Button == 0 && longClickedFlag == true) {    //setMode로 진입.
                            ((Alarm) modes[1]).enterEditAlarm();
                            isEditMode = !isEditMode;
                        }
                        else if(Button == 0 && longClickedFlag == false)    //Mode : changeMode
                            this.changeMode();
                        else if(Button == 1)    //Adjust : 현재 보고 있는 Alarm을 바꾼다.
                            ((Alarm)modes[1]).changeAlarm();
                        else if(Button == 2)    //Forward : 현재 보고 있는 알람을 on/off시킨다.
                            ((Alarm)modes[1]).turnOnOffAlarm();
                        else if(Button == 3);   //지정된 버튼이 없다.
                    }
                    break;
                case 2:
                    break;
                case 3: //StopWatch
                        if(Button == 0)
                            this.changeMode();  //Mode : changeMode
                        if(Button == 1 && !( ((StopWatch)modes[3]).getIsPaused() ))  //Adjust 장타 : resume 되어있었다면 laptime save.
                            ((StopWatch)modes[3]).lapStopwatch();
                        if(Button == 1 && ((StopWatch)modes[3]).getIsPaused()) //Adjust : paused라면 reset.
                            ((StopWatch)modes[3]).resetStopwatch();
                        if(Button == 2 && ((StopWatch)modes[3]).getIsPaused())  //Forward : puased라면 start. , 사실 Resume이나 Start나 operation 내부 동작은 같다...
                            ((StopWatch)modes[3]).startStopwatch();
                        if(Button == 2 && !( ((StopWatch)modes[3]).getIsPaused() )) //Forward : paused가 아니라면 pause.
                            ((StopWatch)modes[3]).pauseStopwatch();
                        if(Button == 3);    //지정된 버튼이 없다.
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 8:
                    break;
                default:
                    break;
            }
        }


    }


    /*
    setMode
    currentMode를 8으로 설정(for display)
     */

    public void enterEditMode() {
        this.currentMode=8;
        this.currentCursor=0;
        this.editStatus=this.isModeActive;
    }


    public void changeCursor() {
        if(this.currentCursor==5) {
            this.currentCursor=0;
            return;
        }
        this.currentCursor+=1;
    }


    public void changeStatus() {// active->disable  disable->active
        if(this.editStatus[this.currentCursor]) {
            this.editStatus[this.currentCursor]=false;
            for(int i=0;i<4;i++) {
                if(this.activeList[i]==this.currentCursor) {
                    for(int j=i+1;j<4;j++) {
                        this.activeList[j-1]=this.activeList[j];
                    }
                    this.ActiveModeCounter--;
                }
            }
        }
        else {
            this.editStatus[this.currentCursor]=true;
            if(this.ActiveModeCounter==4) {
                for(int i=1;i<4;i++) {
                    this.activeList[i-1]=this.activeList[i];
                }
                this.activeList[3]=this.currentCursor;
            }
            else {
                this.activeList[this.ActiveModeCounter-1]=this.currentCursor;
                this.ActiveModeCounter++;
            }
        }
    }


    public void saveModeData() {
        this.isModeActive=this.editStatus;
    }


}
