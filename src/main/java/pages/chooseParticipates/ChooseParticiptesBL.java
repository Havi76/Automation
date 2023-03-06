package pages.chooseParticipates;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import pages.newDistributionMessage.NewDistributionMessageBL;

import java.util.Random;

public class ChooseParticiptesBL {
    private final ChooseParticiptesPage page =
            Selenide.page(ChooseParticiptesPage.class);
    Random random = new Random();

    @Step("Choose random subunit")
    public ChooseParticiptesBL chooseRandomSubunit(){
        page.subunitCheckboxCollection().get(random.nextInt
                (page.subunitCheckboxCollection().size())).click();
        return this;
    }

    @Step("Choose {0}")
    public NewDistributionMessageBL chooseSpecificSolider(String soliderPersonalNumber) {
        page.searchInput().val(soliderPersonalNumber);
        page.soliderCheckbox().click();
        page.chooseButton().click();
        return new NewDistributionMessageBL();
    }


}
