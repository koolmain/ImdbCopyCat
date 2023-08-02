package com.koolmain.imdb.config;

import com.koolmain.imdb.dto.TitleDTO;
import com.koolmain.imdb.model.Title;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.autoconfigure.validation.ValidationConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImdbConfigurations {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper(); 
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        TypeMap<Title, TitleDTO> properyMapper = modelMapper.createTypeMap(Title.class, TitleDTO.class);
        properyMapper.addMappings(mapper -> mapper.map(src -> src.getNames(), TitleDTO::setNames));
        properyMapper.addMappings(mapper -> mapper.map(src -> src.getCrew().getDirectors(), TitleDTO::setDirectors));
        properyMapper.addMappings(mapper -> mapper.map(src -> src.getCrew().getWriters(), TitleDTO::setWrites));

        return modelMapper; 
    }

    @Bean
    public ValidationConfigurationCustomizer validationCustomizer(){
        
    }

}
