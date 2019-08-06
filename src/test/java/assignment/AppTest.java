package assignment;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    App app;

    @Before
    public void setup() {
        app = new App();
    }

    @Test
    public void canStartApp() {
        app.run(new String[] {});
    }

    @Test
    public void whenAppStartedWithUnknownItem_anErrorMessageIsDisplayed() {
        PrintStream systemOut = System.out;
        ByteArrayOutputStream systemOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent));

        try {
            String[] invalidArgs = new String[] {"F35 aircraft"};
            app.run(invalidArgs);
            assertThat(systemOutContent.toString())
                    .contains("Following items are unknown")
                    .contains("F35 aircraft");
        }
        finally {
            System.setOut(systemOut);
        }
    }
}
