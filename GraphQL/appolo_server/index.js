/**
 * Подключим необходимые модули для работы
 */
import {ApolloServer} from "@apollo/server";
import {startStandaloneServer} from "@apollo/server/standalone";

/**
 * Опишем типы данных (модели и запросы)
 * @type {string}
 */
const typeDefs = `
    type Book {
        title: String,
        author: String
    }
    
    type Query {
        books: [Book]
    }
`;

/**
 * Создадим коллекцию объектов
 * @type {[{author: string, title: string}]}
 */
const books = [
    {
        title: "12 Стульев",
        author: "Ильф и Петров"
    }
];

/**
 * Набор решателей
 * @type {{Query: {books: (function(): {author: string, title: string}[])}}}
 */
const resolvers = {
    Query: {
        books: () => books
    }
}

/**
 * Экземпляру сервера мы передаем типы данных, с которыми мы будем работать
 * и resolvers - как сервер будет отвечать на запросы
 * @type {ApolloServer<BaseContext>}
 */
const server = new ApolloServer({
   typeDefs, resolvers
});

/**
 * начнем прослушивать 4000 порт
 */
const {url} = await startStandaloneServer( server, {
    listen: {port: 3000}
});

console.log("Server start " + url);
