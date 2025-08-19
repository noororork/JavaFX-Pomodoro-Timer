public class FSM {
    private State currentState;
    private State nextState;

    public FSM() {
        currentState = new Work();
    }
    
    public State getCurrentState(){
        return currentState;
    }

    public int getTime(){
        return currentState.getTime();
    }

    public void updateRoundCounter(){
        if (currentState.getRoundCounter() == 4){
            currentState.setRoundCounter(1);
        } else{
            currentState.setRoundCounter(currentState.getRoundCounter() + 1);
        }
    }

    public void setNextState(){
        nextState = currentState.nextState();
        currentState.setState(nextState);
    }
}
