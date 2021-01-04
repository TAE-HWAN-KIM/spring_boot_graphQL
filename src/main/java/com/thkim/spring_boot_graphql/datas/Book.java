package com.thkim.spring_boot_graphql.datas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table
@Entity
public class Book {
	@Id
	@GeneratedValue
	private Long bookId;

	private String name;

	private Long pageCount;

	private String authorId;

	public Book(Long bookId, String name, Long pageCount, String authorId) {
		this.bookId = bookId;
		this.name = name;
		this.pageCount = pageCount;
		this.authorId = authorId;
	}
}
