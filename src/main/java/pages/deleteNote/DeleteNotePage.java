package pages.deleteNote;

import com.codeborne.selenide.SelenideElement;
import framework.annotations.locators.Find;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class DeleteNotePage {

    @Find(className = "Notification_TextMobile__3N1PS")
    private SelenideElement headerText;

    @Find(className = "Notification_blueButton__q0TEz")
    private SelenideElement deleteApproval;
}
