package Chat;

import java.util.Iterator;
import java.util.List;

class User implements IterableByUser {
    private String username;
    private ChatServer server;

    public User(String username, ChatServer server) {
        this.username = username;
        this.server = server;
    }

    public void sendMessage(List<String> recipients, String content) {
        Message message = new Message(username, recipients, content);
        server.sendMessage(message);
    }

    public void receiveMessage(Message message) {
        // Check if the message is not blocked
        if (!message.getRecipients().contains(username) && !message.getSender().equals(username)) {
            System.out.println(username + " received message from " + message.getSender() + ": " + message.getContent());
        }
    }

    public void undoLastMessage() {
        ChatHistory chatHistory = server.getChatHistory();
        Message lastMessage = chatHistory.getLastMessageForUser(username);
        if (lastMessage != null) {
            chatHistory.removeMessage(lastMessage);
            System.out.println(username + " undid the last message sent.");
        } else {
            System.out.println(username + " has no messages to undo.");
        }
    }

    public void blockUser(String userToBlock) {
        server.getChatHistory().blockUser(username, userToBlock);
        System.out.println(username + " blocked messages from " + userToBlock);
    }

    public String getUsername() {
        return username;
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return server.getMessagesForUser(userToSearchWith.getUsername());
    }
}
