//package com.example.demo.service.impl;
//
//import com.example.demo.service.IService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//public abstract class BaseRepository<EntityType, DTOType> implements IService<EntityType> {
//    private ModelMapper mapper;
//    protected JpaRepository<EntityType, Long> repository;
//    protected abstract JpaRepository<EntityType, Long> getRepository();
//    protected abstract ModelMapper getMapper();
//
//    public BaseRepository() {
//        this.setRepository();
//    }
//
//    public void setRepository() {
//        this.repository = this.getRepository();
//        this.mapper = this.getMapper();
//    }
//
//    @Override
//    public EntityType findById(Long id) {
//        return null;
//    }
//
//    @Override
//    public List<EntityType> findAll() {
//        return null;
//    }
//
//    @Override
//    public DTOType save(DTOType dto) {
//        EntityType entity = mapper.map(dto, EntityType.class);
//        return mapper.map(repository.save(dto), DTOType.class);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }
//}
