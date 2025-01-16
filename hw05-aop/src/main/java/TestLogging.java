import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogging implements TestLoggingInterface{
    private static final Logger LOGGER = LoggerFactory.getLogger(TestLogging.class);

    @Log
    @Override
    public void calculation(String param){
        LOGGER.info("This is calculation method working with String param = {}", param);
    }
    @Override
    public void calculation(int a){
        LOGGER.info("This is calculation method working with parameter a = {}", a);
    }

    @Log
    @Override
    public void calculation(int a, int b){
        LOGGER.info("This is calculation method working with parameters a = {}, b = {}", a, b);
    }

    public void calculation(int a, int b, int c){
        LOGGER.info("This is calculation method working with parameters a = {}, b = {}, c = {}", a, b, c);
    }
}
