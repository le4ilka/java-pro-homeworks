import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestApp.class);

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        LOGGER.info("Main begins...");
        run(ToTest.class);
    }

    private static void run(Class<?> TestClazz) throws InvocationTargetException, IllegalAccessException {
        LOGGER.info("Start testing {}", TestClazz.getName());
        Class<?> clazz = ToTest.class;
        int passedCounter = 0;
        int failedCounter = 0;
        int allCounter = 0;
        Method[] allMethods = clazz.getDeclaredMethods();
        Method[] testMethods = clazz.getDeclaredMethods();

        Annotation[] annotations = clazz.getDeclaredAnnotations();
        LOGGER.info("Annotations: {}", (Object) annotations);

        for (Method testMethod : testMethods) {
            if (testMethod.isAnnotationPresent(Test.class)) {
                for (Method method : allMethods) {
                    if (method.isAnnotationPresent(Before.class)) {
                        method.setAccessible(true);
                        method.invoke(TestClazz);
                        method.setAccessible(false);
                    }
                }
                try {
                    testMethod.invoke(TestClazz);
                    LOGGER.info("Test {} passed", testMethod.getName());
                    passedCounter++;
                    allCounter++;
                } catch (Exception e) {
                    LOGGER.info("Test {} failed", testMethod.getName());
                    new RuntimeException(e);
                    failedCounter++;
                    allCounter++;
                }

                for (Method method : allMethods) {
                    if (method.isAnnotationPresent(After.class)) {
                        method.setAccessible(true);
                        method.invoke(TestClazz);
                    }
                }
            }
        }
        LOGGER.info("Statistics passed/failed/all: {}/{}/{}", passedCounter, failedCounter, allCounter);
    }

    private void startMethodWithAnnotation(Class<?> clazz, Annotation annotation) throws InvocationTargetException, IllegalAccessException {
        Method[] allMethods = clazz.getDeclaredMethods();
        for (Method method : allMethods) {
            if (method.isAnnotationPresent(Before.class)) {
                method.setAccessible(true);
                method.invoke(method);
            }
        }

    }


}
