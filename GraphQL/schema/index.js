/**
 * Подключим необходимые модули для работы
 */
import {ApolloServer} from "@apollo/server";
import {startStandaloneServer} from "@apollo/server/standalone";

import fs from "fs";

const typeDefs = fs.readFileSync('./schema.facebook.graphql', {
    encoding: 'utf-8'
});

const sasha = {
    name: "Oleksandr Nykytin",
    posts: [],
    comments: [],
    likes: []
}
const sveta = {
    name: "Svetlana",
    posts: [],
    comments: [],
    likes: []
}

const pSasha = {
    title: "В поддержку Израиля",
    author: sasha,
    comments: [],
    likes: []
}

const c1 = {
    title: "Привально",
    post: pSasha,
    author: sveta
}
pSasha.comments.push(c1)
sveta.comments.push(c1)

const l1 = {
    post: pSasha,
    author: sveta
}
pSasha.likes.push(l1)
sveta.likes.push(l1)

const users = [sasha, sveta]
const posts = [pSasha]

const resolvers = {
    Query: {
        users: () => users,
        posts: ()=> posts
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
