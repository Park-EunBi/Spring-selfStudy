package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    // 객체 생성하기 
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test 가 순서에 의존되지 않도록 test 뒤에 메모리를 clear
    @AfterEach
    public void afterEach() {
        // MemoryMemberRepository.java 하단에 추가한 메서드
        repository.clearStore();
    }

    // test 할 때 넣어줘야 한다
    @Test
    public void save() {
        Member member = new Member();
        // name 설정하기
        member.setName("spring");
        // 저장하기
        repository.save(member);
        // 확인하기
        // optional을 꺼낼 땐 get() 사용 (패스트코드에서)
        Member result = repository.findById(member.getId()).get();
        // 검증하기
        // 방법 1 - 가져온 결과와 멤버가 같은지 확인
        //System.out.println("result = " + (result == member));
        // 근데 text로 확인하기는 좀 그러니깐 Assertions 사용
        // 방법 2 - Assertion 사용
        // 이 방법 쓰면 출력되는건 없는데 run 창 왼쪽에 파란 체크 생김
        //Assertions.assertEquals(member, result);
        // 방법 3 - Assertion.assertThat 사용 (요즘에는 이거 많이 사용)
        // assertion 위에서 alt + enter 누르고 두번째 클릭하면 위로 import 됨
        assertThat(member).isEqualTo(result);
    }

    // 두번째 test
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // result 에 name = spring1의 결과를 넣음
        Member result = repository.findByName("spring1").get();
        // 같은지 확인하기
        assertThat(result).isEqualTo(member1);

    }

    // 세번째 test - findAll
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        // 저장된 회원의 수가 2인지 확인
        assertThat(result.size()).isEqualTo(2);
    }
}
