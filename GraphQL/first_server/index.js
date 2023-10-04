// Создадим веб сервер
const express = require('express');
const app = express();


// Создадим схему (правила ответов на запросы фронта)
const {GraphQLSchema, GraphQLObjectType, GraphQLString} = require("graphql");
const graphqlSchema = new GraphQLSchema({

    query: new GraphQLObjectType({
        name: 'RootQueryType',
        fields: {
            sayHello: {
                type: GraphQLString,
                resolve: () => 'Hello'
            }

        }

    })
});

// Подключим правила ответов к нашему веб серверу
const {graphqlHTTP} = require("express-graphql");
// Вместо того, что бы писать разные маршруты для работы с разными сущностями
// мы описываем 1 точку входа - все запросы теперь идут сюда
app.use('/graphql', graphqlHTTP({
    graphqlSchema,
    graphiql: false
}))


// Создадим раздел для документации (по доступным решениям)
// Что бы фронты знали, на какие запросы я могу отвечать - стаим утилиту для документации
const playGround = require('graphql-playground-middleware-express').default;
app.use('/playground', playGround({endpoint:'/graphql'}));


// Запустим веб сервер
app.listen(3000, () => {
    console.log("http://localhost:3000/playground Started");
})
