package pages.datePicker;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import pages.commendNotes.AddCommanderNoteBL;
import pages.commendNotes.AddCommanderNotePage;
import pages.datePicker.DatePickerPage;

import java.util.Random;

import static com.codeborne.selenide.Condition.exist;

public class DatePickerBL {
    private final DatePickerPage page =
            Selenide.page(DatePickerPage.class);
    Random random = new Random();

    public AddCommanderNoteBL chooseRandomDate(){
        page.enabledDates().get(random.nextInt
                (page.enabledDates().size()-1)).click();
        return new AddCommanderNoteBL();
    }

}
