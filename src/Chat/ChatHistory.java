package Chat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class ChatHistory {
    private List<Message> messages = new ArrayList<>();
    private List<String> blockedUsers = new ArrayList<>();

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Message getLastMessageForUser(String username) {
        for (int i = messages.size() - 1; i >= 0; i--) {
            Message message = messages.get(i);
            if (message.getSender().equals(username) || message.getRecipients().contains(username)) {
                return message;
            }
        }
        return null;
    }

    public void removeMessage(Message message) {
        messages.remove(message);
    }

    public void blockUser(String blocker, String blockedUser) {
        blockedUsers.add(blocker + "-" + blockedUser);
    }

    public boolean isBlocked(String username) {
        return blockedUsers.stream().anyMatch(block -> block.startsWith(username + "-"));
    }

    public Iterator<Message> iterator(String username) {
        return new SearchMessagesByUser(username, messages);
    }
}
