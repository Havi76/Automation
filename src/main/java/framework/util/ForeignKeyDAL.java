package framework.util;


import framework.cleanup.CleanupService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;

/**
 * Represents a DAL which has 2 foreign keys which are used to uniquely identify a row
 *
 * @param <FK1> the 1st key's type
 * @param <FK2> the 2nd key's type
 * @param <V>   the model representing this table
 */
@Slf4j
@ParametersAreNonnullByDefault
public abstract class ForeignKeyDAL<V, FK1, FK2> extends DBHelper<V> {
    /**
     * Get a map with column name and values required to find a single row
     *
     * @param value the object
     * @return a map with the values
     */
    protected abstract Map<String, Object> filters(V value);

    public List<V> findAll() {
        return super.selectAll();
    }

    public abstract V findOne(FK1 key1, FK2 key2);

    public V save(V value) {
        log.info("Insert: (%s) - %s".formatted(value.getClass().getSimpleName(), value));

        insert(value);

        CleanupService.stack(() -> delete(value));

        return value;
    }


    public void delete(V value) {
        log.info("Delete: (%s) - %s".formatted(value.getClass().getSimpleName(), value));

        super.delete(filters(value));
    }
}
