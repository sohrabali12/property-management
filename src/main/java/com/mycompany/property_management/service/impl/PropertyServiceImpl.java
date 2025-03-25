package com.mycompany.property_management.service.impl;

import com.mycompany.property_management.converter.PropertyConverter;
import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.entity.PropertyEntity;
import com.mycompany.property_management.entity.UserEntity;
import com.mycompany.property_management.exception.BusinessException;
import com.mycompany.property_management.exception.ErrorModel;
import com.mycompany.property_management.repository.PropertyRepository;
import com.mycompany.property_management.repository.UserRepository;
import com.mycompany.property_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PropertyDTO savePropertyDto(PropertyDTO propertyDTO) {

        Optional<UserEntity> optionById = userRepository.findById(propertyDTO.getUserId());
        if(optionById.isPresent()) {
            PropertyEntity propertyEntity = propertyConverter.convertDTOToEntity(propertyDTO);
            propertyEntity.setUserEntity(optionById.get());
            propertyEntity = propertyRepository.save(propertyEntity);
            propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);
        }
        else {
            List<ErrorModel> errorModels = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("USER_ID_DOES_NOT_EXIST");
            errorModel.setMessage("A User With This User ID Does Not Exists..");
            errorModels.add(errorModel);
            throw new BusinessException(errorModels);
        }
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> properties = ( List<PropertyEntity>) propertyRepository.findAll();

        List<PropertyDTO> propertyDTOs = new ArrayList<>();
        for(PropertyEntity propertyEntity : properties){
            PropertyDTO propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);
            propertyDTOs.add(propertyDTO);
        }
        return propertyDTOs;
    }

    @Override
    public List<PropertyDTO> getAllPropertiesForUser(Long userId) {
        List<PropertyEntity> properties = propertyRepository.findAllByUserEntityId(userId);
        List<PropertyDTO> propertyDTOs = new ArrayList<>();
        for(PropertyEntity propertyEntity : properties){
            PropertyDTO propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);
            propertyDTOs.add(propertyDTO);
        }
        return propertyDTOs;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long id) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(id);

        PropertyDTO propDto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setTitle(propertyDTO.getTitle());
            pe.setDescription(propertyDTO.getDescription());
            pe.setAddress(propertyDTO.getAddress());
            pe.setPrice(propertyDTO.getPrice());
            propDto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }

        return propDto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long id) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(id);

        PropertyDTO propDto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setDescription(propertyDTO.getDescription());
            propDto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }
        else {
            List<ErrorModel> errorModels = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("PROPERTY_DOES_NOT_EXIST");
            errorModel.setMessage("A Property With This Property ID Does Not Exists..");
            errorModels.add(errorModel);
            throw new BusinessException(errorModels);
        }

        return propDto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long id) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(id);

        PropertyDTO propDto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setPrice(propertyDTO.getPrice());
            propDto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }

        return propDto;
    }

    @Override
    public void deletePropertyById(Long id) {
        propertyRepository.deleteById(id);
    }
}
