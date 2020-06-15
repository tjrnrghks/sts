package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Book;

//repository 생성시 jpa 에서 제공하는 repository 를 하나 상속받아야함
//1번째 제너릭은 entitiy type
//2번째 제너릭은 p k type

public interface BookRepository extends JpaRepository<Book, Integer> {

	
	
}
