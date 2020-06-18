package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Reply;
import com.example.demo.entity.Reply_Vo;
import com.example.demo.exception.ElementNotFoundException;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.ReplyRepository;

@RestController
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	private ReplyRepository replyRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@PostMapping("/")
	public Reply write(@RequestBody Reply_Vo replyVo) {
		Reply reply = replyVo.convert();
		reply.setBoard(boardRepo.findById(replyVo.getBoard_no()).orElseThrow(ElementNotFoundException::new));
		
		return replyRepo.save(reply);
	}
	
}
