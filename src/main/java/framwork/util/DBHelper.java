package framwork.util;

import com.google.common.base.Joiner;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import framwork.annotations.database.Column;
import framwork.annotations.database.Database;
import framwork.annotations.database.Table;
import framwork.cleanup.CleanupService;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.OffsetDateTime.parse;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ParametersAreNonnullByDefault
public class DBHelper<M> implements Module {
    protected static final String IS_NULL = "IS NULL";
    protected static final String IS_NOT_NULL = "IS NOT NULL";

    private static HikariDataSource dataSource;

    private final Class<M> type;

    /**
     * Retrieve the generic type of this instance
     */
    @SuppressWarnings("unchecked")
    protected DBHelper() {
        this.type = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private static HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlserver://mefakdim-db.database.windows.net:1433;database=updateble-mefakdim-db");
        config.setUsername("yotam");
        config.setPassword("Aa123456");
        config.setMaximumPoolSize(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    protected static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            dataSource = createDataSource();
        }

        return dataSource.getConnection();
    }

    /**
     * <p>Inserts the given model-instance into it's corresponding table.
     * <p>The method will return a generated Id of type {@code idType}.
     *
     * @param object the entity to insert
     * @param idType the type of generated Id the model has
     * @return the generated Id value
     */
    @SuppressWarnings("unchecked")
    @Synchronized
    @SneakyThrows(SQLException.class)
    protected final <T> T insert(M object, Class<T> idType) {
        @Cleanup Connection conn = getConnection();
        Table table = getAnnotationIfNotNull(Table.class);

        List<String> columns = getObjectColumnList(object);
        List<String> values = getObjectValuesList(object);

        PreparedStatement insert =
                conn.prepareStatement("""
                                INSERT INTO %s(%s)
                                VALUES (%s)
                                """.formatted(table.name(),
                        String.join(",", columns),
                        String.join(",", values)),
                        Statement.RETURN_GENERATED_KEYS);
        insert.execute();

        ResultSet generatedKeys = insert.getGeneratedKeys();

        if (generatedKeys.next()) {
            // Return int or long

            if (generatedKeys.getObject(1) instanceof BigDecimal) {
                BigDecimal key = (BigDecimal) generatedKeys.getObject(1);
                if (Integer.class.equals(idType)) {
                    return (T) Integer.valueOf(key.intValue());
                }

                if (Long.class.equals(idType)) {
                    return (T) Long.valueOf(key.intValue());
                }
            } else {
                return (T) generatedKeys.getObject(1);
            }
        }

        // Fall-through
        return null;
    }

    /**
     * <p>Inserts the given model-instances into the corresponding table.
     * <p>They are inserted in a single database query.
     *
     * @param objects the objects to insert
     */
    @SafeVarargs
    @Synchronized
    @SneakyThrows(SQLException.class)
    protected final void insert(M... objects) {
        assertThat(objects).as("Cannot insert empty array")
                .hasSizeGreaterThan(0);

        @Cleanup Connection conn = getConnection();
        Table table = getAnnotationIfNotNull(Table.class);

        List<String> columns = getObjectColumnList(objects[0]);
        List<List<String>> values = new ArrayList<>();

        for (M object : objects) {
            List<String> valueList = getObjectValuesList(object);
            values.add(valueList);
        }

        PreparedStatement insert =
                conn.prepareStatement("""
                                INSERT INTO %s(%s)
                                VALUES %s
                                """.formatted(table.name(),
                        String.join(",", columns),
                        values.stream()
                                .map(valueList -> "(" + String.join(",", valueList) + ")")
                                .collect(joining(","))),
                        Statement.RETURN_GENERATED_KEYS);

        insert.execute();
    }

    /**
     * <p>Parse the given object and get a {@link List}.
     * <p>The list consists of every data-member value of the given object.
     * <br><p>For the given class:
     * <pre>{@code class Animal {
     *     @Column("Name") String name = "Boris";
     *     @Column("Age") Integer age = 18;
     * }}</pre>
     * <p>The method will return a list with the strings: "Boris", and "18".
     *
     * @param object the model instance to parse
     * @return a list with every column value
     * @implNote The method will only parse fields annotated with {@link Column}.
     */
    private List<String> getObjectValuesList(M object) {
        List<String> values = new ArrayList<>();

        for (Field field : getTableFields(object)) {
            field.setAccessible(true);
            values.add(parseFieldToString(field, object));
        }

        return values;
    }

    /**
     * <p>Parse the given object and get a {@link List}.
     * <p>The list consists of every data-member database column name of the given object.
     * <br><p>For the given class:
     * <pre>{@code class Animal {
     *     @Column("Name") String name = "Boris";
     *     @Column("Age") int age = 18;
     * }}</pre>
     * <p>The method will return a list with the strings: "Name", and "Age".
     *
     * @param object the model instance to parse
     * @return a list with every column name
     * @implNote The method will only parse fields annotated with {@link Column}.
     */
    private List<String> getObjectColumnList(M object) {
        List<String> columns = new ArrayList<>();

        for (Field field : getTableFields(object)) {
            field.setAccessible(true);
            Column column = field.getAnnotation(Column.class);

            columns.add("\"" + column.value() + "\"");
        }

        return columns;
    }

    private Field[] getTableFields(M object) {
        Field[] classFields = getAllFields(new LinkedList<>(), object.getClass()).toArray(new Field[0]);
        Table table = getAnnotationIfNotNull(Table.class);
        return stream(classFields).filter(field -> {
            Column column = field.getAnnotation(Column.class);

            // If field is not annotated with @Column
            if (column == null) {
                return false;
            }

            // If field can be inserted as null
            if (column.nullable()) {
                return true;
            }

            // If the field can be processed to a SQL value AND
            // is not an auto-generated id column
            return parseFieldToString(field, object) != null ||
                    "id".equalsIgnoreCase(column.value()) && !table.isIdGenerated();
        }).toArray(Field[]::new);
    }

    /**
     * Select all rows in {@link M}'s table.
     *
     * @return a list with every object in the table.
     */
    @Synchronized
    protected List<M> selectAll() {
        String table = getAnnotationIfNotNull(Table.class).name();
        return selectAll("""
                SELECT * FROM %s
                """.formatted(table));
    }

    /**
     * Select all rows matching the given {@code filters} in {@link M}'s table.
     *
     * @param filters the filters to apply in the SELECT statement.
     * @return a list
     */
    @Synchronized
    protected List<M> selectAll(Map<String, Object> filters) {
        String table = getAnnotationIfNotNull(Table.class).name();
        String andClauses = createANDClauses(filters);
        return selectAll("""
                SELECT * FROM %s
                WHERE (%s)
                """.formatted(table, andClauses));
    }

    /**
     * Performs the given SQL string as an attempt to select multiple rows.
     *
     * @param sql the sql to perform
     * @return {@link List} selected objects
     */
    @Synchronized
    @SneakyThrows(SQLException.class)
    protected List<M> selectAll(String sql) {
        @Cleanup Connection conn = getConnection();

        Statement select = conn.createStatement();
        return mapAll(select.executeQuery(sql));
    }

    /**
     * Performs a select operation built with the given {@code filters}.
     *
     * @param filters the filters to apply
     * @return {@link Optional} selected object
     */
    @Synchronized
    protected Optional<M> selectOne(Map<String, Object> filters) {
        String table = getAnnotationIfNotNull(Table.class).name();
        String andClauses = createANDClauses(filters);

        return selectOne("""
                SELECT * FROM %s
                WHERE (%s)
                """.formatted(table, andClauses));
    }

    /**
     * Performs the given SQL string as an attempt to select a single row.
     *
     * @param sql the sql to perform
     * @return {@link Optional} selected object
     */
    @Synchronized
    @SneakyThrows(SQLException.class)
    protected Optional<M> selectOne(String sql) {
        @Cleanup Connection conn = getConnection();

        Statement select = conn.createStatement();
        return mapOne(select.executeQuery(sql));
    }

    /**
     * Updates the given model, <b>all fields</b>
     *
     * @param model   the model to update
     * @param filters the filters required to identify the right database row
     * @return the number of affected rows
     */
    @Synchronized
    @SneakyThrows
    protected Integer update(M model, Map<String, Object> filters) {
        @Cleanup Connection conn = getConnection();

        String table = getAnnotationIfNotNull(Table.class).name();
        String setClauses = stream(getAllFields(new LinkedList<>(), model.getClass()).toArray(new Field[0]))
                .filter(field -> field.getAnnotation(Column.class) != null &&
                        !"id".equals(field.getAnnotation(Column.class).value().toLowerCase()))
                .map(field -> {
                    field.setAccessible(true);
                    String columnName = field.getAnnotation(Column.class).value();
                    return columnName + "=" + parseFieldToString(field, model);
                })
                .collect(joining(","));
        String andClauses = createANDClauses(filters);

        Statement update = conn.createStatement();
        return update.executeUpdate("""
                UPDATE %s
                SET %s
                WHERE (%s)
                """.formatted(table, setClauses, andClauses));
    }

    @Synchronized
    protected Integer updateAndRollback(M model, Map<String, Object> filters) {
        selectOne(filters).ifPresent(m -> CleanupService.stack(() -> update(m, filters)));
        return update(model, filters);
    }

    /**
     * Updates a single column.
     *
     * @param filters the filters required to identify the right database row
     * @param column  the column name
     * @param value   the desired value
     * @return the number of affected rows
     */
    @Synchronized
    @SneakyThrows
    protected Integer update(Map<String, Object> filters, String column, Object value) {
        @Cleanup Connection conn = getConnection();

        String table = getAnnotationIfNotNull(Table.class).name();

        String andClauses = createANDClauses(filters);

        Statement update = conn.createStatement();
        return update.executeUpdate("""
                UPDATE %s
                SET %s = %s
                WHERE (%s)
                """.formatted(table, column, makeSQLStringFromObject(value, type), andClauses));
    }

    @Synchronized
    protected Integer updateAndRollback(Map<String, Object> filters, String column, Object value) {
        selectOne(filters).ifPresent(m -> CleanupService.stack(() -> update(m, filters)));
        return update(filters, column, value);
    }

    @Synchronized
    @SneakyThrows(SQLException.class)
    protected void delete(Map<String, Object> filters) {
        log.info("Delete: (%s) - (%s)".formatted(type.getSimpleName(),
                Joiner.on(",").withKeyValueSeparator("=").join(filters)));

        @Cleanup Connection conn = getConnection();

        String table = getAnnotationIfNotNull(Table.class).name();
        String andClauses = createANDClauses(filters);

        Statement select = conn.createStatement();

        select.execute("""
                DELETE FROM %s
                WHERE (%s)
                """.formatted(table, andClauses));
    }

    protected List<M> mapAll(ResultSet resultSet) {
        return map(resultSet, type);
    }

    protected Optional<M> mapOne(ResultSet results) {
        List<M> objects = mapAll(results);

        if (objects.size() == 0) {
            return Optional.empty();
        }

        if (objects.size() > 1) {
            log.warn("More than 1 result in ResultSet");
        }

        return Optional.of(objects.get(0));
    }

    private String createANDClauses(Map<String, Object> filters) {
        return filters.entrySet()
                .stream()
                // return key=value
                // If it's a String, add parentheses. This is the only special handling done.
                .map((entry) -> {
                    if (entry.getValue() == null || IS_NULL.equals(entry.getValue())) {
                        return entry.getKey() + " " + IS_NULL;
                    } else if (IS_NOT_NULL.equals(entry.getValue())) {
                        return entry.getKey() + " " + IS_NOT_NULL;
                    }

                    return entry.getKey() + "=" + makeSQLStringFromObject(entry.getValue(), type);
                })
                .collect(joining(" AND "));
    }

    protected <A extends Annotation> A getAnnotationIfNotNull(Class<A> annotationClass) {
        A annotation = type.getAnnotation(annotationClass);

        if (annotation == null) {
            throw new IllegalArgumentException("The class %s is not annotated with %s".formatted(type.getSimpleName(),
                    annotationClass.getSimpleName()));
        }

        return annotation;
    }

    /**
     * Maps the given {@link ResultSet} to an ArrayList
     *
     * @param set   the given ResultSet from a query
     * @param clazz the type of the ArrayList
     * @return the populated list
     * @deprecated use {@link #mapAll(ResultSet)} or {@link #mapOne(ResultSet)}
     */
    @Synchronized
    @SneakyThrows
    @Deprecated(forRemoval = true)
    protected static <T> List<T> map(ResultSet set, Class<T> clazz) {
        List<T> objects = new ArrayList<>();

        while (set.next()) {
            // Create an instance of the given type
            T dto = clazz.getConstructor().newInstance();

            for (Field field : getAllFields(new LinkedList<>(), clazz)) {
                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);

                if (column != null) {
                    String value = set.getString(column.value());

                    field.set(dto, parseStringToField(field, value));
                }
            }

            objects.add(dto);
        }

        return objects;
    }

    /**
     * <p>Parse the given {@code value} to the format of the given {@code field}'s type.
     * <p>This method defines custom parsing for different field types.
     * <p>Example:
     * <p>Given a {@link LocalDate} field, and the String "2020-05-01",
     * <p>the method would return a LocalDate object created from the given date String.
     * <p><u>Important:</u>
     * <p>For non-explicitly defined classes, the method would attempt to call a
     * constructor with a String parameter.
     *
     * @param field the target field format
     * @param value the value to parse
     * @return the parsed object which can be assigned to the field
     */
    @SneakyThrows
    private static Object parseStringToField(Field field, String value) {
        // The value can be null, IDE inspection shows a false-positive
        if (value == null) {
            return null;
        }

        if (LocalDate.class.equals(field.getType())) {
            return LocalDate.parse(value);
        }

        if (LocalDateTime.class.equals(field.getType())) {

            if (value.matches(/*For Instance 2022-02-08 12:04:12+02*/"^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30))) [0-9]{2}:[0-9]{2}:[0-9]{2}\\+[0-9]{2}")) {

                return parse(value, ofPattern("yyyy-MM-dd' 'HH:mm:ss[XX`X][X]")).toLocalDateTime();
            } else if (value.matches(/*For Instance 2022-02-08 12:04:12.3+02*/"^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30))) [0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]\\+[0-9]{2}")) {

                return parse(value, ofPattern("yyyy-MM-dd' 'HH:mm:ss.S[XXX][X]")).toLocalDateTime();
            } else if (value.matches(/*For Instance 2022-02-08 12:04:12.35+02*/"^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30))) [0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{2}\\+[0-9]{2}")) {

                return parse(value, ofPattern("yyyy-MM-dd' 'HH:mm:ss.SS[XXX][X]")).toLocalDateTime();
            } else if (value.matches(/*For Instance 2022-02-08 12:04:12.354+02*/"^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30))) [0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{3}\\+[0-9]{2}")) {

                return parse(value, ofPattern("yyyy-MM-dd' 'HH:mm:ss.SSS[XXX][X]")).toLocalDateTime();
            } else if (value.matches(/*For Instance 2022-02-08 12:04:12.35*/"^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30))) [0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{1,3}")) {

                return LocalDateTime.parse(value.replace(" ", "T"));
            } else {

                throw new IllegalArgumentException("Unknown date format %s".formatted(value));
            }
        }

        if (Integer.class.equals(field.getType())) {
            return Integer.valueOf(value);
        }

        if (UUID.class.equals(field.getType())) {
            return UUID.fromString(value);
        }

        if (Boolean.class.equals(field.getType())) {
            if (value.equals("t") || value.equals("f")) {
                return "t".equals(value);
            } else {
                return "1".equals(value);
            }
        }

        return field.getType().getConstructor(String.class).newInstance(value);
    }

    /**
     * Create a SQL compatible String from the given modelField and modelInstance.
     *
     * @param modelField    the model's field, used to get the field's value from the model instance
     * @param modelInstance the model instance
     * @return the SQL compatible String
     */
    @Synchronized
    @SneakyThrows(IllegalAccessException.class)
    private static String parseFieldToString(Field modelField, Object modelInstance) {
        modelField.setAccessible(true);

        Object fieldValue = modelField.get(modelInstance);

        if (fieldValue != null) {
            return makeSQLStringFromObject(fieldValue, modelInstance.getClass());
        }

        return null;

    }

    /**
     * Returns a String representation of the given object, which can be used in a SQL operation.
     *
     * @param object the object to parse
     * @return the text representation
     */
    private static String makeSQLStringFromObject(@NonNull Object object, Class<?> model) {
        Class<?> clazz = object.getClass();

        if (String.class.equals(clazz)) {
            return "N'" + String.join("'", object.toString().split("(?=')")) + "'";
        }

        if (LocalDateTime.class.equals(clazz)) {
            String date = ((LocalDateTime) object).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            // Check if there are more than 3 milliseconds
            if (date.indexOf('.') + 4 < date.length()) {
                date = date.substring(0, date.indexOf('.') + 4);
            }
            return "'" + date + "'";
        }

        if (LocalDate.class.equals(clazz)) {
            return "'" + ((LocalDate) object).format(DateTimeFormatter.ISO_LOCAL_DATE) + "'";
        }

        if (Boolean.class.equals(clazz)) {
            if (model.getAnnotation(Database.class).syntax().equals("Postgresql")) {
                return String.valueOf(((boolean) object));
            } else {
                return String.valueOf(((boolean) object) ? 1 : 0);
            }
        }

        return object.toString();
    }

    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(this.getClass()).in(Scopes.SINGLETON);
    }
}
