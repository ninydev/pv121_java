

// Создадим веб сервер
const express = require('express');
const app = express();



// Создадим схему (правила ответов на запросы фронта)
const {GraphQLSchema, GraphQLObjectType} = require("graphql");
const graphqlSchema = new GraphQLSchema({

    query: new GraphQLObjectType({
        name: 'Root',
        fields: {

        }

    })
});

// Подключим правила ответов к нашему веб серверу
const {graphqlHTTP} = require("express-graphql");
app.use('/graphql', graphqlHTTP({
    graphqlSchema,
    graphiql: false
}))


// Создадим раздел для документации (по доступным решениям)
const playGround = require('graphql-playground-middleware-express').default;
app.use('/playground', playGround({endpoint:'/graphql'}));


// Запустим веб сервер
app.listen(3000, () => {
    console.log("http://localhost:3000 Started");
})
