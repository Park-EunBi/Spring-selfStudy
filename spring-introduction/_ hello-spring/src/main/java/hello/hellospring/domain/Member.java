package hello.hellospring.domain;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

// JAP가 관리하는 Entity가 된다
@Entity
public class Member {

    // PK를 맵핑 - IDENTITY (db가 생성해준다는 의미)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    // 아이디 식별자와 이름이라는 두가지 요구사항이 있었음
    private Long id; // 고객이 정한 id 값이 아닌 시스템이 정한 값

    // db의 Column name 이 username이라는 의미
    //@Column(name = "username")
    private String name;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
