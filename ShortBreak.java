public class ShortBreak extends State {
    private int time = 5;
    private String name = "SHORT BREAK";

    @Override
    public int getTime(){
        return time;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public State nextState(int roundCounter, Work work, LongBreak longBreak, ShortBreak shortBreak){
        return work;
    }
}
