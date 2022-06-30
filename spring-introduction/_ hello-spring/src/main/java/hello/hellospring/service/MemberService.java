package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// MemberController.java 와 연결
//@Service // 이걸 추가해야 스프링 컨테이너가 AutoWired 시켜줄 수 있다
@Transactional
public class MemberService {

    // 회원 서비스를 개발하려면 일단 회원 repository가 있어야 한다
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    // test에서 같은 객체를 사용하여 test를 하기 위해 생성
    // 위에서 MemberRepository를 생성하는 것이 아니라
    // 외부에서 생성해서 가져올 수 있도록 변환
    // 이런 방식을 DI(Dependency injection - 의존관계 주입) 라고 한다
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // 회원 가입
    // member 에 저장하고 id를 반환하면 된다
    public Long join(Member member) {
        // 조건 - 같은 이름이 있는 중복회원 안된다
        /**
         Optional<Member> result = memberRepository.findByName(member.getName());
         // Optional 으로 감쌌기에 ifPresent 사용가능
         // 아니면 null 이 아니면 판단하는 메서드 사용
         result.ifPresent(m -> {
         throw new IllegalStateException("이미 존재하는 회원입니다.");
         });
         */
        // 근데 Optional 이 안예쁘기 때문에 코드를 아래와 같이 수정할 수 있다
        /**
         * 이 정도로 길면 그냥 메서드로 뽑아도 된다
         memberRepository.findByName(member.getName())
         .ifPresent( m -> {
         throw new IllegalStateException("이미 존재하는 회원입니다.");
         });

         */
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 검증
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}


