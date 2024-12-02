import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Annotations.*;

public class ToTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToTest.class);

    @Test
    static void testMethod1() {
        LOGGER.info("test1");
    }

    @Before
    static void beforeMethod1() {
        LOGGER.info("before1");
    }

    @Before
    static void beforeMethod2() {
        LOGGER.info("before2");
    }

    @After
    static void afterMethod() {
        LOGGER.info("after");
    }

    @Test
    static void testMethod2() {
        LOGGER.info("test2");
        int a = 2;
        int b = 0;
        int result = (a / b);
    }

}
