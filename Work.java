public class Work extends State{
    private int time = 10;
    private State longBreak;
    private State shortBreak;

    public int getTime(){
        return time;
    }

    public State nextState(){
        if (getRoundCounter() == 4){
            return longBreak;
        }else{
            return shortBreak;
        }
    }

    public void updateRoundCounter(){
        if (getRoundCounter() == 4){
            setRoundCounter(1);
        } else{
            setRoundCounter(getRoundCounter() + 1);
        }
    }
}
