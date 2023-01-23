package framwork.elements.widgets;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TextField {

    public static void clear(SelenideElement input) {
        int length = input.val().length();

        List<CharSequence> sequences = new ArrayList<>();
        IntStream.range(0, length).forEach(index -> sequences.add(Keys.BACK_SPACE));

        input.sendKeys(sequences.toArray(new CharSequence[0]));
    }
}
