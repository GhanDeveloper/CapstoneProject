package com.upgrad.FoodOrderingApp.service.businness;


import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthTokenEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class LogoutBusinessService {

    //Respective Data access object has been autowired to access the method defined in respective Dao
    @Autowired
    private CustomerDao customerDao;

    @Transactional(propagation = Propagation.REQUIRED)

    public CustomerAuthTokenEntity logout(final String accessToken) {
        CustomerAuthTokenEntity customerAuthTokenEntity = customerDao.getCustomerAuthToken(accessToken);
            final ZonedDateTime now = ZonedDateTime.now();
            customerAuthTokenEntity.setLogoutAt(now);
            return customerAuthTokenEntity;
        }
    }
