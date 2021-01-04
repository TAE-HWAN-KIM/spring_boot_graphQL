package com.thkim.spring_boot_graphql.datas;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "User")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "userid")
	public Long getId() {
		return userId;
	}

	public void setId(Long id) {
		this.userId = id;
	}

	private String Name;

	@Column(name = "name")
	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public User(Long id, String Name) {
		this.userId = id;
		this.Name = Name;
	}

	@PrePersist
	protected void onCreate() {
	}

	// 데이터 저장 및 수정을 할 때 사용할 메서드
	public User update(Map<String, Object> map) {
		return this;
	}

	// 조회수 증가 메서드
	public User setVisitCount(int visitCount) {
		return this;
	}
}
