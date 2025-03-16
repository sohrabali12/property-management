package com.mycompany.property_management.converter;

import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {

    public PropertyEntity convertDTOToEntity(PropertyDTO propertyDTO){
        PropertyEntity pe = new PropertyEntity();
        pe.setTitle(propertyDTO.getTitle());
        pe.setDescription(propertyDTO.getDescription());
        pe.setAddress(propertyDTO.getAddress());
        pe.setPrice(propertyDTO.getPrice());
        return pe;
    }

    public PropertyDTO convertEntityToDTO(PropertyEntity propertyEntity){
        PropertyDTO propertyDto = new PropertyDTO();
        propertyDto.setId(propertyEntity.getId());
        propertyDto.setTitle(propertyEntity.getTitle());
        propertyDto.setDescription(propertyEntity.getDescription());
        propertyDto.setAddress(propertyEntity.getAddress());
        propertyDto.setPrice(propertyEntity.getPrice());
        return propertyDto;
    }
}
