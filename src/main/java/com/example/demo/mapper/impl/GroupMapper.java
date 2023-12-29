package com.example.demo.mapper.impl;

import com.example.demo.domain.dto.resource.GroupResource;
import com.example.demo.domain.entity.GroupEntity;
import com.example.demo.mapper.IMapper;
import com.example.demo.markerinterface.GroupDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper implements IMapper<GroupEntity, GroupDto, GroupResource> {
    private final ModelMapper mapper;

    @Autowired
    public GroupMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public GroupResource mapTo(GroupEntity groupEntity) {
        return mapper.map(groupEntity, GroupResource.class);
    }

    @Override
    public GroupEntity mapFrom(GroupDto groupDto) {
        System.out.println(groupDto);
        System.out.println(mapper.map(groupDto, GroupEntity.class));
        return mapper.map(groupDto, GroupEntity.class);
    }
}
