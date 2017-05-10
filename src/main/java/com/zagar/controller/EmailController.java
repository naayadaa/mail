package com.zagar.controller;

import com.zagar.email.Email;
import com.zagar.email.JsonEmailRepository;
import com.zagar.email.RequestOrigin;
import com.zagar.email.SendEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.bus.Event;
import reactor.bus.EventBus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by naayadaa on 03.05.17.
 */
@RestController
@RequestMapping(value = "/api/v1/all/application")
public class EmailController {

    @Autowired
    @Qualifier(value = "registrationTerraRepository")
    private JsonEmailRepository registrationTerraRepository;

    @Autowired
    @Qualifier(value = "questionTerraRepository")
    private JsonEmailRepository questionTerraRepository;

    @Autowired
    @Qualifier(value = "registrationInstaRepository")
    private JsonEmailRepository registrationInstaRepository;

    @Autowired
    @Qualifier(value = "questionInstaRepository")
    private JsonEmailRepository questionInstaRepository;

    @Autowired
    private EventBus eventBus;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public void register(@RequestBody Email email, HttpServletRequest request){

        if(request.getHeader("Origin").equals("http://instaleads-reg-it.biz"))
            registrationInstaRepository.create(email);
        if(request.getHeader("Origin").equals("http://terragram-reg-it.biz"))
            registrationTerraRepository.create(email);

        SendEvent sendEvent = new SendEvent(email, RequestOrigin.TERRAGRAM);
        eventBus.notify("email", Event.wrap(sendEvent));
    }

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public void question(@RequestBody Email email, HttpServletRequest request){
        if(request.getHeader("Origin").equals("http://instaleads-reg-it.biz"))
            questionInstaRepository.create(email);
        if(request.getHeader("Origin").equals("http://terragram-reg-it.biz"))
            questionTerraRepository.create(email);
    }

    @RequestMapping(value = "/registration/terragram", method = RequestMethod.GET)
    public List<Email> registeredTerra(){
        return registrationTerraRepository.list();
    }

    @RequestMapping(value = "/registration/instaleads", method = RequestMethod.GET)
    public List<Email> registeredInsta(){
        return registrationInstaRepository.list();
    }

    @RequestMapping(value = "/question/terragram", method = RequestMethod.GET)
    public List<Email> questionTerra(){
        return questionTerraRepository.list();
    }

    @RequestMapping(value = "/question/instaleads", method = RequestMethod.GET)
    public List<Email> questionInsta(){
        return questionTerraRepository.list();
    }
}
