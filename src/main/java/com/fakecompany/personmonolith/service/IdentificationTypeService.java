package com.fakecompany.personmonolith.service;

import com.fakecompany.personmonolith.model.IdentificationType;
import com.fakecompany.personmonolith.exception.DataNotFoundException;
import com.fakecompany.personmonolith.model.IdentificationTypeRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class IdentificationTypeService {
    private IdentificationTypeRepository identificationTypeRepository;

    public IdentificationType findById(Integer id){
        if(Objects.isNull(id)){
            throw new ObjectNotFoundException(id, "exception.objeto_no_encontrado");
        }
        return identificationTypeRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("exception.data_not_found.IdentificationType"));
    }
}
