package org.maxym.spring.controllers;

import org.maxym.spring.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/batch")
public class BatchController {

    private final PersonDAO personDAO;

    @Autowired
    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String start() {
        return "batch/start";
    }

    @GetMapping("/without")
    public String withoutBatch() {
        personDAO.withoutBatch();
        return "redirect:/people";
    }

    @GetMapping("/with")
    public String withBatch() {
        personDAO.withBatch();
        return "redirect:/people";
    }


}
