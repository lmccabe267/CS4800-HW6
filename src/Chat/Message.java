package Chat;

import java.util.List;

class Message {
    private String sender;
    private List<String> recipients;
    private long timestamp;
    private String content;

    public Message(String sender, List<String> recipients, String content) {
        this.sender = sender;
        this.recipients = recipients;
        this.timestamp = System.currentTimeMillis();
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    public MessageMemento createMemento() {
        return new MessageMemento(content, timestamp);
    }

    public void restoreFromMemento(MessageMemento memento) {
        this.content = memento.getContent();
        this.timestamp = memento.getTimestamp();
    }
}