package assignment;

import org.junit.Test;

public class AppTest {

    @Test
    public void canStartApp() {
        new App().run(new String[] {});
    }
}
