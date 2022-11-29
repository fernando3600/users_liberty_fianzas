package com.users.users.services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.users.users.models.userModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.users.users.repositories.userRepositoryPagination;

@Service
@Qualifier("userServicePaginate")
public class userServicePaginate {
    
    @Autowired
    @Qualifier("Paginate")
    userRepositoryPagination _userRepositoryPagination;

    public Page<userModel> getUsersPaginate(int page) {
        Pageable firstPageWithTwoElements = PageRequest.of(page, 10);
        Page<userModel> allProducts = _userRepositoryPagination.findAll(firstPageWithTwoElements);

        return  allProducts;
    }
}
