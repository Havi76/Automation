package framwork.annotations.locators;

import framwork.annotations.locators.support.CustomFindByBuilder;
import framwork.elements.UIElement;
import framwork.annotations.RootLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactoryFinder;
import org.openqa.selenium.support.pagefactory.ByChained;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * <p>An alternative to Selenium's {@link FindBy}.
 * <p>This class allows adding / customizing locating mechanisms, such as {@link #text()}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@PageFactoryFinder(Find.FindByBuilder.class)
public @interface Find {
    /**
     * Finds elements by 'id' attribute
     */
    String id() default "";

    /**
     * Finds elements by 'name' attribute
     */
    String name() default "";

    /**
     * Finds elements bu one 'class' attribute value
     */
    String className() default "";

    /**
     * Finds elements matching the given CSS Selector
     */
    String css() default "";

    /**
     * Finds elements with the given tag name
     */
    String tagName() default "";

    /**
     * Finds links with matching text values
     */
    String linkText() default "";

    /**
     * Finds links containing the given text values
     */
    String partialLinkText() default "";

    /**
     * Finds elements whose 'attribute' matches the given 'value'
     */
    String attribute() default "";

    /**
     * Finds elements by 'value' attribute
     */
    String attributeValue() default "";

    /**
     * Finds elements matching the given XPath
     */
    String xpath() default "";

    /**
     * Finds elements containing the given text
     */
    String text() default "";

    /**
     * Finds elements matching the given text
     */
    String exactText() default "";

    /**
     * Finds elements matching the given title
     */
    String title() default "";

    class FindByBuilder extends CustomFindByBuilder {
        /**
         * <p>Create the {@link By} locator from the given annotation.
         * <p>This method does special locator-building for {@link UIElement}s.
         *
         * @param annotation the annotation to parse
         * @param field      the annotation's respective field
         * @return an object locator
         */
        public By buildIt(Object annotation, Field field) {
            Find findBy = (Find) annotation;
            assertValidFind(findBy);

            // Build a normal locator
            By locator = buildByFromShortFindBy(findBy);

            // Get the field's type (Or the List's generic type)
            Class<?> type = field.getType();
            if (List.class.isAssignableFrom(type)) {
                type = getListGenericType(field);

                // If the list does not have a generic type
                if (type == null) {
                    return locator;
                }
            }

            // If the type is subclass of UIElement
            if (UIElement.class.isAssignableFrom(type)) {
                String classOrTagName;

                RootLocator rootLocator = type.getAnnotation(RootLocator.class);
                if (rootLocator != null) {
                    classOrTagName = rootLocator.value();
                } else {
                    throw new IllegalStateException(type + " must be annotated with " + RootLocator.class.getSimpleName());
                }

                locator = new ByChained(locator, new ByAncestor(classOrTagName));
            }

            return locator;
        }

        @CheckReturnValue
        @Nullable
        private Class<?> getListGenericType(Field field) {
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) return null;

            return (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
        }

        /**
         * A customized {@link ByXPath} used to locate an ancestor-or-self of a calling element.
         */
        public static class ByAncestor extends By.ByXPath implements Serializable {
            private static final long serialVersionUID = -6727228887685051584L;

            private final String classOrTagName;

            public ByAncestor(String classOrTagName) {
                super((classOrTagName.startsWith("."))
                        ? "ancestor-or-self::*[contains(concat(' ', normalize-space(@class), ' '), ' " + classOrTagName.substring(1) + " ')][1]"
                        : "ancestor-or-self::" + classOrTagName + "[1]");

                this.classOrTagName = classOrTagName;
            }

            @Override
            public String toString() {
                return "By.ancestor: " + classOrTagName;
            }
        }
    }
}
