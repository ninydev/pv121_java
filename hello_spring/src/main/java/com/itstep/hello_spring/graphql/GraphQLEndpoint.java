package com.itstep.hello_spring.graphql;

import com.itstep.hello_spring.repositories.BookRepository;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends HttpServlet {

    private SimpleGraphQLHttpServlet graphQLServlet;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        graphQLServlet.service(req, resp);
    }

    @Override
    public void init() {
        GraphQLSchema schema = SchemaParser.newParser()
                .resolvers(new GraphQLQueries())
                .file("schema.graphqls")
                .build()
                .makeExecutableSchema();
        graphQLServlet = SimpleGraphQLHttpServlet
                .newBuilder(schema)
                .build();
    }
}