package com.kh.totalEx.repository;



import com.kh.totalEx.constant.ItemSellStatus;
import com.kh.totalEx.entity.Item;
import com.kh.totalEx.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(locations ="classpath:application.test.properties")
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 정보 저장 테스트")
    public void createMemberTest() {
        for(int i = 1; i <= 10; i++) {
            Member member = new Member();
            member.setName("이름" + i);
            member.setPwd("pwd" + i);
            member.setEmail("user" + i + "@example.com");
            member.setImage("url" + i);
            member.setRegDate(LocalDateTime.now());
            memberRepository.save(member);
        }
    }
    @Test
    @DisplayName("가입 여부 확인")
    public void findByEmailAndPassword() {
        this.createMemberTest();
        Optional<Member> email1 = memberRepository.findByEmailAndPwd("user5@example.com", "pwd5");
        if(email1.isPresent()) {
            System.out.println("로그인 성공");
        } else {
            System.out.println("로그인 실패");
        }
    }
    @Test
    @DisplayName("전체 회원 정보 조회")
    public void findAll () {
        this.createMemberTest();
        List<Member> members = memberRepository.findAll();
        for(Member e : members) System.out.println(e.toString());
    }
    @Test
    @DisplayName("이메일 조회 테스트")
    public void findByIdTest() {
        this.createMemberTest();
        Optional<Member> memberList = memberRepository.findByEmail("user5@example.com");
        if(memberList.isPresent()) {
            Member member1 = memberList.get();
        }
    }


}
