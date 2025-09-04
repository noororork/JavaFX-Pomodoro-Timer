public class FSM {
    private State currentState;
    private int roundCounter = 1;
    private int complete = 1;

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

    public int getTime(){
        return currentState.getTime();
    }

    public int getRoundCounter(){
        return roundCounter;
    }

    public int getWholeCounter(){
        return complete;
    }

    public String getCurrentName(){
        return currentState.getName();
    }

    public void setNextState(){
        currentState = currentState.nextState(roundCounter, work, longBreak, shortBreak);
        if (currentState instanceof Work){
            if (roundCounter == 4){
                roundCounter = 1;
                complete++;
            }else{
                roundCounter++;
            }
        }
    }
}
