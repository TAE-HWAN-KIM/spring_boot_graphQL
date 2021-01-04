package com.thkim.spring_boot_graphql.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.thkim.spring_boot_graphql.datas.User;
import com.thkim.spring_boot_graphql.repository.UserRepository;
import com.thkim.spring_boot_graphql.util.annotation.Gql;
import com.thkim.spring_boot_graphql.util.annotation.GqlDataFetcher;
import com.thkim.spring_boot_graphql.util.annotation.GqlType;

import graphql.schema.DataFetcher;

@Gql
@Component
public class UserQuery {
	@Autowired
	UserRepository userRepository;

	@GqlDataFetcher(type = GqlType.QUERY)
	public DataFetcher<?> getUserList() {
		return enviroment -> {
			int pageIndex = enviroment.getArgument("index");
			/**
			 * 조회할 페이지 번호, 한 페이지에 보여줄 개수, 정렬 순서, 정렬할 기준 컬럼을 인자로 하여 Pageable 객체를 생성합니다.
			 */
			Pageable pageable = PageRequest.of(pageIndex, 5, Direction.DESC, "id");
			return userRepository.findAll(pageable);
		};
	}

	@GqlDataFetcher(type = GqlType.QUERY)
	public DataFetcher<?> users() {
		return enviroment -> {
			return userRepository.findAll();
		};
	}

	// 게시글 단건 조회
	@GqlDataFetcher(type = GqlType.QUERY)
	public DataFetcher<?> getUser() {
		return enviroment -> {
			int id = enviroment.getArgument("id");
			return userRepository.findById(id);
		};
	}

	// 게시글 신규 생성
	@GqlDataFetcher(type = GqlType.MUTATION)
	public DataFetcher<?> createUser() {
		return environment -> {
			User entity = new User(environment.getArgument(""), environment.getArgument(""));
			entity.update(environment.getArguments());
			return userRepository.save(entity);
		};
	}

	// 게시글 수정
	@GqlDataFetcher(type = GqlType.MUTATION)
	public DataFetcher<?> updateUser() {
		return environment -> {
			Optional<User> optional = userRepository.findById(environment.getArgument("id"));
			User entity = optional.get();
			return entity.update(environment.getArguments());
		};
	}
}
