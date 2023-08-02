package com.koolmain.imdb.controller;

import com.koolmain.imdb.constants.ImdbI18NConstants;
import com.koolmain.imdb.model.Name;
import com.koolmain.imdb.util.ImdbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koolmain.imdb.exceptions.ImdbNotFoundException;
import com.koolmain.imdb.model.summary.NameSmmary;
import com.koolmain.imdb.service.NameService;

@RestController
@RequestMapping("/name")
public class NamesController {
    
    @Autowired
    private NameService nameService; 

    @Autowired
    ImdbUtils utils;

    

    @GetMapping(value = "summary/{id}", produces = "application/hal+json")
    public NameSmmary getNameSummary(@PathVariable final String id){
        return nameService.getNameSummaryById(id)
                    .orElseThrow(()-> new ImdbNotFoundException(utils.getLocalMessage(ImdbI18NConstants.NAME_NOT_FOUND, id)));

    }

    @GetMapping(value = "details/{id}", produces = "application/hal+json")
    public Name getNameDetails(@PathVariable final String id){
        return nameService.getNameById(id)
                    .orElseThrow(()-> new ImdbNotFoundException(utils.getLocalMessage(ImdbI18NConstants.NAME_NOT_FOUND, id)));    
    }
}

