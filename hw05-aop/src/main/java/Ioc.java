import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

class Ioc {
    private static final Logger logger = LoggerFactory.getLogger(Ioc.class);

    private Ioc() {}

    static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler<>(new TestLogging());
        return (TestLoggingInterface)
                Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[] {TestLoggingInterface.class}, handler);
    }

    static SecondTestLoggingInterface createSecondClass() {
        InvocationHandler handler = new DemoInvocationHandler<>(new SecondTestLogging());
        return (SecondTestLoggingInterface)
                Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[] {SecondTestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler<T> implements InvocationHandler {
        private final T myClass;
        DemoInvocationHandler(T myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Class<?> clazz = myClass.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method1: methods) {
                if (method1.isAnnotationPresent(Log.class) &&
                        method.getName().equals(method1.getName()) &&
                                (args.length == method1.getParameterCount())){
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
