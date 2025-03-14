package com.mycompany.property_management.service.impl;

import com.mycompany.property_management.converter.PropertyConverter;
import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.entity.PropertyEntity;
import com.mycompany.property_management.repository.PropertyRepository;
import com.mycompany.property_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;
    @Override
    public PropertyDTO savePropertyDto(PropertyDTO propertyDTO) {
        PropertyEntity propertyEntity = propertyConverter.convertDTOToEntity(propertyDTO);
        propertyEntity = propertyRepository.save(propertyEntity);
        propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);
        return propertyDTO;
    }
}
