package com.koolmain.imdb.repository;

import java.util.List;
import java.util.Optional;

import com.koolmain.imdb.model.Title;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.koolmain.imdb.model.summary.TitleSummary;

public interface TitleSummaryRepository  extends CrudRepository<Title, String > {

    Optional<TitleSummary> findByTconst(String tconst);

    @Query(value = "select title from Title title where title.tconst in :idList")
    List<TitleSummary> getAllTitlesFromIds(List<String> idList); 
    
}