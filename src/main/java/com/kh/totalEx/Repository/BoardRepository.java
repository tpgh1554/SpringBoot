package com.kh.totalEx.Repository;

import com.kh.totalEx.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitleContaining(String keyword);
    // 본문 내용에 포함된 키워드로 검색
    // 게시글 전체 보기
}
