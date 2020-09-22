
package edu.eci.arep.sparkdocker.persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * The type Data base connection.
 */
public class DataBaseConnection {

    /**
     * The Uri.
     */
    MongoClientURI uri = new MongoClientURI(
            "mongodb://root:aG9sYW11bmRvMTIz@mongo-db:27017/?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&authSource=registro-arep&authMechanism=SCRAM-SHA-256&3t.uriVersion=3");
    /**
     * The Mongo client.
     */
    MongoClient mongoClient = new MongoClient(uri);
    /**
     * The Database.
     */
    MongoDatabase database = mongoClient.getDatabase("registro-arep");
    /**
     * The Collection.
     */
    MongoCollection<Document> collection = database.getCollection("messages");

    /**
     * Get client mongo client.
     *
     * @return the mongo client
     */
    public  MongoClient getClient(){
        return mongoClient;
    }


    /**
     * Gets clients.
     *
     * @return the clients
     */
    public MongoCollection<Document> getCollection(){
        try{
            return collection;
        }catch (Exception e){
            e.printStackTrace();
        }return null;
    }
    /**
     * Insert.
     *
     * @param data the data
     */



}