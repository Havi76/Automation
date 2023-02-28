package pages.datePicker;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import pages.commendNotes.AddCommanderNoteBL;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;

public class DatePickerBL {
    private final DatePickerPage page =
            Selenide.page(DatePickerPage.class);
    Random random = new Random();

    public AddCommanderNoteBL chooseRandomDate(){
        page.enabledDays().get(random.nextInt
                (page.enabledDays().size()-1)).click();
        return new AddCommanderNoteBL();
    }

    public DatePickerBL checkDatesLimitations(){
        checkFirstFriday();
        checkLastSaturday();
        checkToday();
        return this;
    }

    public void checkFirstFriday(){
        page.allDays().get(5).shouldHave(cssClass("DatePickerCalendar_disabledCell__3F9L-"));
    }

    public void checkLastSaturday(){
        page.allDays().get(34).shouldHave(cssClass("DatePickerCalendar_disabledCell__3F9L-"));
    }

    public void checkToday(){
        page.todayCell().shouldBe(exist);
    }
}

