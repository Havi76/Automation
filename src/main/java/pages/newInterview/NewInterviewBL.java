package pages.newInterview;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import framwork.general.DataFaker;
import pages.soliderDetails.SoliderDetailsBL;
import consts.Consts.*;


import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static consts.Consts.BODYTEXT;

public class NewInterviewBL {
    private final NewInterviewPage page = Selenide.page(NewInterviewPage.class);
    Random random = new Random();
    DataFaker dataFaker = new DataFaker();
    public static String topic;
    public static Boolean interviewFoundFlag = false;
    public static String bodyText;

    public NewInterviewBL chooseRandomTopic() {
        page.topicsHeader().click();
        List<String> topics = page.topicOptions().texts();
        topic = topics.get(random.nextInt(topics.size()));
        page.topicSelect().selectOption(topic);
        return this;
    }

    public SoliderDetailsBL fillInterview(){
//        bodyText = dataFaker.howIMetYourMotherQuote(500);
        bodyText = "I realized that I’m searching, searching for what I really want in life. And you know what? I have absolutely no idea what that is.";
        page.interviewBodyText().val(bodyText);
        page.interviewConclusionText().val(dataFaker.gameOfThrones(200));
        if (topic.equals("טיוב סד\"כ")) {
            page.dropDownListsofTiob().get(0).click();
            page.chooseRecommendationChildren().get(random.nextInt(3)).click();
            page.dropDownListsofTiob().get(1).click();
            page.chooseResponseChildren().get(random.nextInt(3)).click();
        }
        page.saveButton().shouldBe(Condition.enabled).click();
        page.savedText().should(exist);
        page.thankyouButton().click();
        return new SoliderDetailsBL();
    }

    public void checkBodyText(){
        if (bodyText == null)
            bodyText = BODYTEXT;
        if (page.interviewBodyText().should(visible, enabled).text().equals(bodyText)) {
            interviewFoundFlag = true;
        }
        page.exitButton().shouldBe(enabled, visible).click();
    }
}
