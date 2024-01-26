package websocket;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/server")
public class WebSocketServer {
	// 用來存放每一個客戶端的連線資訊
	private static CopyOnWriteArrayList<Session> sessions = new CopyOnWriteArrayList<>();
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("有 Client 連入");
		sessions.add(session); // 加入到容器
		System.out.println("目前連線數量: " + sessions.size());
		System.out.println("目前連線的 session id: " + session.getId());
	}
	
	// session: 傳送者(訊息來源)
	// message: 訊息內容
	@OnMessage
	public void onMessage(String message, Session session) throws Exception {
		System.out.printf("session id: %s 發送: %s%n", session.getId(), message);
		// 將訊息廣播給大家
		sessions.forEach(s -> { // s 指的就是每一個 session
			if(s.isOpen()) {
				s.getAsyncRemote().sendText(message);
			}
		});
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.printf("session id: %s 離開了%n", session.getId());
		sessions.remove(session);
		System.out.println("目前連線數量: " + sessions.size());
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.printf("session id: %s 發生錯誤: %s%n", session.getId(), throwable.getMessage());
	}
	
}
