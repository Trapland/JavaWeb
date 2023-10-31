package step.learning.ws;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import step.learning.dao.AuthTokenDao;
import step.learning.dao.ChatDao;
import step.learning.dto.entities.AuthToken;
import step.learning.dto.entities.ChatMessage;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ServerEndpoint(
        value = "/chat", // url ws-сервера (ws://.../chat)
        configurator = WebsocketConfigurator.class
)
public class WebsocketServer {
    private final static Set<Session> sessions =
            Collections.synchronizedSet(new HashSet<>());
    private final ChatDao chatDao;

    private final AuthTokenDao authTokenDao;


    @Inject
    public WebsocketServer(ChatDao chatDao, AuthTokenDao authTokenDao) {
        this.chatDao = chatDao;
        this.authTokenDao = authTokenDao;
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig sec){
        //chatDao.install();
        session.getUserProperties().put
                ("user",sec.getUserProperties().get("user"));
        sessions.add(session);
    }
    @OnClose
    public void onClose(Session session){
        sessions.remove(session);
    }


    ExecutorService executor = Executors.newCachedThreadPool();

    @OnMessage
    public void onMessage(String message, Session session){
        JsonObject jsonObject = JsonParser.parseString(message).getAsJsonObject();
        String command = jsonObject.get("command").getAsString();
        String data = jsonObject.get("data").getAsString();
        switch (command){
            case "auth":
                AuthToken authToken = authTokenDao.getTokenByBearer(data);
                if(authToken == null){
                    sendToSession(session,403,"token rejected");
                }
                else {
                    session.getUserProperties().put("auth", authToken.getSub());
                    session.getUserProperties().put("nik", authToken.getNik());

                    sendToSession(session,202,authToken.getNik());
                }
                break;
            case "chat":
                String sub = (String) session.getUserProperties().get("auth"); // user id
                String nik = (String) session.getUserProperties().get("nik"); // user

                if(sub == null)
                {
                    sendToSession(session,401,"Auth required");
                }
                else{
                    executor.execute(() ->
                    {
                        chatDao.add(new ChatMessage(sub, data)); // user   data
                    });
                    sendToAll(201,nik + ": " + data); // user   data
                }
                break;
            default:
                break;
        }


    }
    @OnError
    public void onError(Throwable ex, Session session){
        System.err.println("onError " + ex.getMessage());
    }
    private void sendToSession(Session session,int status ,String data){
        JsonObject respObject = new JsonObject();
        respObject.addProperty("status",status);
        respObject.addProperty("data",data);

        try {
            session.getBasicRemote().sendText(respObject.toString());
        } catch (IOException ex) {

            System.err.println( "sendToSession " + ex.getMessage());
        }
    }
    private void sendToAll(int status,String data){
        for (Session session : sessions){
            sendToSession(session,status,data);
        }
    }
}
