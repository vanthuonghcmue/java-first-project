package com.example.demo.mapper;

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
