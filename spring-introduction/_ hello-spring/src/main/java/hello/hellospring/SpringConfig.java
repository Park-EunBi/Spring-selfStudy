package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //springJPA 사용하기 위해 추가
    private final MemberRepository memberRepository;

    @Autowired // 생성자가 1개라서 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /** springJPA 사용하기 위해 주석처리

    // JPA 사용하기 위해 추가
    private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
     */
    /** JPA 사용하기 위해 주석 처리
    //JdbcMemberRepository 을 사용하기 위해 변경
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */

    // spring bean 을 등록하기 위해 어노테이션 등록
    @Bean
    public MemberService memberService() {
        // springJpa 사용하기 위해 주석
        //return new MemberService(memberRepository());
        // springJpa 사용하기 위해 추기
        return new MemberService(memberRepository);
    }

    /**
     * springJpa 사용하기위해 주석처리
     *
     * @Bean public MemberRepository memberRepository() {
     * //return new MemoryMemberRepository();
     * // 이 부분만 수정
     * //return new JdbcMemberRepository(dataSource);
     * //Jdbc 사용하기 위해 파일 변경
     * //return new JdbcTemplateMemberRepository(dataSource);
     * //JPA 사용
     * return new JpaMemberRepository(em);
     * }
     */

    /** component scan 사용하기 위해 일단 생략 
     * 근데 이 방법이 더 좋은 것 (직접 스프링 빈 등록하는 방법)
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
    
     */
}
