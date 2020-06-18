package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

	@Query("select m from Member m where member_id = :#{#member.member_id} and member_pw = :#{#member.member_pw} ")
	Member login(Member member);	
}
