package pages.newDistributionMessage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import database.NotificationsDAL;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import pages.chooseParticipates.ChooseParticiptesBL;
import pages.soliderDetails.SoliderDetailsBL;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.text;

public class NewDistributionMessageBL {
    private final NewDistributionMessagePage page =
            Selenide.page(NewDistributionMessagePage.class);
    Random random = new Random();
    public static int actionId;

    @Step("Send Header: {0}")
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

    @Step("Send message: {0}")
    public NewDistributionMessageBL valMessage(String message) {
        page.textArea().clear();
        page.textArea().val(message);
        return this;
    }

    //לתקן
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

    public NewDistributionMessageBL ensureUpdateContent(String header, String message){
        page.headerText().should(Condition.text(header));
        page.textArea().should(Condition.text(message));
        return this;
    }

    public SoliderDetailsBL exitUpdate(){
        page.exitButton().click();
        return new SoliderDetailsBL();
    }

    public SoliderDetailsBL exitConfirmPage() {
        page.popupConfirmText().should(Condition.text("הודעתך נשלחה בהצלחה"), Duration.ofSeconds(15));
        page.popupExitButton().click();
        return new SoliderDetailsBL();
    }

    @SneakyThrows //אם לא הוא לא מצליח למשיך את הURL זרוק שגיאה
    public NewDistributionMessageBL getActionId() {
        URI uri = new URI(WebDriverRunner.getWebDriver().getCurrentUrl());
        List<NameValuePair> pair = URLEncodedUtils.parse(uri, StandardCharsets.UTF_8);
        actionId = Integer.parseInt(pair.get(0).getValue());
        return this;
    }
}


