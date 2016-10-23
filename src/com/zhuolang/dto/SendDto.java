package com.zhuolang.dto;

import java.util.Date;
import java.util.List;

import com.zhuolang.model.Discuss;
import com.zhuolang.model.Like;

public class SendDto {
	private int id;
	private int user_id;
	private String send_content;
	private Date create_time;
	private int likes;
	private List<Discuss> discussList;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getSend_content() {
		return send_content;
	}
	public void setSend_content(String send_content) {
		this.send_content = send_content;
	}

	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}

	public List<Discuss> getDiscussList() {
		return discussList;
	}
	public void setDiscussList(List<Discuss> discussList) {
		this.discussList = discussList;
	}

	@Override
	public String toString() {
		return "SendDto{" +
				"id=" + id +
				", user_id=" + user_id +
				", send_content='" + send_content + '\'' +
				", create_time=" + create_time +
				", likes=" + likes +
				", discussList=" + discussList +
				'}';
	}
}
