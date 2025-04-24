package com.aloha.DTO;

import java.text.SimpleDateFormat;
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
	private String createdAtFmt;
	private String updatedAtFmt;

	public String getCreatedAtFmt() {
		return dateFormat(createdAt);
	}
	public String getUpdatedAtFmt() {
		return dateFormat(updatedAt);
	}

	public static String dateFormat(Date date) {
		if (date == null) return "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	
}
