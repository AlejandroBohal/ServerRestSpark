package edu.eci.arep.sparkdocker.services.impl;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import edu.eci.arep.sparkdocker.persistence.DataBaseConnection;
import edu.eci.arep.sparkdocker.documents.Message;
import edu.eci.arep.sparkdocker.services.MessageService;
import org.bson.Document;
import org.json.JSONObject;

import java.util.*;

import java.util.stream.Collector;

public class MessageServiceImpl implements MessageService {
    DataBaseConnection db = new DataBaseConnection();
    MongoCollection<Document> collection = db.getCollection();

    @Override
    public List<Message> getLastMessages() {
        List<Message> messages = new ArrayList<>();

        for (Document d : collection.find()) {
            String date = d.get("fecha").toString();
            messages.add(new Message(d.get("mensaje").toString(), date));
        }

        return messages.stream().collect(lastN(10));
    }

    @Override
    public Message insertMessage(String data) {
        Gson gson = new Gson();
        Message message = gson.fromJson(data, Message.class);
        System.out.println(message);
        Document document = new Document();
        System.out.println(document);
        document.put("mensaje", message.getMensaje());
        System.out.println(document);
        document.put("fecha", message.getFecha());
        System.out.println(document);
        collection.insertOne(document);
        System.out.println(message);
        return message;

    }

    public static <T> Collector<T, ?, List<T>> lastN(int n) {
        return Collector.<T, Deque<T>, List<T>>of(ArrayDeque::new, (acc, t) -> {
            if (acc.size() == n)
                acc.pollFirst();
            acc.add(t);
        }, (acc1, acc2) -> {
            while (acc2.size() < n && !acc1.isEmpty()) {
                acc2.addFirst(acc1.pollLast());
            }
            return acc2;
        }, ArrayList::new);
    }
}
