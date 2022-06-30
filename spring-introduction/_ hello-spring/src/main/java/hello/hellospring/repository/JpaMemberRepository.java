package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // Jpa 는 entityManager로 모든 것이 동작
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    // 이렇게만 짜도 Jpa가 insert 쿼리 짜서 다 넣고 아이디까지 set 해준다
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // pk 기반이 아닌 것들은 쿼리를 작성해줘야 한다
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // Member 객체 m을 생성해서 바로 select 할 수 있다
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
