package pages.newInterview;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import framwork.annotations.locators.Find;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class NewInterviewPage {
    @Find(className = "ExpandedInterview_topTopic__2Fwes")
    private SelenideElement topicsHeader;

    @Find(css = "select.ExpandedInterview_topicInput__2j2YP")
    private SelenideElement topicSelect;

    @Find(css = ".ExpandedInterview_topicInput__2j2YP > *:not([disabled])")
    private ElementsCollection topicOptions;

    @Find(className = "ExpandedInterview_contentBoxDesktop__1D2X1")
    private SelenideElement interviewBodyText;

    @Find(className = "ExpandedInterview_conclusionBoxDesktop__3Zh3V")
    private SelenideElement interviewConclusionText;

    @Find(className = "ant-select-selection-item")
    private ElementsCollection dropDownListsofTiob;

    @Find(css = "#relativeSelect1 .ant-select-item.ant-select-item-option")
    private ElementsCollection chooseRecommendationChildren;

    @Find(css = "#relativeSelect2 .ant-select-item.ant-select-item-option")
    private ElementsCollection chooseResponseChildren;

    @Find(className = "ExpandedInterview_saveButton__1PHAE")
    private SelenideElement saveButton;

    @Find(className = "Notification_TextMobile__3N1PS")
    private SelenideElement savedText;

    @Find(className = "Notification_whiteButton__d2POB")
    private SelenideElement thankyouButton;

    @Find(className = "ExpandedInterview_closeIcon__2ciaz")
    private SelenideElement exitButton;

}
