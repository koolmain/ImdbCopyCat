package com.koolmain.imdb.service.impl;

import java.util.Optional;

import com.koolmain.imdb.model.Principals;
import com.koolmain.imdb.model.PrincipalsId;
import com.koolmain.imdb.repository.PrincipalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koolmain.imdb.service.PrincipalsService;

@Service
public class PrincipalsServiceImpl implements PrincipalsService{

    @Autowired
    private PrincipalsRepository principalsRepository;
    
    
    /** 
     * Get Principals (relation between Name and TItle)
     * 
     * @param id PrincipalsId 
     * @return Optional<Principals>
     */
    @Override
    public Optional<Principals> getPrincipalsById(PrincipalsId id) {
        return principalsRepository.findById(id);
    }
    
}
