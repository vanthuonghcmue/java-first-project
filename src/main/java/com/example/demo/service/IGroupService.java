package com.example.demo.service;

import com.example.demo.domain.dto.request.GroupRequest;
import com.example.demo.domain.dto.resource.GroupResource;

import java.util.List;

public interface IGroupService {
    GroupResource save(GroupRequest groupRequest);
    GroupResource partialUpdate(Long id, GroupRequest groupRequest);
    Boolean exists(String name);
    GroupResource getById(Long id);
    List<GroupResource> all();
    GroupResource delete(Long id);
}
