package utilities;

import javax.interceptor.InvocationContext;

public interface BaseLogger {
    public void log(InvocationContext context);
}
