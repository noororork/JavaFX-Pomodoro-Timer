public abstract class State {
    public abstract int getTime();

    public abstract State nextState(int roundCounter, Work work, LongBreak longBreak, ShortBreak shortBreak);
}
