package com.zagar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by naayadaa on 04.05.17.
 */
@Controller
public class MvcController {

    @RequestMapping("/registration/terragram")
    public String regterra(Model model) {
        return "regterra";
    }

    @RequestMapping("/registration/instaleads")
    public String reginsta(Model model) {
        return "reginsta";
    }

    @RequestMapping("/question/terragram")
    public String queterra(Model model) {
        return "queterra";
    }

    @RequestMapping("/question/instaleads")
    public String queinsta(Model model) {
        return "queinsta";
    }
}

