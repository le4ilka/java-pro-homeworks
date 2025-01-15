import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        LOGGER.info("Application starts...");
        TestLoggingInterface tli = Ioc.createMyClass();
        tli.calculation(3);
        tli.calculation(8);
        tli.calculation(3,1);
        tli.calculation(1,1,1);

        LOGGER.info("Application countinue...");
        SecondTestLoggingInterface second = Ioc.createSecondClass();
        second.sum(1);
        second.sum(1, 2, 3);
    }
}
