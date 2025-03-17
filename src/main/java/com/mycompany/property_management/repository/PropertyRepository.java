package com.mycompany.property_management.repository;

import com.mycompany.property_management.entity.PropertyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

    @Query("select p from PropertyEntity p where p.userEntity.id=:userId")
    public List<PropertyEntity> findAllByUserEntityId(@Param("userId") Long userEntityId);

}
