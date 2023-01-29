package pages.newInterview;

import com.codeborne.selenide.Selenide;

import java.util.List;
import java.util.Random;

public class NewInterviewBL {
    private final NewInterviewPage page = Selenide.page(NewInterviewPage.class);
    Random random = new Random();

    List<String> topics = page.topicOptions().texts();
    public NewInterviewBL chooseRandomTopic() {
        page.topicsHeader().click();
        page.topicMenu().selectRadio(topics.get(random.nextInt(topics.size()-1)));
        Selenide.sleep(3000);
        return this;
    }
}
