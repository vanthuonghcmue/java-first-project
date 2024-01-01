package com.example.demo.service.impl;

import com.example.demo.domain.dto.request.GroupRequest;
import com.example.demo.domain.dto.resource.GroupResource;
import com.example.demo.domain.entity.GroupEntity;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.exception.customexception.ResourceNotFoundException;
import com.example.demo.mapper.impl.GroupMapper;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IGroupService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupService implements IGroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupMapper groupMapper;

    @Autowired
    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public GroupResource save(GroupRequest groupRequest) {
        GroupEntity groupEntity = groupMapper.mapFrom(groupRequest);
        return groupMapper.mapTo(groupRepository.save(groupEntity));
    }

    @Override
    public List<GroupResource> all() {
        List<GroupEntity> groupEntities = groupRepository.findAll();
        return groupEntities.stream().map(groupMapper::mapTo).toList();
    }

    @Override
    @Transactional
    public GroupResource delete(Long id) {
        GroupEntity deletedGroup = groupRepository.findById(id)
                .orElseThrow(() -> initGroupNotFoundException(id));
        groupRepository.deleteById(id);
        return groupMapper.mapTo(deletedGroup);
    }

    @Override
    @Transactional
    public GroupResource partialUpdate(Long id, GroupRequest groupRequest) {
        GroupEntity groupEntity = groupRepository.findById(id).map(
                existingGroup -> {
                    Optional.ofNullable(groupRequest.getName()).ifPresent(existingGroup::setName);
                    Optional.ofNullable(groupRequest.getDescription()).ifPresent(existingGroup::setDescription);
                    return groupRepository.save(existingGroup);
                }
        ).orElseThrow(() -> initGroupNotFoundException(id));
        GroupEntity groupUpdated = this.updateGroupMember(groupEntity, groupRequest.getUserIds());
        return groupMapper.mapTo(groupUpdated);
    }

    public Boolean exists(String name) {
        Optional<GroupEntity> existingGroup = groupRepository.findByName(name);
        return existingGroup.isPresent();
    }

    public GroupEntity updateGroupMember(GroupEntity groupEntity, List<Long> userIds) {
        Set<UserEntity> users = new HashSet<>();
        for (Long userId : userIds) {
            userRepository.findById(userId).ifPresent(users::add);
        }
        groupEntity.setUsers(users);
        return groupRepository.save(groupEntity);
    }

    @Override
    public GroupResource getById(Long id) {
        GroupEntity groupEntity = groupRepository.findById(id)
                .orElseThrow(() -> initGroupNotFoundException(id));
        return groupMapper.mapTo(groupEntity);
    }

    private ResourceNotFoundException initGroupNotFoundException(Long id) {
        return new ResourceNotFoundException("Group not found with id: " + id);
    }
}
