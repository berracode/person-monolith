package com.pragma.personmonolith.mapper;

import com.pragma.personmonolith.dto.IdentificationTypeDto;
import com.pragma.personmonolith.dto.PersonDto;
import com.pragma.personmonolith.model.IdentificationType;
import com.pragma.personmonolith.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IdentificationTypeMapper extends EntityMapper<IdentificationTypeDto, IdentificationType>{
}

