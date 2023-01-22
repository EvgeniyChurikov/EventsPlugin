package me.needenoughsleep.eventsplugin;

import org.bukkit.Bukkit;

public class Timer {
    public interface TickAction {
        void run(int t);
    }

    private final Runnable before;
    private final TickAction tick;
    private final Runnable after;

    private final long period;

    private int counter;

    private int taskId = -1;

    public Timer(Runnable before, TickAction tick, Runnable after, int fromNumber, long period) {
        this.before = before;
        this.tick = tick;
        this.after = after;
        this.counter = fromNumber;
        this.period = period;
    }

    public void start() {
        before.run();
        taskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(EventsPlugin.getInstance(), () -> {
            if (counter <= 0) {
                Bukkit.getServer().getScheduler().cancelTask(taskId);
                after.run();
            }
            else {
                tick.run(counter);
                --counter;
            }
        }, 0L, period);
    }
}
