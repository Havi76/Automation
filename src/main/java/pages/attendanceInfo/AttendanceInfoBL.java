package pages.attendanceInfo;

import com.codeborne.selenide.Selenide;
import pages.soliderDetails.SoliderDetailsBL;
import pages.uielement.RamzorGraph;
import pages.uielement.Solider;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static pages.attendanceInfo.Consts.*;

public class AttendanceInfoBL {
    private final AttendanceInfoPage page =
            Selenide.page(AttendanceInfoPage.class);
    Random random = new Random();
    String attendanceInfoValue;
    Boolean isCommanderGrade;

    public AttendanceInfoBL(){
    }

    public AttendanceInfoBL(String attendanceInfoValue, boolean isCommanderGrade){
        this.attendanceInfoValue = attendanceInfoValue;
        this.isCommanderGrade = isCommanderGrade;
    }

    public RamzorGraph findRamzorGraph(){
        int ramzorGraphIndex;
        if(isCommanderGrade)
            ramzorGraphIndex = COMMANDER_CHOSEN_RECOMMENDATION;
        else
            ramzorGraphIndex = SYSTEM_CHOSEN_RECOMMENDATION;
        return new RamzorGraph(page.ramzorGraphsWrappers().get(ramzorGraphIndex),
                this.attendanceInfoValue);
    }

    public AttendanceInfoBL chooseCommanderRandomRecommendation(){
        new RamzorGraph(page.ramzorGraphsWrappers().get(COMMANDER_CHOSEN_RECOMMENDATION)).chooseRandomRecommendation();
        return this;
    }

    public AttendanceInfoBL chooseRandomReason() {
        page.dropDownList().click();
        page.dropDownListOptions().get(random.nextInt(page.dropDownListOptions().size())).click();
        return this;
    }

    public AttendanceInfoBL saveAttendanceInfo() {
        page.saveButton().shouldBe(exist, visible).click();
        return this;
    }

    public SoliderDetailsBL exitApproval() {
        page.approvalText().shouldHave(text("הציון נשמר בהצלחה"));
        page.approvalExitButton().click();
        return new SoliderDetailsBL();
    }
}
