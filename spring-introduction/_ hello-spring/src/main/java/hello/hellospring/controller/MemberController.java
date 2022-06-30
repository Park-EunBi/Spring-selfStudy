package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // 여기서 바로 객체를 생성하지 않는 것이 좋다
    // 여기 저기서 MemberController 를 사용하는데
    // 그때 마다 객체가 생성되면 안좋음 -> 생성자로 연결하기
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // createMemberForm과 연결하는 코드
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 받아온 name을 join하고 다시 이전 페이지로 돌리기
    @PostMapping("/members/new")
    public String create(MemberForm from) {
        Member member = new Member();
        member.setName(from.getName());

        memberService.join(member);

        // 리다이렉트 시키기 - 이전 페이지로 돌아가기
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
