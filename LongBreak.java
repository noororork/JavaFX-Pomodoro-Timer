public class LongBreak extends State {
    private int time = 5;

    @Override
    public int getTime(){
        return time;
    }

    @Override
    public State nextState(int roundCounter, Work work, LongBreak longBreak, ShortBreak shortBreak){
        return work;
    }
}
