package com.thkim.spring_boot_graphql;

import org.springframework.stereotype.Component;

@Component
public class GraphQLProvider {

	/*
	 * private GraphQL graphQL;
	 * 
	 * @PostConstruct public void init() throws IOException { URL url =
	 * Resources.getResource("schema.graphqls"); String sdl =
	 * Resources.toString(url, Charsets.UTF_8); GraphQLSchema graphQLSchema =
	 * buildSchema(sdl); this.graphQL = GraphQL.newGraphQL(graphQLSchema).build(); }
	 * 
	 * @Autowired GraphQLDataFetchers graphQLDataFetchers;
	 * 
	 * private GraphQLSchema buildSchema(String sdl) { TypeDefinitionRegistry
	 * typeRegistry = new SchemaParser().parse(sdl); RuntimeWiring runtimeWiring =
	 * buildWiring(); SchemaGenerator schemaGenerator = new SchemaGenerator();
	 * return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring); }
	 * 
	 * // In the GraphQLProvider class private RuntimeWiring buildWiring() {
	 * graphQLDataFetchers = new GraphQLDataFetchers(); return
	 * RuntimeWiring.newRuntimeWiring()
	 * .type(newTypeWiring("Query").dataFetcher("books",
	 * graphQLDataFetchers.getBooksDataFetcher()) .dataFetcher("bookById",
	 * graphQLDataFetchers.getBookByIdDataFetcher()))
	 * .type(newTypeWiring("Book").dataFetcher("author",
	 * graphQLDataFetchers.getAuthorDataFetcher()) // This line is new: we need to
	 * register the additional DataFetcher // .dataFetcher("pageCount",
	 * graphQLDataFetchers.getPageCountDataFetcher()) ).build(); }
	 * 
	 * @Bean public GraphQL graphQL() { return graphQL; }
	 */
}