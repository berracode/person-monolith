package com.pragma.personmonolith.service;

import com.pragma.personmonolith.exception.DataConstraintViolationException;
import com.pragma.personmonolith.exception.DataDuplicatedException;
import com.pragma.personmonolith.exception.DataNotFoundException;
import com.pragma.personmonolith.model.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ImageService {
    private ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image createImage(Image image){
        if(Objects.nonNull(image.getId())){
            Optional<Image> imageOptional = imageRepository.findById(image.getId());
            if(imageOptional.isPresent()){
                throw new DataDuplicatedException("exception.data_duplicated.image");
            }
        }

        try {
            return imageRepository.save(image);
        }catch (DataIntegrityViolationException e) {
            throw new DataConstraintViolationException("exception.data_constraint_violation.image");
        }
    }

    public Image findById(Integer id){
        if(Objects.isNull(id)){
            throw new ObjectNotFoundException(id, "exception.objeto_no_encontrado");
        }
        return imageRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("exception.data_not_found.image"));
    }
}
