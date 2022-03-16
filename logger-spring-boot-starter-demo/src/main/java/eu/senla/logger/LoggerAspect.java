package eu.senla.logger;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Log4j2
@Component
@Aspect
public class LoggerAspect {

    @Around("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public Object aroundCallAt(ProceedingJoinPoint call) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) call.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        final StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object result = call.proceed();
        stopWatch.stop();

        log.info("Execution time of " + className + "." + methodName + "args " +
                Arrays.toString(call.getArgs()) + "return value " + result +
                " :: " + stopWatch.getTotalTimeMillis() + " ms");
        return result;
    }
}
