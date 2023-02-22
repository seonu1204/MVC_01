package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    // new Member~~ 로 만들지 않는 이유는 싱글톤으로 정의해놨기 때문에 새로 생성하면 안된다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    //Test가 끝날때마다 초기화
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given 이런게 주어졌을때
        Member member = new Member("hello", 20);

        //when 이런걸 실행했을때
        Member savedMember = memberRepository.save(member);

        //then 이런 결과가 나와야한다
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        //given 이런게 주어졌을때
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        
        //when 이런걸 실행했을때
        List<Member> result = memberRepository.findAll();

        //then 이런 결과가 나와야한다
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);

    }
}
