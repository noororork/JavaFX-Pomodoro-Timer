public class FSM {
    private State currentState;
    private int time;
    
    public State getCurrentState(){
        return currentState.getState();
    }
}
