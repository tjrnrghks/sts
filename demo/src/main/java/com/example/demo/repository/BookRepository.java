package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Book;

//repository 생성시 jpa 에서 제공하는 repository 를 하나 상속받아야함
//1번째 제너릭은 entitiy type
//2번째 제너릭은 p k type

public interface BookRepository extends JpaRepository<Book, Integer> {

	
	//이름 일치 검색 메소드
	List<Book> findAllByName(String name);
	Page<Book> findAllByName(String name, Pageable pageable);
	
	//이름 일치 검색 메소드 (+대소문자 무시)
	List<Book> findByNameIgnoreCase(String name);
	
	//이름 유사 검색 메소드
	List<Book> findAllByNameLike(String name);
	List<Book> findAllByNameContaining(String name);
	
	
	//이름 유사 검색 메소드(+대소문자 무시)
	List<Book> findByNameLikeIgnoreCase(String name);
	List<Book> findAllByNameContainingIgnoreCase(String name);
	
	//+정렬 (orderby + 항목 + 순서)
	List<Book> findAllByNameContainingIgnoreCaseOrderByNameAsc(String name);
	
	//
	
}
