package com.thkim.spring_boot_graphql.config;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory; // +++++
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.thkim.spring_boot_graphql.util.annotation.Gql;
import com.thkim.spring_boot_graphql.util.annotation.GqlDataFetcher;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.RuntimeWiring.Builder; // +++++
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;

@Component
public class GraphqlAPI {

	// +++++
	@Autowired
	DefaultListableBeanFactory beanFactory;

	private GraphQL graphQL;

	//@Value("classpath:static/graphql/schema.graphqls")
	@Value("classpath:schema.graphqls")
	Resource resource;

	@Bean
	public GraphQL graphQL() {
		return graphQL;
	}

	@PostConstruct
	public void init() throws IOException {
		URL url = resource.getURL();
		String sdl = Resources.toString(url, Charsets.UTF_8);
		GraphQLSchema graphQLSchema = buildSchema(sdl);
		this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
	}

	private GraphQLSchema buildSchema(String sdl) {
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
		RuntimeWiring runtimeWiring = buildWiring();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
	}

	// +++++
	private RuntimeWiring buildWiring() {
		return createBuilderByAnnotation().build();
	}

	// +++++
	public Builder createBuilderByAnnotation() {
		Builder builder = null;
		try {
			// (1)
			builder = RuntimeWiring.newRuntimeWiring();
			// (2)
			Map<String, Object> classes = beanFactory.getBeansWithAnnotation(Gql.class);
			Class<?> clz = null;
			GqlDataFetcher gdf = null;
			// (3)
			for (Object obj : classes.values()) {
				clz = obj.getClass();
				// (4)
				for (Method mtd : clz.getMethods()) {
					// (5)
					if (mtd.isAnnotationPresent(GqlDataFetcher.class)) {
						// (6)
						gdf = mtd.getAnnotation(GqlDataFetcher.class);
						builder.type(
								// (7)
								TypeRuntimeWiring.newTypeWiring(gdf.type().getValue()).dataFetcher(mtd.getName(),
										(DataFetcher<?>) mtd.invoke(obj)));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder;
	}

}