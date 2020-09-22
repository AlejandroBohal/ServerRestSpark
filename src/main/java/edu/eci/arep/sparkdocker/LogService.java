package edu.eci.arep.sparkdocker;


import com.google.gson.Gson;
import edu.eci.arep.sparkdocker.persistence.DataBaseConnection;
import edu.eci.arep.sparkdocker.documents.Message;
import edu.eci.arep.sparkdocker.services.MessageService;
import edu.eci.arep.sparkdocker.services.impl.MessageServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

/**
 * The type Log service.
 */
public class LogService {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String ... args){
        MessageService messageService = new MessageServiceImpl();

        port(getPort());
        staticFileLocation("/");
        get("/",(req,res)-> {
            res.redirect("/index.html");
            res.status(200);
            return null;
        });
        DataBaseConnection db = new DataBaseConnection();
        post("/messages",(request, response) -> {
            System.out.println(request.body());
            messageService.insertMessage(request.body());
            response.status(201);
            System.out.println(request.body());
            return request.body();
        });
        try {
            get("/messages", (request, response) -> {
                response.status(200);
                System.out.println(request.body());
                List<Message> data = messageService.getLastMessages();
                if (data == null) {
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "No se encontraron mensajes");
                    System.out.println(error);
                    response.status(404);
                    return error;
                }
                return new Gson().toJson(messageService.getLastMessages());
            });
        }catch (Exception e){
            System.out.println("Hola");
            e.printStackTrace();
        }

    }

    /**
     * Gets port.
     *
     * @return the port
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }


}
