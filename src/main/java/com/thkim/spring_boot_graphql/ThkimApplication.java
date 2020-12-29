package com.thkim.spring_boot_graphql;

import java.io.IOException;

//import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import graphql.GraphQL;
/*import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;*/

@SpringBootApplication
@RestController
public class ThkimApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ThkimApplication.class, args);
		/*
		 * String schema = "type Query{hello: String}";
		 * 
		 * SchemaParser schemaParser = new SchemaParser(); TypeDefinitionRegistry
		 * typeDefinitionRegistry = schemaParser.parse(schema);
		 * 
		 * RuntimeWiring runtimeWiring = newRuntimeWiring() .type("Query", builder ->
		 * builder.dataFetcher("hello", new StaticDataFetcher("world"))).build();
		 * 
		 * SchemaGenerator schemaGenerator = new SchemaGenerator(); GraphQLSchema
		 * graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry,
		 * runtimeWiring);
		 * 
		 * GraphQL build = GraphQL.newGraphQL(graphQLSchema).build(); ExecutionResult
		 * executionResult = build.execute("{hello}");
		 * System.out.println(executionResult.getData().toString());
		 */

		GraphQLProvider gqlP = new GraphQLProvider();
		gqlP.init();
		GraphQL build = gqlP.graphQL();
		// ExecutionResult executionResult = build.execute("{bookById(id:
		// 'book-1'){name}}");
		ExecutionResult executionResult = build.execute("{books{name pageCount author{firstName lastName}}}");

		String temp = executionResult.getData() == null ? "null" : executionResult.getData().toString();

		System.out.println(executionResult.getData().toString());

	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/test")
	public String test(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException {
		GraphQLProvider gqlP = new GraphQLProvider();
		gqlP.init();
		GraphQL build = gqlP.graphQL();
		// ExecutionResult executionResult = build.execute("{bookById(id:
		// 'book-1'){name}}");
		ExecutionResult executionResult = build.execute("{books{name pageCount author{firstName lastName}}}");

		String temp = executionResult.getData() == null ? "null" : executionResult.getData().toString();

		System.out.println(executionResult.getData().toString());
		return temp;
	}
}
