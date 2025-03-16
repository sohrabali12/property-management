package com.mycompany.property_management.service;

import com.mycompany.property_management.dto.PropertyDTO;

import java.util.List;

public interface PropertyService {

    PropertyDTO savePropertyDto(PropertyDTO propertyDTO);

    List<PropertyDTO> getAllProperties();
    PropertyDTO updateProperty(PropertyDTO propertyDTO, Long id);
    PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long id);
    PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long id);
    void deletePropertyById(Long id);

}
