import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "steps",
        tags = "@test",
        snippets = CucumberOptions.SnippetType.UNDERSCORE
)

public class RunnerTest extends AbstractTestNGCucumberTests {
}
