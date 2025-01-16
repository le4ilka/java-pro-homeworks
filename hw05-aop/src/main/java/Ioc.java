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
        private Set<Method> loggingMethods = new HashSet<>();


        DemoInvocationHandler(Object myClass) {
            this.myClass = myClass;
            this.loggingMethods = findLoggingMethods(myClass);
        }

        private Set<Method> findLoggingMethods(Object myClass) {
            Class<?> clazz = myClass.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method1 : methods) {
                if (method1.isAnnotationPresent(Log.class)) {
                    this.loggingMethods.add(method1);
                }
            }
            return loggingMethods;
        }

        Boolean isSignatureEquals(Method method1, Method method2) {
            Parameter[] params1 = method1.getParameters();
            Parameter[] params2 = method2.getParameters();
            if (params1.length != params2.length) {
                return false;
            }
            for (int i = 0; i < params1.length; i++) {
                if (params1[i].toString().equals(params2[i].toString())) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Method method1 : loggingMethods) {
                if (method.getName().equals(method1.getName()) &&
                        args.length == method1.getParameterCount() &&
                        isSignatureEquals(method1, method)
                ) {
                    logger.info("executed method: {}, param: {}", method1.getName(), Arrays.toString(args));
                }
            }
            return method.invoke(myClass, args);
        }


        @Override
        public String toString() {
            return "DemoInvocationHandler{" + "myClass=" + myClass + '}';
        }
    }
}
