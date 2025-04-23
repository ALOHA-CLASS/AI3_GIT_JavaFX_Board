package com.aloha.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Board {

	private int no;
	@NonNull private String title;
	@NonNull private String writer;
	@NonNull private String content;
	private Date createdAt;
	private Date updatedAt;
	
	
}
