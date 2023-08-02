package com.koolmain.imdb.service;

import java.util.Optional;

import com.koolmain.imdb.model.Principals;
import com.koolmain.imdb.model.PrincipalsId;

public interface PrincipalsService {
    
    Optional<Principals> getPrincipalsById(PrincipalsId id);

}
