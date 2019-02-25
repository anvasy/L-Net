package com.anvasy.model;

public class Article {
    private int id;
    private String topic;
    private String summary;
    private String content;
    private int author;

    public Article() {
        id = 0;
    }

    public Article(String topic, String summary, String content) {
        this.topic = topic;
        this.summary = summary;
        this.content = content;
    }

    public Article(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String text) {
        this.content = text;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
}
