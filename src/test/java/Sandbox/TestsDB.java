package Sandbox;

import database.CommentsToSolidersDAL;
import models.CommentsToSoliders;
import org.testng.annotations.Test;

public class TestsDB {
    // "8122079", "212753743"
    @Test
    void DB() {
        CommentsToSolidersDAL.instance().deleteLast("8122079", "212753743");
    }
}
