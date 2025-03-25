package com.mycompany.property_management.service;

import com.mycompany.property_management.converter.PropertyConverter;
import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.entity.PropertyEntity;
import com.mycompany.property_management.entity.UserEntity;
import com.mycompany.property_management.exception.BusinessException;
import com.mycompany.property_management.repository.PropertyRepository;
import com.mycompany.property_management.repository.UserRepository;
import com.mycompany.property_management.service.impl.PropertyServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyServiceImplTest {

    @InjectMocks
    private PropertyServiceImpl propertyServiceImpl;

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PropertyConverter propertyConverter;

    @Test
    void test_saveProperty_Success(){
        //This object is created to pass in the propertyServiceImpl.savePropertyDto(propertyDTO) method call
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setTitle("Title");

        // This PropertyEntity is created to return the mocked Object when propertyConverter.convertDTOToEntity is called
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setTitle("Title");

        // This PropertyEntity is created to return the mocked Object when propertyRepository.save is called
        PropertyEntity savedEntity = new PropertyEntity();
        savedEntity.setTitle("Title");
        savedEntity.setId(1L);

        // This PropertyDTO is created to return the mocked Object when propertyConverter.convertEntityToDTO is called
        PropertyDTO savedPropertyDTO = new PropertyDTO();
        savedPropertyDTO.setTitle("Title");
        savedPropertyDTO.setId(1L);

        //This Optional UserEntity Object is created to return when the actual call of UserRepository.findById is called
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        Optional<UserEntity> optnUserEntity = Optional.of(userEntity);


        when(userRepository.findById(Mockito.any())).thenReturn(optnUserEntity);
        when(propertyConverter.convertDTOToEntity(Mockito.any())).thenReturn(propertyEntity);
        when(propertyRepository.save(Mockito.any())).thenReturn(savedEntity);
        when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(savedPropertyDTO);

        PropertyDTO result = propertyServiceImpl.savePropertyDto(propertyDTO);

        assertEquals(savedPropertyDTO.getId(), result.getId());
    }

    @Test
    @DisplayName("Getting all the properties Success Scenario")
    void test_getAllProperties_Success(){

        //This property list is for mocking the actual find all call of Repository
        List<PropertyEntity> listOfProperties = new ArrayList<>();
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setTitle("Title");
        propertyEntity.setId(1L);
        listOfProperties.add(propertyEntity);

        //For mocking the Entity to DTO conversion
        PropertyDTO convertedPropertyDTO = new PropertyDTO();
        convertedPropertyDTO.setTitle("Title");
        convertedPropertyDTO.setId(1L);

        when(propertyRepository.findAll()).thenReturn(listOfProperties);
        when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(convertedPropertyDTO);

        List<PropertyDTO> result = propertyServiceImpl.getAllProperties();

        //Asserting not equals zero as Non zero result size represents that properties are present in the list
        assertNotEquals(0, result.size());

    }

    @Test
    @DisplayName("Update Property Success Scenario")
    void test_updateProperty_Success(){
        PropertyDTO savedDTO = new PropertyDTO();
        savedDTO.setTitle("Title");
        savedDTO.setId(1L);
        savedDTO.setPrice(12345.55);
        savedDTO.setDescription("Description");
        savedDTO.setAddress("Address");

        //For Mocking the repository's findById call
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setTitle("Title");
        propertyEntity.setId(1L);
        propertyEntity.setPrice(12345.55);
        propertyEntity.setDescription("Description");
        propertyEntity.setAddress("Address");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        propertyEntity.setUserEntity(userEntity);

        when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(propertyEntity));
        when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(savedDTO);

        PropertyDTO updatedPropertyDTO = propertyServiceImpl.updateProperty(savedDTO, 1L);

        assertEquals(savedDTO.getTitle(), updatedPropertyDTO.getTitle());
    }

    @Test
    @DisplayName("Update Property Description Success Scenario")
    void test_updatePropertyDescription_Success(){
        PropertyDTO savedDTO = new PropertyDTO();
        savedDTO.setId(1L);
        savedDTO.setDescription("Updated Description");

        //For Mocking the repository's findById call
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setId(1L);
        propertyEntity.setDescription("Updated Description");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        propertyEntity.setUserEntity(userEntity);

        when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(propertyEntity));
        when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(savedDTO);

        PropertyDTO updatedPropertyDTO = propertyServiceImpl.updatePropertyDescription(savedDTO, 1L);

        assertEquals(savedDTO.getDescription(), updatedPropertyDTO.getDescription());
    }

    @Test
    @DisplayName("Update Property Description Failure Scenario")
    void test_updatePropertyDescription_Failure(){
        PropertyDTO savedDTO = new PropertyDTO();
        savedDTO.setId(1L);
        savedDTO.setDescription("Updated Description");


        when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            propertyServiceImpl.updatePropertyDescription(savedDTO, 1L);
        });

        assertEquals("A Property With This Property ID Does Not Exists..", businessException.getErrors().get(0).getMessage());
    }
}
