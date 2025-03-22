package com.mycompany.property_management.controller;

import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.service.PropertyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class PropertyControllerTest {

    @InjectMocks
    private PropertyController propertyController;//Mockito is going to create a proxy/dummy object of PropertyController and inject it to our PropertyControllerTest

    @Mock//Mockito will give memory to PropertyService and it will inject this dummy/proxy PropertyService object inside the proxy/dummy object of PropertyController
    private PropertyService propertyService;

    @Test
    @DisplayName("Save new property Success Scenario")
    void test_saveProperty(){

        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setTitle("Title Of The Property");

        PropertyDTO savedPropertyDTO = new PropertyDTO();
        savedPropertyDTO.setId(1L);
        savedPropertyDTO.setTitle(propertyDTO.getTitle());

        //Do not make the actual call for propertyService.saveProperty(dto) inside controller rather return dummy object savedProperty
        Mockito.when(propertyService.savePropertyDto(propertyDTO)).thenReturn(savedPropertyDTO);

        ResponseEntity<PropertyDTO> responseEntity = propertyController.saveProperty(propertyDTO);

        assertNotNull(responseEntity.getBody().getId());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    @Test
    @DisplayName("Fetching all properties Success Scenario")
    void test_getAllProperties(){
        List<PropertyDTO> propertyList = new ArrayList<>();
        PropertyDTO dto  = new PropertyDTO();
        dto.setId(1L);
        dto.setTitle("Dummy Title");
        propertyList.add(dto);

        //Do not make the actual call for propertyService.getAllProperties() inside controller rather return the dummy list of property object
        Mockito.when(propertyService.getAllProperties()).thenReturn(propertyList);
        ResponseEntity<List<PropertyDTO>> allProperties = propertyController.getAllProperties();
        System.out.println("Property List Size : "+allProperties.getBody().size());
        assertNotEquals(0, allProperties.getBody().size());
        assertEquals(HttpStatus.OK, allProperties.getStatusCode());
    }

    @Test
    @DisplayName("Update Property Price Success Scenario")
    void test_UpdatePropertyPrice(){
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setPrice(67676.78);

        //Do not make the actual call for propertyService.updatePropertyPrice() inside controller rather return the dummy updated DTO of property object
        Mockito.when(propertyService.updatePropertyPrice(Mockito.any(), Mockito.anyLong())).thenReturn(propertyDTO);
        ResponseEntity<PropertyDTO> responseEntity = propertyController.updatePropertyPrice(propertyDTO, 1L);

        assertEquals(67676.78, responseEntity.getBody().getPrice());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
}
