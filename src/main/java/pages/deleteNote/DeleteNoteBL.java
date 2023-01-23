package pages.deleteNote;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import pages.soliderDetails.SoliderDetailsBL;

public class DeleteNoteBL {
    private final DeleteNotePage page = Selenide.page(DeleteNotePage.class);

    public SoliderDetailsBL approveDelete(){
        page.headerText().should(Condition.text("האם למחוק את ההערה"));
        page.yesButton().click();
        return new SoliderDetailsBL();
    }
}
