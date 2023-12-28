package com.example.demo.mapper;

import com.example.demo.domain.dto.request.RegisterUserRequest;
import com.example.demo.domain.entity.UserEntity;

public interface IMapper<A, B, C>{
    /**
     *
     * @param a  entity object
     * @return  resource object
     */
    C mapTo(A a);


    /**
     *
     * @param b request object
     * @return  entity object
     */
    A mapFrom(B b);
}
