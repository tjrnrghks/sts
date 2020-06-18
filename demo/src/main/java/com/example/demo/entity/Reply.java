package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(name = "reply_seq", sequenceName = "reply_seq", initialValue = 1, allocationSize = 1)
@ToString(exclude = {"board"})
public class Reply {

	@Id @GeneratedValue(generator = "reply_seq", strategy = GenerationType.AUTO)
	long reply_no;
	
	@Lob
	@Column(nullable = false)
	String reply_content;
	
	@CreationTimestamp
	Timestamp ctime;
	
	@CreationTimestamp
	Timestamp utime;
	
	@ManyToOne
	@JoinColumn(name="board") // 조인할 이름
	@JsonBackReference//찍지마라 (json 만들 때)
	Board board;
	
}
