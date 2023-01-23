package pages.actionsMenu;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import pages.newDistributionMessage.NewDistributionMessageBL;

public class ActionsMenuBL {
    private final ActionsMenuPage page = Selenide.page(ActionsMenuPage.class);
    String titleToClick = "כללי";
    String distributionMessageAction = "שליחת הודעת תפוצה";

    public NewDistributionMessageBL nevigateToDistributionMessageAction() {
        page.actionSearchBar().val(distributionMessageAction);
        page.actionsTitles().find(Condition.text(titleToClick)).click();
        page.actions().find(Condition.text(distributionMessageAction)).click();
        return new NewDistributionMessageBL();
    }
//
//    public x clickOnRecruimentButton() {
//        page.recruitmentButton().click();
//        return x;
//    }
//
//    public x clickOnMoveInSubunitButton() {
//        page.moveInSubunitButton().click();
//        return x;
//    }
//
//    public x clickOnChangeRoleButton() {
//        page.changeRoleButton().click();
//        return x;
//    }
//
//    public x clickOnRecruimentButton() {
//        page.recruitmentButton().click();
//        return x;
//    }
}
