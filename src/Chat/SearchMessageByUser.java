package Chat;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class SearchMessagesByUser implements Iterator<Message> {
    private Iterator<Message> iterator;
    private String username;
    private Message nextMessage;

    public SearchMessagesByUser(String username, List<Message> messages) {
        this.username = username;
        this.iterator = messages.iterator();
        this.nextMessage = findNextMessage();
    }

    @Override
    public boolean hasNext() {
        return nextMessage != null;
    }

    @Override
    public Message next() {
        if (nextMessage == null) {
            throw new NoSuchElementException("No more messages for user: " + username);
        }
        Message message = nextMessage;
        nextMessage = findNextMessage();
        return message;
    }

    private Message findNextMessage() {
        while (iterator.hasNext()) {
            Message message = iterator.next();
            if (message.getSender().equals(username) || message.getRecipients().contains(username)) {
                return message;
            }
        }
        return null;
    }
}
