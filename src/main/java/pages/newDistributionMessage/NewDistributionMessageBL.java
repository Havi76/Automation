package pages.newDistributionMessage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import database.NotificationsDAL;
import pages.chooseParticipates.ChooseParticiptesBL;
import pages.soliderDetails.SoliderDetailsBL;

import java.time.Duration;
import java.util.Random;

public class NewDistributionMessageBL {
    private final NewDistributionMessagePage page =
            Selenide.page(NewDistributionMessagePage.class);
    Random random = new Random();

    public NewDistributionMessageBL sendHeader(String headerText) {
        page.topTitle().should(Condition.text("הודעת תפוצה"));
        page.headerText().clear();
        page.headerText().val(headerText);
        return this;
    }

    public ChooseParticiptesBL clickOnChooseParticipates() {
        page.toButton().click();
        return new ChooseParticiptesBL();
    }

    public NewDistributionMessageBL valMessage(String message) {
        page.textArea().clear();
        page.textArea().val(message);
        return this;
    }

    public NewDistributionMessageBL clickOnSendMethods(){
        int sms = 0;
        int email = 1;
        page.messageSendMethods().get(sms).click();
        page.messageSendMethods().get(email).click();
        return this;
    }

    public NewDistributionMessageBL pressSendButton() {
        page.sendButton().click();
        return this;
    }

    public NewDistributionMessageBL confirmSendMessage() {
        page.areSureText().should(Condition.text("האם ברצונך לשלוח את ההודעה?"));
        page.areSureConfirmButton().click();
        return this;
    }

    public NotificationsDAL ensureUpdateContent(String header, String message){
        page.headerText().should(Condition.text(header));
        page.textArea().should(Condition.text(message));
        page.exitButton().click();
        return new NotificationsDAL();
    }

    public SoliderDetailsBL exitConfirmPage() {
        page.popupConfirmText().should(Condition.text("הודעתך נשלחה בהצלחה"), Duration.ofSeconds(15));
        page.popupExitButton().click();
        return new SoliderDetailsBL();
    }
}


