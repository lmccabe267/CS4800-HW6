package Chat;

import java.util.Iterator;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();

        User user1 = new User("Liam", server);
        User user2 = new User("Estevan", server);
        User user3 = new User("Brian", server);

        server.registerUser(user1);
        server.registerUser(user2);
        server.registerUser(user3);

        user1.sendMessage(List.of("Estevan", "Brian"), "Message from Liam");

        user2.blockUser("Liam");

        user3.sendMessage(List.of("Estevan"), "Hello Estevan");

        user2.undoLastMessage();

        user3.sendMessage(List.of("Estevan"), "How are you Estevan?");
        
        System.out.println("Messages sent or received by Estevan:");
        Iterator<Message> iterator = user2.iterator(user2);
        while (iterator.hasNext()) {
            Message message = iterator.next();
            System.out.println("Timestamp: " + message.getTimestamp() + ", Sender: " + message.getSender() + ", Content: " + message.getContent());
        }

    }
}