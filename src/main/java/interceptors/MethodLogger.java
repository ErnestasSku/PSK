package interceptors;

import utilities.BaseLogger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@LoggedInvocation
public class MethodLogger implements Serializable{

//    @Inject
//    BaseLogger logger;

    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
//        logger.log(context);
        return context.proceed();
    }
}
