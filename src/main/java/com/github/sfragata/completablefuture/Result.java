package com.github.sfragata.completablefuture;

public class Result {

    private Status status;

    private String message;

    enum Status {
            SUCCESS, ERROR
    }

    public Result() {
    }

    public Status getStatus() {

        return this.status;
    }

    public void setStatus(
        final Status status) {

        this.status = status;
    }

    public String getMessage() {

        return this.message;
    }

    public void setMessage(
        final String message) {

        this.message = message;
    }

    @Override
    public String toString() {

        return String.format("Result [status=%s, message=%s]", this.status, this.message);
    }

}
