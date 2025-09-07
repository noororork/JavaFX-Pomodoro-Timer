public class FSM {
    private State currentState;
    private int roundCounter = 1;
    private int wholeCounter = 1;

    private Work work;
    private LongBreak longBreak;
    private ShortBreak shortBreak;

    public FSM() {
        work = new Work();
        shortBreak = new ShortBreak();
        longBreak = new LongBreak();
        currentState = work;
    }

    public State getCurrentState(){
        return currentState;
    }

    public void setCurrentState(State currentState){
        this.currentState = currentState;
    }

    public int getTime(){
        return currentState.getTime();
    }

    public void setTime(int time, State state){
        state.setTime(time);
    }

    public int getRoundCounter(){
        return roundCounter;
    }

    public void setRoundCounter(int roundCounter){
        this.roundCounter = roundCounter;
    }

    public int getWholeCounter(){
        return wholeCounter;
    }

    public void setWholeCounter(int wholeCounter){
        this.wholeCounter = wholeCounter;
    }

    public String getCurrentName(){
        return currentState.getName();
    }

    public void setNextState(){
        currentState = currentState.nextState(roundCounter, work, longBreak, shortBreak);
        if (currentState instanceof Work){
            if (roundCounter == 4){
                roundCounter = 1;
                wholeCounter++;
            }else{
                roundCounter++;
            }
        }
    }
}
