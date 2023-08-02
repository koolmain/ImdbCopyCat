package com.koolmain.imdb.repository;

import com.koolmain.imdb.model.Name;
import org.springframework.data.repository.CrudRepository;

public interface NamesRepository extends CrudRepository<Name,String> {
}
