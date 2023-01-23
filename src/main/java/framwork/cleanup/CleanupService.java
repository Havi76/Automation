package framwork.cleanup;


import framwork.util.SQLOperation;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Stack;


@Slf4j
@Accessors(fluent = true)
public class CleanupService {
    private final static ThreadLocal<Stack<SQLOperation>> classLevelStack = ThreadLocal.withInitial(Stack::new);
    private final static ThreadLocal<Stack<SQLOperation>> methodLevelStack = ThreadLocal.withInitial(Stack::new);
    private final static ThreadLocal<Stack<SQLOperation>> testLevelStack = ThreadLocal.withInitial(Stack::new);

    private final static ThreadLocal<Stack<SQLOperation>> currentStack = ThreadLocal.withInitial(Stack::new);

    @Getter
    private final static CleanupThread cleanupThread = new CleanupThread();

    /**
     * <p>Sets the funneling level.
     * <p>After setting the level, future {@link #stack(SQLOperation)} calls will add operations to the corresponding stack.
     *
     * @param level the stack {@link Level} enum
     */
    public static void setLevel(Level level) {
        switch (level) {
            case CLASS -> currentStack.set(classLevelStack.get());
            case METHOD -> currentStack.set(methodLevelStack.get());
            case TEST -> currentStack.set(testLevelStack.get());
        }
    }

    /**
     * Stacks an operation to perform
     *
     * @param SQLOperation the function to perform
     */
    public static void stack(SQLOperation SQLOperation) {
        currentStack.get().add(SQLOperation);
    }

    /**
     * Performs all operations in the stack corresponded to the given cleanup {@link Level}
     *
     * @param cleanupLevel the stack {@link Level} enum
     */
    @SuppressWarnings("unchecked")
    public static void cleanUp(Level cleanupLevel) {
        Stack<SQLOperation> stack = switch (cleanupLevel) {
            case CLASS -> classLevelStack.get();
            case METHOD -> methodLevelStack.get();
            case TEST -> testLevelStack.get();
        };

        if (stack.isEmpty()) {
            return;
        }

        cleanupThread.queueTasks((Stack<SQLOperation>) stack.clone());
        stack.removeAllElements();
    }

    public enum Level {
        CLASS(),
        METHOD(),
        TEST()
    }
}
