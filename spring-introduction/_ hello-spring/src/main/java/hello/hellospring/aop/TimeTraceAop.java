package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// AOP 를 사용하기 위해서는 아래 어노테이션을 붙여야 한다
@Aspect
// spring bean 으로 등록하기 위해 component 사용하도 되지만
// AOP 를 사용하는 건 특별한 경우이기에 스프링 빈에 등록하는게 더 좋다
// AOP 를 사용하는 건 정형화 된 것이 아니고 쓰는구나 하고 아는 게 좋아서
@Component
public class TimeTraceAop {

    // 공통 관심 사항을 타겟팅 하기 위해 @Around 추가
    // Around의 범위를 조정하면 된다
    // 이러면 AOP 된 것!
    @Around("execution(* hello.hellospring..*(..))")
    // 메뉴얼 보고 구조 짜면 된다
    public Object execute (ProceedingJoinPoint joinPoint) throws Throwable {
        // 메서드 실행 시간 측정 코드
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
