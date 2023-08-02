package com.koolmain.imdb.service;

import java.util.Optional;

import com.koolmain.imdb.model.Name;
import com.koolmain.imdb.model.summary.NameSmmary;

public interface NameService {

    Optional<Name> getNameById(String id);
    Optional<NameSmmary> getNameSummaryById(String id);
    
}

