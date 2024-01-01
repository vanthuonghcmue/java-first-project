package com.example.demo.api.rest;

import com.example.demo.domain.dto.request.GroupRequest;
import com.example.demo.domain.dto.resource.GroupResource;
import com.example.demo.service.impl.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupApi {
    private final GroupService groupService;

    @Autowired
    public GroupApi(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseWrapper<GroupResource>> getAllGroups() {
        List<GroupResource> groups = groupService.all();
        ResponseWrapper<GroupResource> response = new ResponseWrapper<>();
        response.setData(groups);
        response.setMessage("Get groups successful");
        response.setCode("get_groups_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<GroupResource>> getGroupById(@PathVariable Long id) {
        GroupResource groupResource = groupService.getById(id);
        ResponseWrapper<GroupResource> response = new ResponseWrapper<>();
        response.setData(groupResource);
        response.setMessage("Get group successful");
        response.setCode("get_group_successful");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("")
    public ResponseEntity<ResponseWrapper<GroupResource>> createGroup(
            @Valid @RequestBody GroupRequest groupRequest
    ) {
        GroupResource groupResource = groupService.save(groupRequest);
        ResponseWrapper<GroupResource> response = new ResponseWrapper<>();
        response.setData(groupResource);
        response.setMessage("Create group successful");
        response.setCode("create_group_successful");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<GroupResource>> deleteGroup(@PathVariable Long id) {
        GroupResource groupResource = groupService.delete(id);
        ResponseWrapper<GroupResource> response = new ResponseWrapper<>();
        response.setData(groupResource);
        response.setMessage("Delete group successful");
        response.setCode("delete_group_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseWrapper<GroupResource>> updatePartialGroup(
            @PathVariable Long id,
            @RequestBody GroupRequest groupRequest
    ) {
        GroupResource groupResource = groupService.partialUpdate(id, groupRequest);
        ResponseWrapper<GroupResource> response = new ResponseWrapper<>();
        response.setData(groupResource);
        response.setMessage("Update group successful");
        response.setCode("update_group_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<GroupResource>> updateGroup(
            @PathVariable Long id,
            @RequestBody GroupRequest groupRequest
    ) {
        groupRequest.setId(id);
        GroupResource groupResource = groupService.save(groupRequest);
        ResponseWrapper<GroupResource> response = new ResponseWrapper<>();
        response.setData(groupResource);
        response.setMessage("Update group successful");
        response.setCode("update_group_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
