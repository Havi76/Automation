package Sandbox;

import framwork.testrunner.ClassLevelWebRunner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Test(groups = {"E2E"})
@Listeners(ClassLevelWebRunner.class)
public class Tests {
    @Test
    void e2eTest() {

    }
}
