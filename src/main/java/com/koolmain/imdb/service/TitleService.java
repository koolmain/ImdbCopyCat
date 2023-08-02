package com.koolmain.imdb.service;

import java.util.List;
import java.util.Optional;

import com.koolmain.imdb.dto.TitleDTO;
import com.koolmain.imdb.model.Title;
import com.koolmain.imdb.model.summary.TitleSummary;

public interface TitleService {

    Optional<TitleDTO> getTitleById(String id);
    Optional<TitleSummary> getTitleSummaryById(String id);
    Optional<Title> getTitleWithPrincipalsById(String id);
    List<TitleDTO> fetchTitlesByTitleName(String titleName, int page); 
    List<TitleDTO> fetchTitleByGenre(String genre, int page); 
    List<Title> getAllTitlesFromIds(List<String> ids );
    

    
}
