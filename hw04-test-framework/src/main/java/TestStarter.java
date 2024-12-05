import Annotations.After;
import Annotations.Before;
import Annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestStarter.class);
    private Set<Method> beforeMethods = new HashSet<>();
    private Set<Method> testMethods = new HashSet<>();
    private Set<Method> afterMethods = new HashSet<>();

    public void run(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        LOGGER.info("Start testing {}", clazz.getName());
        int passedCounter = 0;
        int failedCounter = 0;



        setMethodsByAnnotations(clazz);

        for (Method testMethod : testMethods) {
            Constructor<?> constructor = clazz.getConstructor(String.class);
            String instanceName = "Instance" + (passedCounter + failedCounter + 1);
            var instance = constructor.newInstance(instanceName);

            try {
                runMethods(instance.getClass(), beforeMethods);
                testMethod.invoke(instance);
                LOGGER.info("Test {} passed", testMethod.getName());
                passedCounter++;
            } catch (Exception e) {
                LOGGER.info("Test {} failed", testMethod.getName());
                failedCounter++;
            } finally {
                runMethods(instance.getClass(), afterMethods);
            }

        }
        LOGGER.info("Statistics passed/failed/all: {}/{}/{}", passedCounter, failedCounter, testMethods.size());
    }

    private void setMethodsByAnnotations(Class<?> clazz) {
        Set<Method> methods = getMethods(clazz);
        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            }
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
            if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            }
        }
    }

    private Set<Method> getMethods(Class<?> clazz) {
        Set<Method> methods = new HashSet<>();
        methods.addAll(List.of(clazz.getDeclaredMethods()));
        methods.addAll(List.of(clazz.getMethods()));
        return methods;
    }

    private void runMethods(Class<?> clazz, Set<Method> methods) throws InvocationTargetException, IllegalAccessException {
        for (Method method : methods) {
            method.setAccessible(true);
            method.invoke(clazz);
            method.setAccessible(false);
        }
    }
}
