package com.kh.totalEx.Repository;

import com.kh.totalEx.Constance.ItemSellStatus;
import com.kh.totalEx.Entity.Item;
import com.kh.totalEx.Entity.Member;
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
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        for(int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }
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
        Optional<Member> email1 = memberRepository.findByEmailAndPassword("user5@example.com", "pwd5");
        if(email1.isEmpty()) {
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


    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품3");
        for(Item e : itemList) System.out.println(e.getItemDetail());
    }

    @Test
    @DisplayName("상품명 또는 상품 상세 설명 조회")
    public void findByItemNmOrItemDetailTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("이이", "테스트 상품 상세 설명 5");
        for(Item e : itemList) System.out.println(e.toString());
    }


    @Test
    @DisplayName("가격 LessThan 테스트")
    public void fingByPriceLessThanTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for(Item e : itemList) System.out.println(e.toString());
    }

    @Test
    @DisplayName("Between으로 조건 검색")
    public void findByPriceBetween() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceBetween(10001, 10004);
        for(Item e : itemList) System.out.println(e.toString());
    }

    @Test
    @DisplayName("부분 문자열 검색")
    public void findByItemNmContaining() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmContaining("1");
        for(Item e : itemList) System.out.println(e.getItemNm());
    }



}