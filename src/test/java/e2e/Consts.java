package e2e;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Consts {
    public final static String SUBUNIT_NAME = "פלוגה מבצעית א";
    public final static String SOLIDER_NAME = "עמיחי מרנס";
    public final static String USER_NAME = "ינוב סגל";
    public final static String INTERVIEWER_ID = "212753743";
    public final static String SOLIDER_PERSONAL_NUMBER = "8778535";
    public final static String FORMATTED_DATE = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.M.yy"));
}
