package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//회원 서비스 로직 ( 비지니스 로직을 처리하도록 설계 )
//@Service
@Transactional // JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
public class MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /*Refactor - 회원 서비스 코드를 DI 가능하게 변경한다.*/
    private final MemberRepository memberRepository;

    // 외부에서 레포지토리를 넣어준다. (테스트에 있는 레포지토리와 동일한 레포지토리를 사용하기위함)
        @Autowired
        public MemberService(MemberRepository memberRepository) {

            this.memberRepository = memberRepository;
        }


     /* 회원가입 */

    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        // Refactor (cmd + option + m)
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /*전체 회원 검색*/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
