package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    // 기능 1. 멤버 저장
    Member save(Member member);
    // 기능 2. 아이디로 멤버를 찾기
    // Optional - null 값 처리하는 방법 (java 문법)
    Optional<Member> findById(Long id);
    // 기능 3. 이름으로 멤버를 찾기
    Optional<Member> findByName(String name);
    // 기능 4. 지금까지 저장한 회원을 모두 반환
    List<Member> findAll();

}
