package framwork.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.impl.SelenidePageFactory;
import framwork.annotations.RootLocator;
import framwork.annotations.locators.Find;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Custom wrapper on top of {@link ElementsContainer}
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class UIElement extends ElementsContainer {
    /**
     * <p>Allow a {@link UIElement} to be initialized without {@link Find}.
     * <p>If the element has a wrapping {@link RootLocator}, find it from the self parameter context
     *
     * @param self the self context
     */
    @SneakyThrows
    protected UIElement(SelenideElement self) {
        RootLocator rootLocator = this.getClass().getAnnotation(RootLocator.class);
        if (rootLocator != null) {
            By locator = new Find.FindByBuilder.ByAncestor(rootLocator.value());
            self = self.find(locator);
        }

        Field selfField = ElementsContainer.class.getDeclaredField("self");
        selfField.setAccessible(true);
        selfField.set(this, self);

        Type[] types = getClass().getGenericInterfaces();

        new SelenidePageFactory().initElements(WebDriverRunner.driver(), (SearchContext) self, this, types);
    }

    /**
     * Get the root of this UIElement, a wrapper on top of field 'self'.
     */
    protected SelenideElement root() {
        return getSelf();
    }

    public UIElement should(Condition... conditions) {
        root().should(conditions);
        return this;
    }

    public final UIElement shouldBe(Condition... conditions) {
        return should(conditions);
    }

    public final UIElement shouldHave(Condition... conditions) {
        return should(conditions);
    }

    public UIElement shouldNot(Condition... conditions) {
        root().shouldNot(conditions);
        return this;
    }

    public final UIElement shouldNotBe(Condition... conditions) {
        return shouldNot(conditions);
    }

    public final UIElement shouldNotHave(Condition... conditions) {
        return shouldNot(conditions);
    }
}
