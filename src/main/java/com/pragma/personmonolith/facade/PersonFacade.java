package com.pragma.personmonolith.facade;

import com.pragma.personmonolith.dto.ImageDto;
import com.pragma.personmonolith.dto.PersonDto;
import com.pragma.personmonolith.dto.PersonImageDto;
import com.pragma.personmonolith.mapper.PersonMapper;
import com.pragma.personmonolith.model.Image;
import com.pragma.personmonolith.model.Person;
import com.pragma.personmonolith.service.ImageService;
import com.pragma.personmonolith.service.PersonService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonFacade {
    private PersonService personService;
    private ImageService imageService;
    private PersonMapper personMapper;

    public PersonFacade(PersonService personService,ImageService imageService,
                        PersonMapper personMapper) {
        this.personService = personService;
        this.imageService = imageService;
        this.personMapper = personMapper;
    }

    public PersonImageDto createPerson(PersonImageDto personImageDto){
        Person person = new Person();
        person.setName(personImageDto.getName());
        person.setLastName(personImageDto.getLastName());
        person.setIdentification(personImageDto.getIdentification());
        person.setIdentificationTypeId(personImageDto.getIdentificationTypeId());
        person.setAge(personImageDto.getAge());
        person.setCityBirth(personImageDto.getCityBirth());

        person = personService.createPerson(person);
        personImageDto.setPersonId(person.getId());

        if (personImageDto.getImage()!=null){
            Image image = new Image();

            image.setImage(personImageDto.getImage());
            image.setPersonId(personImageDto.getPersonId());
            image = imageService.createImage(image);

            personImageDto.setImageId(image.getId());

        }

        return personImageDto;

    }

    public PersonDto editPerson(PersonDto personDto){
        System.out.println("ID: ##"+personDto.getId());

        return personMapper.toDto(personService.editPerson(personMapper.toEntity(personDto)));

    }

    public void deletePerson(Integer personId){
        personService.deletePerson(personId);

    }

    public List<PersonDto> findAll(){
        return personMapper.toDto(personService.findAll());
    }


}
