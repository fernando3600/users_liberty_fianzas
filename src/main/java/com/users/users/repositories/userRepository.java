package com.users.users.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.users.users.models.userModel;

@Repository
@Qualifier("CrudRepository")
public interface userRepository extends CrudRepository<userModel, Long>{
    
}
