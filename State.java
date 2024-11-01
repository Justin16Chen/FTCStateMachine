public interface State {
    public void execute();
    // are all of the safety requirements met so that mechanically, this state is able to be entered
    public boolean canEnter();
    public boolean canBeOverridden();
    public boolean isDone();
    public StateManager.StateType getNextStateType();
}
