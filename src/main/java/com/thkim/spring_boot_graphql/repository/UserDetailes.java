package com.thkim.spring_boot_graphql.repository;

import graphql.ExecutionResult;

public interface UserDetailes {

	ExecutionResult execute(String query);
}
