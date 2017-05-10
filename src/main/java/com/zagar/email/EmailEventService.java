package com.zagar.email;

import reactor.bus.Event;
import reactor.fn.Consumer;

/**
 * Created by naayadaa on 02.08.16.
 */
public class EmailEventService implements Consumer<Event<SendEvent>> {

    private MailServiceSpring mailService;
    private EmailTextStorage textStorage ;


    public EmailEventService(MailServiceSpring mailService, EmailTextStorage textStorage) {
        this.mailService = mailService;
        this.textStorage = textStorage;

    }

    @Override
    public void accept(Event<SendEvent> accountEvent) {
        Email email = accountEvent.getData().getEmail();
        RequestOrigin requestOrigin = accountEvent.getData().getRequestOrigin();
        String subject = textStorage.getSubject(requestOrigin);


        mailService.send(subject, textStorage.getText(requestOrigin), email.getEmail());
    }
}
