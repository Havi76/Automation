package e2e.positivetest;

import database.ActionsDAL;
import database.NotificationsDAL;
import framwork.general.DataFaker;
import framwork.testrunner.ClassLevelWebRunner;
import org.junit.jupiter.api.Order;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import pages.soliderDetails.SoliderDetailsBL;

import static pages.newDistributionMessage.NewDistributionMessageBL.actionId;


@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2EDistributionMessage {
    DataFaker dataFaker = new DataFaker();
    String soliderPersonalNumber = "8778535";
    String messageHeader = dataFaker.howIMetYourMotherCatchPhrase(20);
    String message = dataFaker.rickAndMorty(300);
    String userName = "ינוב סגל";

    @Test(description = "Sending distribute message")
    @Order(1)
    void sendDistributionMessage() {
        new MenuBL()
                .clickOnActionsMenu()
                .nevigateToDistributionMessageAction()
                .sendHeader(messageHeader)
                .clickOnChooseParticipates() //בוחר את עצמי בשביל לוודא שקיבלתי מייל והודעה
                .chooseRandomSubunit()
                .chooseSpecificSolider(soliderPersonalNumber)
                .valMessage(message)
                .clickOnSendMethods()
                .pressSendButton()
                .confirmSendMessage()
                .exitConfirmPage();
    }

    @Test(description = "Ensuring distribution message has been sent", dependsOnMethods = "sendDistributionMessage")
    @Order(2)
    void ensureDistributionMessage() {
        new SoliderDetailsBL()
                .openDistributionMessageUpdate(userName)
                .ensureUpdateContent(messageHeader, message)
                .exitUpdate()
                .closeDistributionMessageUpdate();
    }

    @Test (description = "Deleting distribution message from database by source id", dependsOnMethods = "sendDistributionMessage")
    @Order(3)
    void deleteDistributionMessageFromDB() {
        new SoliderDetailsBL()
                .openDistributionMessageUpdate(userName)
                .ensureUpdateContent(messageHeader, message)
                .getActionId()
                .exitUpdate()
                .closeDistributionMessageUpdate();
        System.out.println(actionId);
        new NotificationsDAL()
                .deleteNotificationByActionId(actionId);
        new ActionsDAL()
                .deleteActionByActionId(actionId);
    }
}
