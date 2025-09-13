package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/features/TestFeatures.feature",
		glue="stepDefinitions",
		monochrome=false,
		plugin= {"pretty", "html:target/Cucumber-reports.html"}
		)
public class TestRunner{
	
}