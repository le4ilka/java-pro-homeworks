import Annotations.After;
import Annotations.Before;
import Annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToTest.class);
    private String name;

    public ToTest(String name){
        this.name = name;
        LOGGER.info("{} default constructor working...", name);
    }
    @Test
    void testMethod1() {
        LOGGER.info("test1 for {}", name);
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
    void testMethod2() {
        LOGGER.info("test2 for {}", name);
        int a = 2;
        int b = 0;
        int result = (a / b);
    }


    @Test
    void testMethod3() {
        LOGGER.info("test3 for {}", name);
        int a = 2;
        int b = 0;
        int result = (a / b);
    }

}
