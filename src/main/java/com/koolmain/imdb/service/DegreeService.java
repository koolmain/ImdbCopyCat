package com.koolmain.imdb.service;

import com.koolmain.imdb.dto.DegreeDto;

public interface DegreeService {

    DegreeDto getDegreeOfReachbetweenActors(String targetActor, String sourceActor);
    
}

