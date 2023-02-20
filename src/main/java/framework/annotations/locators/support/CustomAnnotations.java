package framework.annotations.locators.support;

import framework.annotations.locators.Find;
import framework.annotations.locators.Finds;
import org.openqa.selenium.By;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class CustomAnnotations extends AbstractAnnotations {
    private final Field field;

    /**
     * @param field expected to be an element in a Page Object
     */
    public CustomAnnotations(Field field) {
        this.field = field;
    }

    /**
     * {@inheritDoc}
     *
     * @return true if @CacheLookup annotation exists on a field
     */
    public boolean isLookupCached() {
        return (field.getAnnotation(CacheLookup.class) != null);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Looks for one of {@link org.openqa.selenium.support.FindBy},
     * {@link org.openqa.selenium.support.FindBys} or
     * {@link org.openqa.selenium.support.FindAll} field annotations. In case
     * no annotations provided for field, uses field name as 'id' or 'name'.
     *
     * @throws IllegalArgumentException when more than one annotation on a field provided
     */
    public By buildBy() {
        assertValidAnnotations();

        By locator = null;

        for (Annotation annotation : field.getDeclaredAnnotations()) {
            AbstractFindByBuilder builder = null;
            if (annotation.annotationType().isAnnotationPresent(PageFactoryFinder.class)) {
                try {
                    builder = annotation.annotationType()
                            .getAnnotation(PageFactoryFinder.class).value().getDeclaredConstructor()
                            .newInstance();
                } catch (ReflectiveOperationException e) {
                    // Fall through.
                }
            }
            if (builder != null) {
                locator = builder.buildIt(annotation, field);
                break;
            }
        }

        if (locator == null) {
            locator = buildByFromDefault();
        }

        if (locator == null) {
            throw new IllegalArgumentException("Cannot determine how to locate element " + field);
        }

        return locator;
    }

    protected By buildByFromDefault() {
        return new ByIdOrName(field.getName());
    }

    protected void assertValidAnnotations() {
        FindBys findBys = field.getAnnotation(FindBys.class);
        framework.annotations.locators.FindAll findAll = field.getAnnotation(framework.annotations.locators.FindAll.class);
        FindBy findBy = field.getAnnotation(FindBy.class);
        Finds customFindBys = field.getAnnotation(Finds.class);
        framework.annotations.locators.FindAll customFindAll = field.getAnnotation(framework.annotations.locators.FindAll.class);
        Find customFindBy = field.getAnnotation(Find.class);

        if (findBys != null && findBy != null) {
            throw new IllegalArgumentException("If you use a '@FindBys' annotation, " +
                    "you must not also use a '@FindBy' annotation");
        }
        if (findAll != null && findBy != null) {
            throw new IllegalArgumentException("If you use a '@FindAll' annotation, " +
                    "you must not also use a '@FindBy' annotation");
        }
        if (findAll != null && findBys != null) {
            throw new IllegalArgumentException("If you use a '@FindAll' annotation, " +
                    "you must not also use a '@FindBys' annotation");
        }

        if (customFindBys != null && customFindBy != null) {
            throw new IllegalArgumentException("If you use a '@Finds' annotation, " +
                    "you must not also use a '@Find' annotation");
        }
        if (customFindAll != null && customFindBy != null) {
            throw new IllegalArgumentException("If you use a '@FindAll' annotation, " +
                    "you must not also use a '@Find' annotation");
        }
        if (customFindAll != null && customFindBys != null) {
            throw new IllegalArgumentException("If you use a '@FindAll' annotation, " +
                    "you must not also use a '@Finds' annotation");
        }
    }
}
