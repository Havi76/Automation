package framework.util;

import java.sql.SQLException;

/**
 * A function which might throw {@link SQLException}
 */
@FunctionalInterface
public interface SQLOperation {
    void run() throws SQLException;
}
