package pages.deleteInterview;

import com.codeborne.selenide.SelenideElement;
import framework.annotations.locators.Find;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class DeleteInterviewPage {
    @Find(className = "Notification_blueButton__q0TEz")
    private SelenideElement deleteApproval;
}
