package com.mycompany.property_management.service.impl;

import com.mycompany.property_management.converter.UserConverter;
import com.mycompany.property_management.dto.UserDTO;
import com.mycompany.property_management.entity.AddressEntity;
import com.mycompany.property_management.entity.UserEntity;
import com.mycompany.property_management.exception.BusinessException;
import com.mycompany.property_management.exception.ErrorModel;
import com.mycompany.property_management.repository.AddressRepository;
import com.mycompany.property_management.repository.UserRepository;
import com.mycompany.property_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private AddressRepository addressRepository;
    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> optnUserEntity = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if (optnUserEntity.isPresent()) {
            List<ErrorModel> errorModels = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("Email_Already_Exists");
            errorModel.setMessage("A User With This Email Already Exists.. Try login In Instead");
            errorModels.add(errorModel);
            throw new BusinessException(errorModels);
        }
        UserEntity userEntity = userConverter.convertDTOToEntity(userDTO);
        userEntity = userRepository.save(userEntity);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setHouseNo(userDTO.getHouseNo());
        addressEntity.setStreet(userDTO.getStreet());
        addressEntity.setCity(userDTO.getCity());
        addressEntity.setPostalCode(userDTO.getPostalCode());
        addressEntity.setCountry(userDTO.getCountry());
        addressEntity.setUserEntity(userEntity);
        addressRepository.save(addressEntity);


        return userConverter.convertEntityToDTO(userEntity);
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO = null;
        Optional<UserEntity> OptnUser = userRepository.findByOwnerEmailAndPassword(email, password);
        if(OptnUser.isPresent()){
            userDTO = userConverter.convertEntityToDTO(OptnUser.get());
        }
        else{
            List<ErrorModel> errors = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("Invalid Login");
            errorModel.setMessage("Incorrect Email/Password");
            errors.add(errorModel);
            throw new BusinessException(errors);
        }
        return userDTO;
    }
}
