import Annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestStarter.class);

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        run(ToTest.class);
    }

    private static void run(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        LOGGER.info("Start testing {}", clazz.getName());
        int passedCounter = 0;
        int failedCounter = 0;
        int allCounter = 0;

        Set<Method> methods = getMethods(clazz);

        for (Method testMethod : methods) {
            if (testMethod.isAnnotationPresent(Test.class)) {
                Constructor<?> constructor = clazz.getConstructor(String.class);
                String instanceName = "Instance" + (allCounter + 1);
                var instance = constructor.newInstance(instanceName);

                runMethodsByAnnotation(instance.getClass(), Before.class);

                try {
                    testMethod.invoke(instance);
                    LOGGER.info("Test {} passed", testMethod.getName());
                    passedCounter++;
                    allCounter++;
                } catch (Exception e) {
                    LOGGER.info("Test {} failed", testMethod.getName());
                    failedCounter++;
                    allCounter++;
                }

                runMethodsByAnnotation(instance.getClass(), After.class);

            }
        }
        LOGGER.info("Statistics passed/failed/all: {}/{}/{}", passedCounter, failedCounter, allCounter);
    }

    private static Set<Method> getMethods(Class<?> clazz) {
        Set<Method> methods = new HashSet<>();
        methods.addAll(List.of(clazz.getDeclaredMethods()));
        methods.addAll(List.of(clazz.getMethods()));
        return methods;
    }

    private static void runMethodsByAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) throws InvocationTargetException, IllegalAccessException {
        Method[] allMethods = clazz.getDeclaredMethods();

        for (Method method : allMethods) {
            if (method.isAnnotationPresent(annotationClass)) {
                method.setAccessible(true);
                method.invoke(clazz);
                method.setAccessible(false);
            }
        }
    }
}
