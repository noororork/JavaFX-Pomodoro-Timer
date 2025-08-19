public class LongBreak extends State {
    private int time = 5;
    private Work work;

    public LongBreak(){
        work = new Work();
    }

    @Override
    public int getTime(){
        return time;
    }

    @Override
    public State nextState(){
        return work;

    }
}
