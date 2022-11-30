import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@DisplayName("Beymen UI Test Suite")
@SelectClasses({
    TestScenario.class
})
public class SuiteClass {
}
