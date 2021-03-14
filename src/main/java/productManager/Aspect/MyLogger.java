package productManager.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
public class MyLogger {
    @AfterReturning(pointcut = "within(productManager.controllers.*)", returning = "result")
    public void log(JoinPoint joinPoint, Object result) {
        System.out.println("start log...");
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println(className + "." + methodName);

        if (result == null) {
            System.out.println("null");
        } else {
//            System.out.println(result.hashCode());
        }

        //date
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));
        System.out.println("End log!");

    }


}
