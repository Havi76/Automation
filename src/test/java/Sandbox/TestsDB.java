package Sandbox;

import com.codeborne.selenide.WebDriverRunner;
import database.ActionsDAL;
import database.CommentsToSolidersDAL;
import database.InterviewsDAL;
import database.NotificationsDAL;
import models.CommentsToSoliders;
import org.testng.annotations.Test;
import pages.newInterview.NewInterviewBL;
@Test
public class TestsDB {
    // "8122079", "212753743"
    @Test
    void DB() {
        InterviewsDAL.instance().deleteLastInterviewByInterviewer("325525814");
//        NotificationsDAL.instance().deleteByUser("212753743");
//        ActionsDAL.instance().deleteByUser("212753743");
//        CommentsToSolidersDAL.instance().deleteLast("8122079", "212753743");
    }
}
