public abstract class State {
    private State currentState;
    private int roundCounter;

    public State(){
        this.roundCounter = 0;
    }   

    public void setState(State currentState){
        this.currentState = currentState;
    }

    public State getState(){
        return currentState;
    }

    public void setRoundCounter(int roundCounter){
        this.roundCounter = roundCounter;
    }

    public int getRoundCounter(){
        return roundCounter;
    }

    public abstract int getTime();

    public abstract State nextState();
}
