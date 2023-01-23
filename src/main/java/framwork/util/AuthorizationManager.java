package framwork.util;

import com.codeborne.selenide.Selenide;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorizationManager {

    public static void authorizeAndRefresh(){
        Selenide.localStorage().setItem("isbypasslogin", "true");
        log.info("Token set");
        Selenide.refresh();
    }
}
