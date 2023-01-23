package framwork.annotations.database;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to map a class to it's respective database-table.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
    String name();

    /**
     * <p>Is this table's 'Id' row is auto-generated?
     * default: false
     */
    boolean isIdGenerated() default false;

    Class<?> idType() default Object.class;

    String idFieldName() default "id";
}
