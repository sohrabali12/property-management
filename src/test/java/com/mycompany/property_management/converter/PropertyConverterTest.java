package com.mycompany.property_management.converter;

import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.entity.PropertyEntity;
import com.mycompany.property_management.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PropertyConverterTest {

    @InjectMocks
    private PropertyConverter converter;

    @Test
    @DisplayName("Converting DTO to Entity Success Scenario")
    void test_convertDTOToEntity_Success(){
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setTitle("Title");
        propertyDTO.setPrice(12345.55);

        PropertyEntity propertyEntity = converter.convertDTOToEntity(propertyDTO);

        assertEquals(propertyDTO.getPrice(), propertyEntity.getPrice());
        assertEquals(propertyDTO.getTitle(), propertyEntity.getTitle());

    }

    @Test
    @DisplayName("Converting Entity to DTO Success Scenario")
    void test_convertEntityToDTO_Success(){
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setTitle("Title");
        propertyEntity.setPrice(12345.55);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        propertyEntity.setUserEntity(userEntity);


        PropertyDTO propertyDTO = converter.convertEntityToDTO(propertyEntity);

        assertEquals(propertyEntity.getPrice(), propertyDTO.getPrice());
        assertEquals(propertyEntity.getTitle(), propertyDTO.getTitle());

    }
}
