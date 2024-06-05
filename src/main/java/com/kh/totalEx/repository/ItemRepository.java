package com.kh.totalEx.repository;

import com.kh.totalEx.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemNm(String itemNm); // select item_nm from item where item_nm = 'dd'
    List<Item> findByItemNmAndPrice(String itemNm, int price);
    //  OR 조건 처리하기
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
    // LessThan : 보다 작다는 의미
    List<Item> findByPriceLessThan(int price);
    // 정렬
    List<Item> findByPriceLessThanOrderByPriceDesc(int price);
    // Between으로 범위에 해당하는 조건 검색
    List<Item> findByPriceBetween(int minPrice, int maxPrice);
    // 전달된 부분 문자열에 대한 검색
    List<Item> findByItemNmContaining(String keyword);

    // 검색
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    @Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String detail);

}
