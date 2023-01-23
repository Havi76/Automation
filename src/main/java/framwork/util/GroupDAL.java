package framwork.util;


import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * Represents a DAL in which rows are grouped with a single key
 * <p>For instance: stations are grouped by MissionId
 *
 * @param <GroupKey> the key's type
 * @param <V>        the model representing this table
 */
@Slf4j
public abstract class GroupDAL<V, GroupKey, PrimaryKey> extends PrimaryKeyDAL<V, PrimaryKey> {
    protected abstract Map<String, Object> groupFilters(GroupKey key);

    public abstract List<V> saveGroup(V[] values);

    public abstract List<V> findGroup(GroupKey key);

    public abstract void deleteGroup(GroupKey key);

    protected abstract Map<String, Object> filters(V v);

    public abstract V findOne(PrimaryKey v);

    public Integer update(V value) {
        return super.update(value, filters(value));
    }

    public Integer updateAndRollback(V value) {
        return super.updateAndRollback(value, filters(value));
    }
}