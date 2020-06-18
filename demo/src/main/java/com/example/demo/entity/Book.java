package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity @SequenceGenerator(name = "book_seq", sequenceName = "book_seq", initialValue = 1, allocationSize = 1)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	
	@Id // 기본키 설정
	@GeneratedValue(generator = "book_seq")  //시퀀스 적용
	private int no;
	@Column(nullable = false, length =60)
	private String name;
	@Column(nullable = false, length =60)
	private String publisher;
	
	@Column
	private int price;
	
	//생성 시각
	@CreationTimestamp
	private Timestamp ctime;
	
	//수정 시각
	@UpdateTimestamp
	private Timestamp utime;
	
	
}
