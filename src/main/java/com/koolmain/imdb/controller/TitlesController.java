package com.koolmain.imdb.controller;

import java.util.List;

import com.koolmain.imdb.constants.ImdbI18NConstants;
import com.koolmain.imdb.dto.TitleDTO;
import com.koolmain.imdb.exceptions.ImdbNotFoundException;
import com.koolmain.imdb.util.ImdbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.koolmain.imdb.service.TitleService;

@RestController
@RequestMapping("/title")
public class TitlesController {

@Autowired
private TitleService titleService;
@Autowired
private ImdbUtils utils;

@GetMapping(value="/details/{id}" ,produces = "application/hal+json" )
public TitleDTO geTitleDetails(@PathVariable final String id){
    return titleService.getTitleById(id)
        .orElseThrow(()-> new ImdbNotFoundException(utils.getLocalMessage(ImdbI18NConstants.TITLE_NOT_FOUND, id)));
}

@GetMapping(value="/toprated/genre/{genre:[a-zA-Z0-9]+}", produces = "application/hal+json")
public List<TitleDTO> fetchTopRatedTitlesBygenre(@PathVariable final String genre, @RequestParam(defaultValue = "0") final int page){
    return  titleService.fetchTitleByGenre(genre, page); 
}

@GetMapping(value="/name/{titleName:.*}", produces = "application/hal+json")
public List<TitleDTO> fetchTitleByPrimaryOrOriginalTitle(@PathVariable final String titleName, @RequestParam(defaultValue = "0") final int page){
    return titleService.fetchTitlesByTitleName(titleName, page); 
}

}


