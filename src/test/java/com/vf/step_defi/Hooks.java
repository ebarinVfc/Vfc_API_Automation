package com.vf.step_defi;

import com.vf.utilities.Driver;
import com.vf.vfcApiEndPoints.consumers_eapi.qa.v2.GuestAccessToken;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {


   /* @Before
    public void setUp() {
        // Run before each scenario
        GuestAccessToken.getAccessToken(); // retrieve guest token
    }
*/
    @After("@ui")
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {

            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        Driver.closeDriver();
    }
}
