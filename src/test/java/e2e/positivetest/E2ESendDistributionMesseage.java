package e2e.positivetest;

import database.NotificationsDAL;
import framwork.general.DataFaker;
import framwork.testrunner.ClassLevelWebRunner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import pages.soliderDetails.SoliderDetailsBL;


@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2ESendDistributionMesseage {
    DataFaker dataFaker = new DataFaker();
    String soliderPersonalNumber = "8778535";
    String messageHeader = dataFaker.howIMetYourMotherCatchPhrase(20);
    String message = dataFaker.rickAndMorty(300);
    String userName = "ינוב סגל";
    String sourceId = "212753743";

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
    }

    @Test(description = "Ensuring distribution message has been sent", dependsOnMethods = "sendDistributionMessage")
    void ensureDistributionMessage() {
        new SoliderDetailsBL()
                .openDistributionMessageUpdate(userName)
                .ensureUpdateContent(messageHeader, message);
    }

    @Test (description = "Deleting distribution message from database according to source id", dependsOnMethods = "sendDistributionMessage")
    void deleteDistributionMessageFromDB() {
        new NotificationsDAL()
                .deleteByUser(sourceId);
    }
}
