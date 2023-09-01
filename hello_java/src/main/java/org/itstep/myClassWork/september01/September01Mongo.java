package org.itstep.myClassWork.september01;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class September01Mongo implements Runnable

{
    @Override
    public void run() {
        connectToMongo();
    }

    private void connectToMongo(){

        // Строка соединения
        String connectionStr = "mongodb://root:password@localhost:27017";

        // Создаем клиента
        MongoClient mongoClient = MongoClients.create(connectionStr);

        // Подключаемся к базе данных
        MongoDatabase mongoDatabase = mongoClient.getDatabase("pv121");

        // Получаем коллекцию
        MongoCollection<Document> collection = mongoDatabase.getCollection("documents");

        // Создаю новый документ
        Document newDoc = new Document("key", "val");
        newDoc.append("otherKey", "otherValue");
        ArrayList<Integer> vars = new ArrayList<>();
        vars.add(10);
        newDoc.append("someArr", vars);

        // Вставляю в коллекцию документ
        collection.insertOne(newDoc);

//
//        mongoDatabase.createCollection("MyColl");

    }
}
