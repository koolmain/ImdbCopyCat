package com.koolmain.imdb.repository;
import com.koolmain.imdb.model.Principals;
import com.koolmain.imdb.model.PrincipalsId;
import org.springframework.data.repository.CrudRepository;

public interface PrincipalsRepository extends CrudRepository<Principals, PrincipalsId>{
    
}
