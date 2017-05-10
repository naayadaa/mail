package com.zagar.email;

/**
 * Created by naayadaa on 05.05.17.
 */
public class SendEvent {

    private Email email;

    private RequestOrigin requestOrigin;

    public SendEvent(Email email, RequestOrigin requestOrigin) {
        this.email = email;
        this.requestOrigin = requestOrigin;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public RequestOrigin getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(RequestOrigin requestOrigin) {
        this.requestOrigin = requestOrigin;
    }
}
