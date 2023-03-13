package com.vf.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {

                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt",
                "json:target/cucumber.json"

                },
        features = "src/test/resources/features",
        glue = "com/vf/step_defi",
        dryRun = false,
        tags = "@token"



)
public class CukesRunner {

}
