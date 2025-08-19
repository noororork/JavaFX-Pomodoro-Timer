public class Work extends State{
    private int time = 10;

    @Override
    public int getTime(){
        return time;
    }

    @Override
    public State nextState(int roundCounter, Work work, LongBreak longBreak, ShortBreak shortBreak){
        if (roundCounter == 4){
            return longBreak;
        }else{
            return shortBreak;
        }
    }
}
