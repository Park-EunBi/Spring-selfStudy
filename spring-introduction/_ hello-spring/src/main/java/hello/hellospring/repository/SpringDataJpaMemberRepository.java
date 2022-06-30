package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    // findbyid만 추가하면 구현이 끝난 것!
    // springJAP 가 자동으로 구현체를 만들어줌
    // 스프링빈에 자동으로 등록하게 함
    @Override
    Optional<Member> findByName(String name);
}
