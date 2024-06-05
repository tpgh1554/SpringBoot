package com.kh.totalEx.repository;

import com.kh.totalEx.constant.ItemSellStatus;
import com.kh.totalEx.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootTest
@TestPropertySource(locations ="classpath:application.test.properties")
public class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

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
    public void findByPriceLessThanTest() {
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

    @Test
    @DisplayName("@Query를 이용한 검색 기능 구현")
    public void findByItemDeailTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemDetail("설명6");
        for(Item e : itemList) log.info(e.toString());
    }
    @Test
    @DisplayName("@Query를 이용한 검색 기능 구현")
    public void findByItemDetailByNative() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemDetailByNative("설명6");
        for(Item e : itemList) log.info(e.toString());
    }





}