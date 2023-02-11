package api.positivetest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

@Epic("פעולות ומשימות")
@Feature("שליחת הודעת תפוצה")
@Story("יצירת פעולת הודעת תפוצה")
@Severity(CRITICAL)
@Test(groups = {"API positive test"}, suiteName = "api", description = "API for distribution mail")
public class APISendDistributionMail {
    final String BASE_URL = "https://mefakdim-backend.azurewebsites.net";
    final String REQUEST_URN = "/notifications/sendDistributionMail";
    final int STATUS_CODE = 200;

    @Test
    void sendDistributionMail(){
        baseURI = BASE_URL;
        Response response = given().log().all().contentType(ContentType.JSON)
                .body(new File("src/test/java/api/jsonsfile/SendDistributionMail.json"))
                .header("isbypasslogin", "true").post(REQUEST_URN);
        response.then().statusCode(STATUS_CODE);
        response.prettyPeek(); // הדפסה
    }
}
