import java.util.HashMap;

public class StateManager {

    public enum StateType {
        NOTHING, RUN, IDLE, JUMP, FALL
    }

    private HashMap<StateType, State> stateMap;
    private StateType activeStateType;
    private int activeStateTime;

    public StateManager() {
        this.stateMap = new HashMap<>();
        tryEnterState(StateType.NOTHING);
    }

    public void addState(StateType stateType, State state) {
        stateMap.put(stateType, state);
    }

    public State getActiveState() {
        return stateMap.get(activeStateType);
    }

    public int getActiveStateTime() {
        return activeStateTime;
    }

    // sole function used for switching between states
    public boolean tryEnterState(StateType stateType) {
        // only don't allow transition if active state is not done and cannot be overriden
        if (stateMap.get(stateType).canEnter() && (getActiveState().isDone() || getActiveState().canBeOverridden())) {
            activeStateType = stateType;
            activeStateTime = 0;
            return true;
        }
        return false;
    }

    public void update() {
        getActiveState().execute();
        activeStateTime++;
        if (getActiveState().isDone()) {
            boolean canEnterState = tryEnterState(getActiveState().getNextStateType());
            if (!canEnterState) tryEnterState(StateType.NOTHING);
        }
    }
}

/*
class thing {

    public static void main() {
        stateManager.add(StateType.RUN, new State() {
            @Override
            public void execute(LiftingSystem liftingSystem) {
                liftingSystem.getLift().raiseTo(position);
                if (liftingSysetm.getLift().position() > threshold) {
                    liftingSystem.getArm().rotateTo(position)
                }
            }
            @Override
            public void canEnter(LiftingSystem liftingSystem) {
                return liftingSystem.getArm.getPosition() == position
            }
            @Override
            public boolean canBeOverridden() {
                return true;
            }
            @Override
            public void isDone(StateManager stateManager, LiftingSystem liftingSystem) {
                return stateManager.getActiveStateTime() >= 1;
            }
            @Override
            public StateType getNextStateType() {
                return StateType.NOTHING;
            }
        });
    }
}

if (gamepad.a) {
    if (stateManager.getActiveState() == nothing)
        liftingSystem.getStateManager.tryEnterState(precSpecCollect);
    else if (stateManager.getActiveState() == waitToCollect)
        liftingSystem.getStateManager.tryEnterState(collect);
}

*/
