import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecondTestLogging implements SecondTestLoggingInterface{
    private static final Logger LOGGER = LoggerFactory.getLogger(SecondTestLogging.class);
    @Override
    public void sum(int param) {
        LOGGER.info("This is sum method working with parameter param = {}", param);
    }

    @Log
    @Override
    public void sum(int param, int param2, int param3) {
        LOGGER.info("This is sum method working with parameters param = {}, param2 = {}, param3 = {}", param, param2, param3);
    }
}