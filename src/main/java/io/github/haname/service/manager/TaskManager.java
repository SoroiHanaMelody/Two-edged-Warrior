package io.github.haname.service.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author SanseYooyea
 */
public enum TaskManager {
    /**
     * 单例
     */
    INSTANCE;

    /**
     * 任务管理器
     */
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private final Map<String, ScheduledFuture<?>> taskMap = new HashMap<>();

    /**
     * 关闭任务管理器
     */
    public void shutdown() {
        executorService.shutdown();
    }

    /**
     * 执行任务
     *
     * @param runnable 任务
     */
    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    /**
     * 延迟执行任务
     *
     * @param taskName 任务名
     * @param runnable 任务
     * @param delay    延迟时间
     * @param unit     时间单位
     */
    public void schedule(String taskName, Runnable runnable, long delay, TimeUnit unit) {
        taskMap.put(taskName, executorService.schedule(runnable, delay, unit));
    }

    /**
     * 延迟执行任务
     *
     * @param taskName     任务名
     * @param runnable     任务
     * @param initialDelay 延迟时间
     * @param period       间隔时间
     * @param unit         时间单位
     */
    public void scheduleAtFixedRate(String taskName, Runnable runnable, long initialDelay, long period, TimeUnit unit) {
        taskMap.put(taskName, executorService.scheduleAtFixedRate(runnable, initialDelay, period, unit));
    }

    /**
     * 延迟执行任务
     *
     * @param taskName     任务名
     * @param runnable     任务
     * @param initialDelay 延迟时间
     * @param delay        间隔时间
     * @param unit         时间单位
     */
    public void scheduleWithFixedDelay(String taskName, Runnable runnable, long initialDelay, long delay, TimeUnit unit) {
        taskMap.put(taskName, executorService.scheduleWithFixedDelay(runnable, initialDelay, delay, unit));
    }

    /**
     * 强制取消任务
     *
     * @param taskName 任务名
     */
    public void forceCancel(String taskName) {
        ScheduledFuture<?> future = taskMap.get(taskName);
        if (future != null) {
            future.cancel(true);
        }
    }

    /**
     * 取消任务
     *
     * @param taskName 任务名
     */
    public void cancel(String taskName) {
        ScheduledFuture<?> future = taskMap.get(taskName);
        if (future != null) {
            future.cancel(false);
        }
    }
}
