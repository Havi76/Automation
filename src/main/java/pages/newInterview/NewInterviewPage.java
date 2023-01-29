package pages.newInterview;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import framwork.annotations.locators.Find;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class NewInterviewPage {
    @Find(className = "Interviews_topTopicDesktop__RradG")
    private SelenideElement topicsHeader;

    @Find(css = "select.Interviews_topicInput__34FEw")
    private SelenideElement topicMenu;

    @Find(css = ".Interviews_topTopic__3cwXw." +
            "Interviews_topTopicDesktop__RradG > * :not([disabled])")
    private ElementsCollection topicOptions;
}
