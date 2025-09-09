public abstract class State {
    public abstract String getName();

    public abstract int getTime();

    public abstract State nextState(int roundCounter, Work work, LongBreak longBreak, ShortBreak shortBreak);

    public abstract void setTime(int time);
}
