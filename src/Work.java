public class Work extends State{
    private int time = 7;
    private String name = "WORK";

    @Override
    public int getTime(){
        return time;
    }

    @Override
    public void setTime(int time){
        this.time = time;
    }

    @Override
    public String getName(){
        return name;
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
