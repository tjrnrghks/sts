package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @SequenceGenerator(name = "board_seq", sequenceName = "board_seq", allocationSize = 1, initialValue = 1)
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Board {
	@Id @GeneratedValue(generator = "board_seq")
	long board_no;
	
	@Lob @Column(nullable = false)
	String board_title;
	
	@Lob @Column(nullable = false)
	String board_content;
	
	@CreationTimestamp
	Timestamp board_ctime;
	
	//같이등록은 있을 수 없고 같이 삭제만 가능
	@OneToMany(
			cascade = CascadeType.REMOVE, 
			mappedBy = "board"
	)
	@JsonManagedReference//JSON 만들 때 넣어라!
	List<Reply> replies;
}

