package pages.newInterview;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import framework.elements.widgets.TextField;
import framework.general.DataFaker;
import pages.deleteNote.DeleteNoteBL;
import pages.soliderDetails.SoliderDetailsBL;

import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.*;

public class NewInterviewBL {
    String defualtInterviewContent = "d";
    private final NewInterviewPage page = Selenide.page(NewInterviewPage.class);
    Random random = new Random();
    DataFaker dataFaker = new DataFaker();
    public static String topic;
    public static Boolean interviewFoundFlag = false;
    public static String interviewContent;

    public String generateString(int size) {
        return new String(new char[size]).replace("\0","A");
    }

    public NewInterviewBL chooseRandomTopic() {
        page.topicsHeader().click();
        List<String> topics = page.topicOptions().texts();
        topic = topics.get(random.nextInt(topics.size()));
        page.topicSelect().selectOption(topic);
        return this;
    }

    public NewInterviewBL fillInterview(){
        fillInterviewContent(dataFaker.howIMetYourMotherQuote(500));
        fillInterviewConclusion(dataFaker.gameOfThrones(200));
        if (topic.equals("טיוב סד\"כ")) {
            page.dropDownListsofTiob().get(0).click();
            page.chooseRecommendationChildren().get(random.nextInt(3)).click();
            page.dropDownListsofTiob().get(1).click();
            page.chooseResponseChildren().get(random.nextInt(3)).click();
        }
        return this;
    }

    public SoliderDetailsBL saveInterview() {
        page.saveButton().shouldBe(Condition.enabled).click();
        page.savedText().should(exist);
        page.thankyouButton().click();
        return new SoliderDetailsBL();
    }

    public void checkInterviewContent(){
        if (interviewContent == null)
            interviewContent = defualtInterviewContent;
        if (page.interviewContent().should(visible, enabled).text().equals(interviewContent)) {
            interviewFoundFlag = true;
        }
        page.exitButton().shouldBe(enabled, visible).click();
    }

    public void fillInterviewContent(String interviewContent){
        page.interviewContent().val(interviewContent);
    }

    public void clearInterviewContent(){
        TextField.clear(page.interviewContent());
    }

    public void fillInterviewConclusion(String interviewConclusion){
        page.interviewConclusion().val(interviewConclusion);

    }

    public void clearInterviewConclusion(){
        TextField.clear(page.interviewConclusion());
    }

    public void checkSaveDisabled() {
        page.saveButton().shouldNotBe(enabled);
    }

    public void chooseRandomTopicWithoutLast() {
        page.topicsHeader().click();
        List<String> topics = page.topicOptions().texts();
        topic = topics.get(random.nextInt(topics.size()-1));
        page.topicSelect().selectOption(topic);
    }

    public void chooseLastTopic() {
        page.topicsHeader().click();
        page.topicSelect().selectOption(4);
    }

    public void chooseRandomRecommendation(){
        page.dropDownListsofTiob().get(0).click();
        page.chooseRecommendationChildren().get(random.nextInt(3)).click();
    }

    public void chooseRandomResponse(){
        page.dropDownListsofTiob().get(1).click();
        page.chooseResponseChildren().get(random.nextInt(3)).click();
    }

    public SoliderDetailsBL exitNewInterview(){
        page.exitButton().shouldBe(enabled, visible).click();
        new DeleteNoteBL().approveDelete();
        return new SoliderDetailsBL();
    }

    public NewInterviewBL negativeContentAndConclusion(String interviewContent, String interviewConclusion) {
        checkSaveDisabled();
        fillInterviewContent(interviewContent);
        page.bodyErrorMessage().shouldHave(text("עברת את מגבלת התווים המותרת"));
        page.bodyCharsCount().shouldHave(text("500/500"));
        checkSaveDisabled();
        fillInterviewConclusion(interviewConclusion);
        checkSaveDisabled();
        page.conclusionErrorMessage().shouldHave(text("עברת את מגבלת התווים המותרת"));
        page.conclusionCharsCount().shouldHave(text("200/200"));
        checkSaveDisabled();
        clearInterviewContent();
        checkSaveDisabled();
        clearInterviewConclusion();
        return this;
    }

    public NewInterviewBL negativeSimpleTopicsAndContentOrConclusion(String interviewContent, String interviewConclusion){
        chooseRandomTopicWithoutLast();
        fillInterviewContent(interviewContent);
        checkSaveDisabled();
        clearInterviewContent();
        fillInterviewConclusion(interviewConclusion);
        checkSaveDisabled();
        clearInterviewConclusion();
        return this;
    }

    public NewInterviewBL negativeComplicatedWithoutResponse(String interviewContent, String interviewConclusion){
        chooseLastTopic();
        fillInterviewContent(interviewContent);
        fillInterviewConclusion(interviewConclusion);
        chooseRandomRecommendation();
        checkSaveDisabled();
        return this;
    }

    public NewInterviewBL negativeComplicatedWithoutRecommendation(String interviewContent, String interviewConclusion){
        chooseLastTopic();
        fillInterviewContent(interviewContent);
        fillInterviewConclusion(interviewConclusion);
        chooseRandomResponse();
        checkSaveDisabled();
        return this;
    }

    public NewInterviewBL negativeComplicatedWithoutContentOrConclusion(String interviewContent, String interviewConclusion){
        chooseLastTopic();
        fillInterviewConclusion(interviewConclusion);
        chooseRandomRecommendation();
        chooseRandomResponse();
        checkSaveDisabled();
        clearInterviewConclusion();
        fillInterviewContent(interviewContent);
        checkSaveDisabled();
        return this;
    }
}
