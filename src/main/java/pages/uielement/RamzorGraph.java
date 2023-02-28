package pages.uielement;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import framework.annotations.RootLocator;
import framework.annotations.locators.Find;
import framework.elements.UIElement;
import pages.attendanceInfo.AttendanceInfoBL;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static pages.attendanceInfo.Consts.COMMANDER_CHOSEN_RECOMMENDATION;
import static pages.attendanceInfo.Consts.SYSTEM_CHOSEN_RECOMMENDATION;

@RootLocator(".RamzorGraph_wrapper__2uabD")
public class RamzorGraph extends UIElement {
    private String attendanceInfoValue;
    public static String chosenRecommendationValue;
    private boolean isCommanderGrade;
    Random random = new Random();

    @Find(className = "RamzorGraph_chosen__g6G8i")
    private SelenideElement chosenRecommendation;

    @Find(css = ".RamzorGraph_container__3ze49:not(.RamzorGraph_chosen__g6G8i)")
    private ElementsCollection notChosenRecommendation;

    public RamzorGraph(SelenideElement self, String attendanceInfoValue){
        super(self);
        this.attendanceInfoValue = attendanceInfoValue;
    }

    public RamzorGraph(SelenideElement self){
        super(self);
    }

    public AttendanceInfoBL checkSystemRecommendation() {
            chosenRecommendation.shouldHave(text(attendanceInfoValue));
        return new AttendanceInfoBL();
    }

    public AttendanceInfoBL chooseRandomRecommendation(){
        notChosenRecommendation.get(random.nextInt(2)).click();
        chosenRecommendationValue = chosenRecommendation.text();
        return new AttendanceInfoBL();
    }
}
