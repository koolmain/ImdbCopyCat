package com.koolmain.imdb.repository;

import java.util.List;
import java.util.Optional;

import com.koolmain.imdb.model.Name;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.koolmain.imdb.model.summary.NameSmmary;

public interface NamesSummaryRepository extends CrudRepository<Name,String> {
    
    Optional<NameSmmary> findByNconst(String nconst);

    @Query(value = "select name from Name name where name.nconst in :idList")
    List<NameSmmary> getAllNamesFromIds(List<String> idList); 

    
}
