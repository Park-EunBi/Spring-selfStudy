package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


// MemberRepository implements 하기
// implements 위에서 alt + enter 누르면 오버라이드 자동 추가
//@Repository
public class MemoryMemberRepository implements MemberRepository {

    // save 할 때 저장될 메모리 - map을 사용
    private static Map<Long, Member> store = new HashMap<>();
    // sequence 도 생성 - 0, 1 , 2 등 키 값을 생성해주는 것
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // sequence 의 값을 증가시켜 아이디의 값으로 설정
        member.setId(++sequence);
        // 아이디 값을 저장
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // id의 값을 반환한다
        // 근데 Optional으로 감싸서 null이 와도 처리한다
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // lambda 식 사용
        return store.values().stream()
                // 루프를 돌며 member의 값이 name과 같으면
                .filter(member -> member.getName().equals(name))
               // 값 반환
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // store에 있는 member들을 반환
        return new ArrayList<>(store.values());
    }

    // test 후 clear 하기 위해 생성
    public void clearStore() {
        store.clear();
    }
}
