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
			 * ��ȸ�� ������ ��ȣ, �� �������� ������ ����, ���� ����, ������ ���� �÷��� ���ڷ� �Ͽ� Pageable ��ü�� �����մϴ�.
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

	// �Խñ� �ܰ� ��ȸ
	@GqlDataFetcher(type = GqlType.QUERY)
	public DataFetcher<?> getUser() {
		return enviroment -> {
			int id = enviroment.getArgument("id");
			return userRepository.findById(id);
		};
	}

	// �Խñ� �ű� ����
	@GqlDataFetcher(type = GqlType.MUTATION)
	public DataFetcher<?> createUser() {
		return environment -> {
			User entity = new User(environment.getArgument(""), environment.getArgument(""));
			entity.update(environment.getArguments());
			return userRepository.save(entity);
		};
	}

	// �Խñ� ����
	@GqlDataFetcher(type = GqlType.MUTATION)
	public DataFetcher<?> updateUser() {
		return environment -> {
			Optional<User> optional = userRepository.findById(environment.getArgument("id"));
			User entity = optional.get();
			return entity.update(environment.getArguments());
		};
	}
}
