package pages.newInterview;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import framework.annotations.locators.Find;
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
    private SelenideElement interviewContent;

    @Find(className = "ExpandedInterview_conclusionBoxDesktop__3Zh3V")
    private SelenideElement interviewConclusion;

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

    @Find(css = ".ExpandedInterview_contentBoxDesktop__1D2X1 + div .ExpandedInterview_errorMessage__2Za1k")
    private SelenideElement bodyErrorMessage;

    @Find(css = ".ExpandedInterview_conclusionBoxDesktop__3Zh3V + div .ExpandedInterview_errorMessage__2Za1k")
    private SelenideElement conclusionErrorMessage;

    @Find(css = ".ExpandedInterview_contentBoxDesktop__1D2X1 + div :nth-last-child(1)")
    private SelenideElement bodyCharsCount;

    @Find(css = ".ExpandedInterview_conclusionBoxDesktop__3Zh3V + div :nth-last-child(1)")
    private SelenideElement conclusionCharsCount;
}
