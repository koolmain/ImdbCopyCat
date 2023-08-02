package com.koolmain.imdb.model.summary;

public interface RatingSummary {

    String getTconst(); 

    Double getAverageRating(); 

    Integer getNumVotes(); 

    TitleSummary getTitle();
    
}
