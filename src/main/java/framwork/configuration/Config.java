package framwork.configuration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.testng.IExecutionListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config implements IExecutionListener {

    private static final String PROPERTY_SQLSERVER_DATABASE_CONNECTION = "sqlserver.database.connection";
    private static final String PROPERTY_SQLSERVER_USERNAME = "sqlserver.database.username";
    private static final String PROPERTY_SQLSERVER_PASSWORD = "sqlserver.database.password";

    private static final String PROPERTY_POSTGRESQL_DATABASE_CONNECTION = "postgresql.database.connection";
    private static final String PROPERTY_POSTGRESQL_USERNAME = "postgresql.database.username";
    private static final String PROPERTY_POSTGRESQL_PASSWORD = "postgresql.database.password";

    public static final String PROPERTY_DATABASE_COMMON_TEST = "database.common.test";
    public static final String PROPERTY_DATABASE_FAULT_TEST = "database.fault.test";
    public static final String PROPERTY_DATABASE_WORKCARD_TEST = "database.work-card.test";
    public static final String PROPERTY_DATABASE_ENTRY_PERMIT_TEST = "database.entry-permit.test";
    public static final String PROPERTY_DATABASE_SHIFTS_TEST = "database.shifts.test";
    public static final String PROPERTY_DATABASE_AUTH_TEST = "database.auth.test";

    public static final String PROPERTY_DATABASE_COMMON_DEV = "database.common.dev";
    public static final String PROPERTY_DATABASE_FAULT_DEV = "database.fault.dev";
    public static final String PROPERTY_DATABASE_WORKCARD_DEV = "database.work-card.dev";
    public static final String PROPERTY_DATABASE_ENTRY_PERMIT_DEV = "database.entry-permit.dev";
    public static final String PROPERTY_DATABASE_SHIFTS_DEV = "database.shifts.dev";
    public static final String PROPERTY_DATABASE_AUTH_DEV = "database.auth.dev";

    private static final String PROPERTY_MONGODB_CONNECTION = "mongodb.connection";

    private static final String PROPERTY_API_TEST_API_KEY = "api.test-api-key";
    private static final String PROPERTY_API_DEV_API_KEY = "api.dev-api-key";

    private static final String PROPERTY_HOST_TEST = "test.host";

    private static final String PROPERTY_COMMON_TEST = "test.api.common";
    private static final String PROPERTY_AUTH_TEST = "test.api.auth";
    private static final String PROPERTY_WORKCARD_TEST = "test.api.work-card";
    private static final String PROPERTY_FAULTS_TEST = "test.api.faults";
    private static final String PROPERTY_ENTRY_PERMITS_TEST = "test.api.entry-permits";

    private static final String PROPERTY_COMMON_DEV = "dev.api.common";
    private static final String PROPERTY_AUTH_DEV = "dev.api.auth";
    private static final String PROPERTY_WORKCARD_DEV = "dev.api.work-card";
    private static final String PROPERTY_FAULTS_DEV = "dev.api.faults";
    private static final String PROPERTY_ENTRY_PERMITS_DEV = "dev.api.entry-permits";

    private static final String PROPERTY_API_AUTOMATION_KEY_NAME = "api.automation-key-name";
    private static final String PROPERTY_API_AUTH_TOKEN_NAME = "api.auth-token-name";

    private static final String PROPERTY_DRIVER_DEVICE_NAME = "driver.device-name";
    private static final String PROPERTY_DRIVER_BROWSER_SIZE = "driver.browser-size";
    private static final String PROPERTY_DRIVER_HEADLESS = "driver.headless";

    private static final String PROPERTY_CLEANUP_THREAD_MAX_LIFE = "cleanup.thread.max_life";

    public static final String PROPERTY_DATABASE_COMMON = "Common";
    public static final String PROPERTY_DATABASE_FAULT = "Faults";
    public static final String PROPERTY_DATABASE_WORKCARD = "WorkCard";
    public static final String PROPERTY_DATABASE_ENTRY_PERMIT = "EntryPermits";
    public static final String PROPERTY_DATABASE_SHIFTS = "Shifts";
    public static final String PROPERTY_DATABASE_AUTH = "Auth";



    static {
        loadPropertiesFile();
    }


    public final static String databaseSqlServerConnection = System.getProperty(PROPERTY_SQLSERVER_DATABASE_CONNECTION);
    public final static String databasePostgresqlConnection = System.getProperty(PROPERTY_POSTGRESQL_DATABASE_CONNECTION);
    public final static String databaseSqlServerUsername = System.getProperty(PROPERTY_SQLSERVER_USERNAME);

    public final static String databasePostgresqlUsername = System.getProperty(PROPERTY_POSTGRESQL_USERNAME);
    public final static String databaseSqlServerPassword = System.getProperty(PROPERTY_SQLSERVER_PASSWORD);
    public final static String databasePostgresqlPassword = System.getProperty(PROPERTY_POSTGRESQL_PASSWORD);

    public static String mongodbConnection = System.getProperty(PROPERTY_MONGODB_CONNECTION);

    public static final String apiAutomationKeyName = System.getProperty(PROPERTY_API_AUTOMATION_KEY_NAME);
    public static final String apiAuthTokenName = System.getProperty(PROPERTY_API_AUTH_TOKEN_NAME);

    public static final String driverDeviceName = System.getProperty(PROPERTY_DRIVER_DEVICE_NAME);
    public static final String driverBrowserSize = System.getProperty(PROPERTY_DRIVER_BROWSER_SIZE);
    public static final Boolean driverHeadless = Boolean.parseBoolean(System.getProperty(PROPERTY_DRIVER_HEADLESS));

    public static final Integer cleanupThreadMaxLife = Integer.parseInt(System.getProperty(PROPERTY_CLEANUP_THREAD_MAX_LIFE));


    public static String databaseCommon;
    public static String databaseFaults;
    public static String databaseWorkcard;
    public static String databaseEntryPermits;
    public static String databaseShifts;
    public static String databaseAuth;

    public static String apiKey;

    public static String url;

    public static String apiCommon;
    public static String apiAuth;
    public static String apiWorkcard;
    public static String apiFaults;
    public static String apiEntryPermits;

    private static void setSystemProperty(Properties properties, String... propertyKeys) {
        for (String key : propertyKeys) {
            System.setProperty(key, properties.getProperty(key));
        }
    }

    @Override
    public void onExecutionStart() {
        if ("dev".equals(System.getProperty("env"))) {
            databaseCommon = System.getProperty(PROPERTY_DATABASE_COMMON_DEV);
            databaseFaults = System.getProperty(PROPERTY_DATABASE_FAULT_DEV);
            databaseWorkcard = System.getProperty(PROPERTY_DATABASE_WORKCARD_DEV);
            databaseEntryPermits = System.getProperty(PROPERTY_DATABASE_ENTRY_PERMIT_DEV);
            databaseShifts = System.getProperty(PROPERTY_DATABASE_SHIFTS_DEV);
            databaseAuth = System.getProperty(PROPERTY_DATABASE_AUTH_DEV);

            apiKey = System.getProperty(PROPERTY_API_DEV_API_KEY);

            if (System.getProperty("host") != null) {
                url = Configuration.baseUrl = System.getProperty("host");
            } else {
                url = System.getProperty(PROPERTY_HOST_TEST);
                Configuration.baseUrl = url;
            }

            apiCommon = System.getProperty(PROPERTY_COMMON_DEV);
            apiAuth = System.getProperty(PROPERTY_AUTH_DEV);
            apiWorkcard = System.getProperty(PROPERTY_WORKCARD_DEV);
            apiFaults = System.getProperty(PROPERTY_FAULTS_DEV);
            apiEntryPermits = System.getProperty(PROPERTY_ENTRY_PERMITS_DEV);

        } else {
            databaseCommon = System.getProperty(PROPERTY_DATABASE_COMMON_TEST);
            databaseFaults = System.getProperty(PROPERTY_DATABASE_FAULT_TEST);
            databaseWorkcard = System.getProperty(PROPERTY_DATABASE_WORKCARD_TEST);
            databaseEntryPermits = System.getProperty(PROPERTY_DATABASE_ENTRY_PERMIT_TEST);
            databaseShifts = System.getProperty(PROPERTY_DATABASE_SHIFTS_TEST);
            databaseAuth = System.getProperty(PROPERTY_DATABASE_AUTH_TEST);

            apiKey = System.getProperty(PROPERTY_API_TEST_API_KEY);

            url = System.getProperty(PROPERTY_HOST_TEST);
            Configuration.baseUrl = url;

            apiCommon = System.getProperty(PROPERTY_COMMON_TEST);
            apiAuth = System.getProperty(PROPERTY_AUTH_TEST);
            apiWorkcard = System.getProperty(PROPERTY_WORKCARD_TEST);
            apiFaults = System.getProperty(PROPERTY_FAULTS_TEST);
            apiEntryPermits = System.getProperty(PROPERTY_ENTRY_PERMITS_TEST);
        }
    }

    @SneakyThrows(IOException.class)
    private static void loadPropertiesFile() {
        ClassLoader loader = Config.class.getClassLoader();
        InputStream inputStream = loader.getResourceAsStream("config.properties");
        Properties properties = new Properties();

        properties.load(inputStream);

        setSystemProperty(properties,
                PROPERTY_SQLSERVER_DATABASE_CONNECTION,
                PROPERTY_POSTGRESQL_DATABASE_CONNECTION,
                PROPERTY_SQLSERVER_USERNAME,
                PROPERTY_POSTGRESQL_USERNAME,
                PROPERTY_SQLSERVER_PASSWORD,
                PROPERTY_POSTGRESQL_PASSWORD,
                PROPERTY_DATABASE_COMMON_TEST,
                PROPERTY_DATABASE_FAULT_TEST,
                PROPERTY_DATABASE_WORKCARD_TEST,
                PROPERTY_DATABASE_ENTRY_PERMIT_TEST,
                PROPERTY_DATABASE_SHIFTS_TEST,
                PROPERTY_DATABASE_AUTH_TEST,
                PROPERTY_DATABASE_COMMON_DEV,
                PROPERTY_DATABASE_FAULT_DEV,
                PROPERTY_DATABASE_WORKCARD_DEV,
                PROPERTY_DATABASE_ENTRY_PERMIT_DEV,
                PROPERTY_DATABASE_SHIFTS_DEV,
                PROPERTY_DATABASE_AUTH_DEV,
                PROPERTY_MONGODB_CONNECTION,
                PROPERTY_API_TEST_API_KEY,
                PROPERTY_API_DEV_API_KEY,
                PROPERTY_HOST_TEST,
                PROPERTY_COMMON_TEST,
                PROPERTY_FAULTS_TEST,
                PROPERTY_AUTH_TEST,
                PROPERTY_WORKCARD_TEST,
                PROPERTY_ENTRY_PERMITS_TEST,
                PROPERTY_COMMON_DEV,
                PROPERTY_AUTH_DEV,
                PROPERTY_WORKCARD_DEV,
                PROPERTY_FAULTS_DEV,
                PROPERTY_ENTRY_PERMITS_DEV,
                PROPERTY_API_AUTOMATION_KEY_NAME,
                PROPERTY_API_AUTH_TOKEN_NAME,
                PROPERTY_DRIVER_DEVICE_NAME,
                PROPERTY_DRIVER_BROWSER_SIZE,
                PROPERTY_DRIVER_HEADLESS,
                PROPERTY_CLEANUP_THREAD_MAX_LIFE
        );
    }

    static {
        Configuration.timeout = 20000;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }
}