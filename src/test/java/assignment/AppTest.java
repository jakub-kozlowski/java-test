package assignment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    App app;
    PrintStream systemOut;
    ByteArrayOutputStream systemOutContent;

    @Before
    public void setup() {
        app = new App();
        systemOut = System.out;
        systemOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent));
    }

    @After
    public void cleanup() {
        System.setOut(systemOut);
    }

    @Test
    public void whenAppStartedWithNoParameters_usageIsDisplayed() {
        app.run(new String[] {});
        assertThat(systemOutContent.toString()).contains("Usage:");
    }

    @Test
    public void whenAppStartedWithUnknownItem_anErrorMessageIsDisplayed() {
        String[] invalidArgs = new String[] {"F35 aircraft", "0"};
        app.run(invalidArgs);
        assertThat(systemOutContent.toString())
                .contains("Following items are unknown")
                .contains("F35 aircraft");
    }

    @Test
    public void whenAppStartedWithKnownItems_basketTotalIsOutput() {
        String[] args = new String[] {"apple", "bread", "0"};
        app.run(args);
        assertThat(systemOutContent.toString()).contains("Total:");
    }
}
