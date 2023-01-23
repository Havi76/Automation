package e2e.positivetest;

import com.codeborne.selenide.Selenide;
import framwork.general.DataFaker;
import framwork.testrunner.ClassLevelWebRunner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import pages.menu.MenuPage;
import pages.soliderDetails.SoliderDetailsBL;
import pages.topMenu.TopMenuBL;
import pages.topMenu.TopMenuPage;

@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2ESendDistributionMesseage {
    DataFaker dataFaker = new DataFaker();
    String soliderPersonalNumber = "8778535";
    String messageHeader = dataFaker.howIMetYourMotherCatchPhrase(20);
    String message = dataFaker.rickAndMorty(300);
    String author = "";
    String sourceId = "212753743";

    @Test
    void SendDistributionMessage() {
        author = new TopMenuBL().getName();
        new MenuBL()
                .clickOnActionsMenu()
                .nevigateToDistributionMessageAction()
                .sendHeader(messageHeader)
                .clickOnChooseParticipates()
//                .chooseRandomSubunit()
                .chooseSpecificSolider(soliderPersonalNumber)
                .valMessage(message)
                .clickOnSendMethods()
                .pressSendButton()
                .confirmSendMessage()
                .exitConfirmPage()
                .openDistributionMessageUpdate(author)
                .ensureUpdateContent(messageHeader, message)
                .deleteByUser(sourceId);
    }
}
