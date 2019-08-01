package ru.javawebinar.graduation.util.exception;

public class ErrorInfo {
    private final String url;
    private final String messageText;
    private final String args[];

    public ErrorInfo(CharSequence url, String messageText, String[] args) {
        this.url = url.toString();
        this.messageText = messageText;
        this.args = args;
    }
}