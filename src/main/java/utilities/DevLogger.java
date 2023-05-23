package utilities;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;
import java.util.Arrays;
import java.util.stream.Collectors;

@Decorator
public class DevLogger implements BaseLogger{

    @Inject
    @Delegate
    private BaseLogger delegate;

    @Override
    public void log(InvocationContext context) {
        String stackTrace = Arrays.stream(Thread.currentThread().getStackTrace())
                            .map(Object::toString)
                            .collect(Collectors.joining("\n"));
        System.out.println(String.format("Stack trace: %s | \n %s %s", stackTrace, context.getMethod(), context.getClass()));

    }
}
