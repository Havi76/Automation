package framwork.util;

import framwork.annotations.database.Table;
import framwork.cleanup.CleanupService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Represents a DAL class which has a primary key
 *
 * @param <Key>   the primary key's type
 * @param <Value> the model representing this table
 */
@Slf4j
@ParametersAreNonnullByDefault
public abstract class PrimaryKeyDAL<Value, Key> extends DBHelper<Value> {
    private final Class<Key> keyType;

    @SuppressWarnings("unchecked")
    protected PrimaryKeyDAL() {
        keyType = (Class<Key>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * Get a map with column name and values required to find a single row
     *
     * @param value the object
     * @return a map with the values
     */
    protected abstract Map<String, Object> filters(Value value);

    public List<Value> findAll() {
        return super.selectAll();
    }

    public abstract Value findOne(Key id);

    public Value save(Value value) {
        log.info("Insert: - %s".formatted(value));

        Value returnValue;

        if (getAnnotationIfNotNull(Table.class).isIdGenerated()) {
            Key key = insert(value, keyType);
            returnValue = findOne(key);
        } else {
            insert(value);
            returnValue = value;
        }

        CleanupService.stack(() -> delete(returnValue));
        return returnValue;
    }

    public Integer update(Value value) {
        log.info("Update: (%s) - %s".formatted(value.getClass().getSimpleName(), value));

        return super.update(value, filters(value));
    }

    public Integer updateAndRollBack(Value value) {
        log.info("Update: (%s) - %s".formatted(value.getClass().getSimpleName(), value));

        return super.updateAndRollback(value, filters(value));
    }

    public void delete(Value value) {
        log.info("Delete: (%s) - %s".formatted(value.getClass().getSimpleName(), value));

        super.delete(filters(value));
    }
}

