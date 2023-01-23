package pages.newDistributionMessage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import framwork.annotations.locators.Find;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class NewDistributionMessagePage {

    @Find(className = "DistributionMail_topTitle__PwH5O")
    private SelenideElement topTitle;

    @Find(className = "DistributionMail_headerTextArea__2AfyA")
    private SelenideElement headerText;

    @Find(className = "DistributionMail_toText__2KXTl")
    private SelenideElement toButton;

    @Find(className = "DistributionMail_contentTextArea__3evpH")
    private SelenideElement textArea;

    @Find(id = "checkboxHolder")
    private ElementsCollection messageSendMethods;

    @Find(text = "שליחה")
    private SelenideElement sendButton;

    @Find(className = "Notification_TextMobile__3N1PS")
    private SelenideElement areSureText;

    @Find(text = "אישור")
    private SelenideElement areSureConfirmButton;

    @Find(className = "DistributionMail_XIcon__3_f-c")
    private SelenideElement exitButton;

    @Find(className = "Notification_TextMobile__3N1PS")
    private SelenideElement popupConfirmText;

    @Find(className = "Notification_XIcon__1EBBv")
    private SelenideElement popupExitButton;
}
