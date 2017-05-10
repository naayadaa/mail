package com.zagar.config;

import com.zagar.email.EmailEventService;
import com.zagar.email.EmailTextStorage;
import com.zagar.email.MailServiceSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import reactor.Environment;
import reactor.bus.EventBus;

import java.io.IOException;

import static reactor.bus.selector.Selectors.$;

/**
 * Created by naayadaa on 10.08.16.
 */
@Configuration
public class MailConfig {

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private String port;

    @Value("${mail.terra-subject}")
    private String terraSubject;

    @Value("classpath:email.txt")
    private Resource terraText;

    @Autowired
    private JavaMailSenderImpl mailSender;

    //Reactor beans
    @Bean
    public Environment reactorEnv() {
        return Environment.initializeIfEmpty()
                .assignErrorJournal();
    }

    @Bean
    public MailServiceSpring mailService(){
        return  new MailServiceSpring(mailSender);
    }



    @Bean
    public EmailTextStorage emailTextStorage() throws IOException {
        return new EmailTextStorage(terraSubject, terraText);
    }

    @Bean
    public EmailEventService emailByEventService() throws IOException {
        return new EmailEventService(mailService(), emailTextStorage());
    }

    @Bean
    public EventBus createEventBus(Environment reactorEnv) throws IOException {
        EventBus eventBus = EventBus.create(reactorEnv, Environment.THREAD_POOL);
        eventBus.on($("email"), emailByEventService());
        return eventBus;
    }


}
