package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링으로 테스트를 하기 위해서는 아래 어노테이션 추가
@SpringBootTest
// 테스트 할 때 사용 하면 db에 커밋까지 하고 롤백한다 
@Transactional

class MemberServiceIntegrationTest {

    // test는 가장 끝단에 있는 거라서 편한 방법으로 하면 된다

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());

    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given - 중복된 이름 등록
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");


        // when - 중복된 이름 저장
        // member1을 등록하고
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /**
         * try catch는 사용하기 애매
        try {
            // member2를 등록할 때
            memberService.join(member2);
            fail(); // 예외가 나야 하는데 넘어감
        } catch (IllegalStateException e) {
            // 예외 잡으면 넘어감 - 예외를 잡는 test 라서
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
        // then
    }
}