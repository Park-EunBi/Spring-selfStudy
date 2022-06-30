package hello.hellospring.controller;

// @Controller 작성하면 자동으로 import 됨
import hello.hellospring.HelloSpringApplication;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // spring은 먼저 컨트롤 어노테이션을 적어줘야 한다 
public class HelloController {

    // web application 에서 hello 가 들어오면 아래 method 실행
    @GetMapping("hello")
    public String hello(Model model) {
        // data를 hello!! 라고 넘길 것이다
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    // 이번에는 외부에서 파라미터를 받아보자
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    //http 헤더, 바디 중 바디에 return 값을 직접 전달하겠다라는 의미
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        // 만약 spring 이라는 값을 넘기면
        // return 값으로 hello spring 이 넘어간다
        // view 이런거 없이 이게 바로 넘어간다
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        // 객체 생성
        Hello hello = new Hello();
        // name 을 name 으로 설정
        hello.setName(name);
        // 이번에는 문자가 아닌 객체를 반환
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

