package pages.deleteInterview;

import com.codeborne.selenide.Selenide;
import pages.soliderDetails.SoliderDetailsBL;
import pages.uielement.Solider;

public class DeleteInterviewBL {
    private final DeleteInterviewPage page = Selenide.page(DeleteInterviewPage.class);

    public SoliderDetailsBL approveDelete(){
        page.deleteApproval().click();
        return new SoliderDetailsBL();
    }
}
