public class Work extends State{
    private int time = 10;
    private LongBreak longBreak;
    private ShortBreak shortBreak;

    public Work(){
        longBreak = new LongBreak();
        shortBreak = new ShortBreak();
    }

    @Override
    public int getTime(){
        return time;
    }

    @Override
    public State nextState(){
        if (getRoundCounter() == 4){
            return longBreak;
        }else{
            return shortBreak;
        }
    }
}
