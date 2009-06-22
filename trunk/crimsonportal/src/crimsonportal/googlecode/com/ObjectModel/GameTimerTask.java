package crimsonportal.googlecode.com.ObjectModel;

public class GameTimerTask
{
    public GameTimerTask(GameTime triggerTime, GameTimerAction triggerAction)
    {
        this.triggerTime = triggerTime;
        this.triggerAction = triggerAction;
    }

    public boolean checkTriggered(GameTime currentGameTime) 
    {
        if (currentGameTime.isAfter(triggerTime)) {
            // Timer has elapsed:
            triggerAction.trigger();
            return true;
        }
        return false;
    }

    protected GameTime getTriggerTime() {
        return triggerTime;
    }

    public String toString() {
        return "GameTimerTask[trigger=" + triggerAction + " at " + triggerTime + "]";
    }

    protected GameTime triggerTime;
    protected GameTimerAction triggerAction;
}