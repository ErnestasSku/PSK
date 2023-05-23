package utilities;

import javax.enterprise.inject.Default;
import javax.interceptor.InvocationContext;

@Default
public class Logger implements BaseLogger {
    @Override
    public void log(InvocationContext context) {
        System.out.println("Called method: " + context.getMethod().getName());
    }
}
