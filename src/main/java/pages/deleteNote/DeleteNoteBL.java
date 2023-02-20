package pages.deleteNote;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import pages.soliderDetails.SoliderDetailsBL;

public class DeleteNoteBL {
    private final DeleteNotePage page = Selenide.page(DeleteNotePage.class);

    public SoliderDetailsBL approveDelete(){
        page.deleteApproval().click();
        return new SoliderDetailsBL();
    }
}
