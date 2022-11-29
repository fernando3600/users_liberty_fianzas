package com.users.users.repositories;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.users.users.models.userModel;

@Repository
@Qualifier("Paginate")
public interface userRepositoryPagination extends PagingAndSortingRepository<userModel, Long>{
    
}