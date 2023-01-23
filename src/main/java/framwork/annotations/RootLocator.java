package framwork.annotations;

import com.codeborne.selenide.ElementsContainer;
import framwork.elements.UIElement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>The root of an html element (component) hierarchy
 * <p>Every class extending {@link UIElement} should be annotated with @{@link RootLocator} in the following format:
 * <p>Consider the following element structure:
 * <pre>{@code <div class="text-field">
 *     <input type="text"/>
 * </div>
 * }</pre>
 * <p>This element is an example of a {@link UIElement}, it's a wrapper on top of Selenide's {@link ElementsContainer}.
 * <p>The {@link #value()} in this example is the class attribute of the div element.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RootLocator {
    /**
     * A class name or tag name to uniquely identify each type of {@link UIElement}
     *
     * @implNote CLASSNAME MUST BE PREFIXED WITH '.'
     */
    String value() default "";
}
