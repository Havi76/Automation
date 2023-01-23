package framwork.cleanup;


import framwork.util.SQLOperation;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Getter(onMethod_ = {@Synchronized})
@Setter(onMethod_ = {@Synchronized})
@Accessors(fluent = true)
public class CleanupThread extends Thread {
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private Queue<Stack<SQLOperation>> tasks = new ConcurrentLinkedQueue<>();

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private Queue<Stack<SQLOperation>> queuedTasks = new ConcurrentLinkedQueue<>();

    /**
     * This flag determines whether or not the {@link CleanupService} will accept new tasks.
     */
    private boolean hadTestsFinished = false;

    CleanupThread() {
        super("Cleanup");
        start();
        log.info("Thread started");
    }

    @Override
    @SneakyThrows
    public void run() {
        while (!hadTestsFinished || !tasks.isEmpty()) {
            if (!tasks.isEmpty()) {
                Stack<SQLOperation> currentStack = tasks.poll();

                if (currentStack != null) {
                    while (!currentStack.isEmpty()) {
                        try {
                            currentStack.pop().run();
                        } catch (SQLException ex) {
                            log.error("A cleanup error had occurred, stack trace follows", ex);
                        } catch (Exception ex) {
                            log.error("An error had occurred, stack trace follows", ex);
                        }
                    }
                }
            }

            if (!queuedTasks.isEmpty()) {
                while (!queuedTasks.isEmpty()) {
                    tasks.offer(queuedTasks.poll());
                }
            }
        }
    }

    @Synchronized
    public void queueTasks(Stack<SQLOperation> taskStack) {
        queuedTasks.offer(taskStack);
    }
}
