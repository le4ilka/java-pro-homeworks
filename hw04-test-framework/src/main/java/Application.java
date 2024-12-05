import java.lang.reflect.InvocationTargetException;

public class Application {
    public static void main(String[] args) {
        try {
            new TestStarter().run(ToTest.class);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }
}
