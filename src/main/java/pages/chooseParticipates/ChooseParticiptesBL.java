package pages.chooseParticipates;

import com.codeborne.selenide.Selenide;
import pages.newDistributionMessage.NewDistributionMessageBL;

import java.util.Random;

public class ChooseParticiptesBL {
    private final ChooseParticiptesPage page =
            Selenide.page(ChooseParticiptesPage.class);
    Random random = new Random();

    public ChooseParticiptesBL chooseRandomSubunit(){
        page.subunitCheckboxCollection().get(random.nextInt
                (page.subunitCheckboxCollection().size()-1)).click();
        return this;
    }

    public NewDistributionMessageBL chooseSpecificSolider(String soliderPersonalNumber) {
        page.searchInput().val(soliderPersonalNumber);
        page.soliderCheckbox().click();
        page.chooseButton().click();
        return new NewDistributionMessageBL();
    }


}
