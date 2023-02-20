package e2e.positivetest;

import database.ActionsDAL;
import database.NotificationsDAL;
import framework.general.DataFaker;
import framework.testrunner.ClassLevelWebRunner;
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
        System.out.println("send");
    }

    @Test(description = "Ensuring distribution message has been sent", dependsOnMethods = "sendDistributionMessage", priority = 1)
    void ensureDistributionMessage() {
        System.out.println("ensure");
        new SoliderDetailsBL()
                .openDistributionMessageUpdate(userName)
                .ensureUpdateContent(messageHeader, message)
                .exitUpdate()
                .closeDistributionMessageUpdate();
    }

    @Test (description = "Deleting distribution message from database by source id", dependsOnMethods = "sendDistributionMessage", priority = 2)
    void deleteDistributionMessageFromDB() {
        System.out.println("delete");
        new SoliderDetailsBL()
                .openDistributionMessageUpdate(userName)
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
