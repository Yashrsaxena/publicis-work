package com.publicis.yash;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
//        MongoDatabase database= client.getDatabase("publicisyash");
        DB database = client.getDB("publicisyash");
//        System.out.println("Connected to the Database - publicisyash");
//        MongoIterable<String> list =  client.listDatabaseNames();
//        System.out.println(list);

        // Create - CRUD
        DBCollection users = database.getCollection("users");
//        BasicDBObject user = new BasicDBObject();
//        users.save(user);
//        user.put("name", "Akshay");
//        user.put("domain", "FullStack");
//        user.put("city", "Noida");
//        users.insert(user);
//        System.out.println("User Registered");
//
//        // Read - CRUD
        BasicDBObject query = new BasicDBObject();
//        DBCursor dbCursor = users.find(query);
//        while(dbCursor.hasNext())
//        {
//            System.out.println(dbCursor.next());
//        }
//
        //Update - CRUD
        BasicDBObject update = new BasicDBObject();
        BasicDBObject change = new BasicDBObject();
        query.put("name", "Akshay");
        change.put("name", "Akshay Kushwaha");
        update.put("$set", change);
        WriteResult result= users.update(query, update);
        System.out.println("Name has been updated from Yash Raj to Yash and rest fields dropped.");
//        database.getCollectionNames().forEach(System.out::println);
    }
}

