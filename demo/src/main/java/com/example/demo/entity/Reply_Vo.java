package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class Reply_Vo {

	long reply_no;
	String reply_content;
	long board_no;
	
	public Reply convert() {
		return Reply.builder()
					.reply_no(reply_no)
					.reply_content(reply_content)
				.build();
	}
	
}
