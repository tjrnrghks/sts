package com.example.demo.repository;

import com.example.demo.entity.Board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {

	//jpql 을 이용한 데이터 조회 구문 작성
	//- 구문만 잘짜면 메소드 이름은 더이상 중요치 않다 (아예 오해 없도록 다르게 짓는게 좋음)
	// jpql 은 대소문자를 구분하며 entity를 기준으로 구문작성 (테이블 기준아님)
	// 와일드 카드가 없다
	
	//@Query("select b from Board b")
	//-조회 시 N+1 이슈가 발생한다.
	//해결책으로 fetch 를 명시하여 조인하면 구문이 조인으로 변경되어 실행
	
//	@Query("select b from Board b join fetch b.replies ") // inner join
	@Query("select b from Board b left join fetch b.replies ") // left outer join
	List<Board> getlist();
	
}
