package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    // 객체 생성


    /** MemberService 에도 MemoryMemberRepository 의 객체가 생성되어 있다
     근데 여기서 또 만든다는건 별로 좋은 생각이 아니다
    지금은 MemoryMemberRepository 가 static 으로 선언되어 있어서 문제가 없지만
    그렇지 않다면 다른 db가 되어 문제가 생길 수 있다
    => 지금은 다른 Repository를 사용하고 있는 것과 같다
    MemberService에 코드 추가
     BeforeEach 에서 객체 생성
     */
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 시작 전 객체를 생성해서 MemberService 에 넘기는 메서드
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 메모리를 clear 하기 위해 생성
    @AfterEach
    public void afterEach() {
        // MemoryMemberRepository.java 하단에 추가한 메서드
        memberRepository.clearStore();
    }

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
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}