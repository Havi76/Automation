package e2e.positivetests;

import database.ActionsDAL;
import database.NotificationsDAL;
import framework.general.DataFaker;
import framework.testrunner.ClassLevelWebRunner;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import pages.soliderDetails.SoliderDetailsBL;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static pages.newDistributionMessage.NewDistributionMessageBL.actionId;
import static e2e.Consts.*;


@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2EDistributionMessage {
    DataFaker dataFaker = new DataFaker();
    String messageHeader = dataFaker.howIMetYourMotherCatchPhrase(20);
    String message = dataFaker.rickAndMorty(300);

    @Feature("הודעת תפוצה")
    @Story("שליחת הודעת תפוצה")
    @Severity(CRITICAL)
    @Test(description = "Sending distribute message")
    void sendDistributionMessage() {
        new MenuBL()
                .clickOnActionsMenu()
                .nevigateToDistributionMessageAction()
                .sendHeader(messageHeader)
                .clickOnChooseParticipates() //בוחר את עצמי בשביל לוודא שקיבלתי מייל והודעה
                .chooseRandomSubunit()
                .chooseSpecificSolider(SOLIDER_PERSONAL_NUMBER)
                .valMessage(message)
                .clickOnSendMethods()
                .pressSendButton()
                .confirmSendMessage()
                .exitConfirmPage();
    }

    @Test(description = "Ensuring distribution message has been sent", dependsOnMethods = "sendDistributionMessage", priority = 1)
    void ensureDistributionMessage() {
        new SoliderDetailsBL()
                .openDistributionMessageUpdate(USER_NAME)
                .ensureUpdateContent(messageHeader, message)
                .exitUpdate()
                .closeDistributionMessageUpdate();
    }

    @Test (description = "Deleting distribution message from database by source id", dependsOnMethods = "sendDistributionMessage", priority = 2)
    void deleteDistributionMessageFromDB() {
        new SoliderDetailsBL()
                .openDistributionMessageUpdate(USER_NAME)
                .ensureUpdateContent(messageHeader, message)
                .getActionId()
                .exitUpdate()
                .closeDistributionMessageUpdate();
        new NotificationsDAL()
                .deleteNotificationByActionId(actionId);
        new ActionsDAL()
                .deleteActionByActionId(actionId);
    }
}
