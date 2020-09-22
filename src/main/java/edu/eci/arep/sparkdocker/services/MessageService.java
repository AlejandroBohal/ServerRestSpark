package edu.eci.arep.sparkdocker.services;

import edu.eci.arep.sparkdocker.documents.Message;

import java.util.List;

public interface MessageService {
    List<Message> getLastMessages();
    Message insertMessage(String data);
}
