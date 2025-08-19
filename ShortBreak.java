public class ShortBreak extends State {
    private int time = 5;
    private Work work;

    public ShortBreak(){
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
