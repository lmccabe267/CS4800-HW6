package Chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class ChatServer {
    private Map<String, User> users = new HashMap<>();
    private ChatHistory history;

    public void registerUser(User user) {
        users.put(user.getUsername(), user);
        history = new ChatHistory();
    }

    public void unregisterUser(User user) {
        users.remove(user.getUsername());
    }

    public void sendMessage(Message message) {
        List<String> recipients = message.getRecipients();
        for (String recipient : recipients) {
            User user = users.get(recipient);
            if (user != null) {
                user.receiveMessage(message);
            }
        }
        history.addMessage(message); // Add message to central chat history
    }
    
    public ChatHistory getChatHistory() {
    	return history;
    }

    public Iterator<Message> getMessagesForUser(String username) {
        return history.iterator(username);
    }
}
