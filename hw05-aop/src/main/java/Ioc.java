import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Ioc {
    private static final Logger logger = LoggerFactory.getLogger(Ioc.class);

    private Ioc() {
    }

    static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (TestLoggingInterface)
                Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static SecondTestLoggingInterface createSecondClass() {
        InvocationHandler handler = new DemoInvocationHandler(new SecondTestLogging());
        return (SecondTestLoggingInterface)
                Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[]{SecondTestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final Object myClass;
        private Set<String> loggingMethods = new HashSet<>();


        DemoInvocationHandler(Object myClass) {
            this.myClass = myClass;
            this.loggingMethods = findLoggingMethods(myClass);
        }

        private Set<String> findLoggingMethods(Object myClass) {
            Class<?> clazz = myClass.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method1 : methods) {
                if (method1.isAnnotationPresent(Log.class)) {
                    this.loggingMethods.add(getMethodSignature(method1));
                }
            }
            return loggingMethods;
        }

        private String getMethodSignature(Method method) {
            return method.getName() + Arrays.toString(method.getParameterTypes());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (loggingMethods.contains(getMethodSignature(method))) {
                logger.info("executed method: {}, param: {}", method.getName(), Arrays.toString(args));
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" + "myClass=" + myClass + '}';
        }
    }
}
