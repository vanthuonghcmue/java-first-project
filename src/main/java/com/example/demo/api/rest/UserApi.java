package com.example.demo.api.rest;

import com.example.demo.domain.dto.request.RegisterUserRequest;
import com.example.demo.domain.dto.request.UserRequest;
import com.example.demo.domain.dto.resource.UserResource;
import com.example.demo.service.impl.S3Service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.impl.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserApi {
    final RedisTemplate redisTemplate;
    final S3Service s3Service;
    final UserService userService;

    @Autowired
    public UserApi(UserService userService, S3Service s3Service, RedisTemplate redisTemplate) {
        this.userService = userService;
        this.s3Service = s3Service;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("")
    public ResponseEntity<ResponseWrapper<UserResource>> getAllUsers() {
        List<UserResource> users = userService.all();
        ResponseWrapper<UserResource> response = new ResponseWrapper<>();
        response.setData(users);
        response.setMessage("Get users successful");
        response.setCode("get_users_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UserResource>> getUserById(@PathVariable Long id) {
        UserResource userResource = userService.getById(id);
        ResponseWrapper<UserResource> response = new ResponseWrapper<>();
        response.setData(userResource);
        response.setMessage("Get user successful");
        response.setCode("get_user_successful");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("")
    public ResponseEntity<ResponseWrapper<UserResource>> createUser(
            @Valid @RequestBody RegisterUserRequest registerUserRequest
    ) {
        UserResource userResource = userService.save(registerUserRequest);
        ResponseWrapper<UserResource> response = new ResponseWrapper<>();
        response.setData(userResource);
        response.setMessage("Create user successful");
        response.setCode("create_user_successful");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UserResource>> deleteUser(@PathVariable Long id) {
        UserResource userResource = userService.delete(id);
        ResponseWrapper<UserResource> response = new ResponseWrapper<>();
        response.setData(userResource);
        response.setMessage("Delete user successful");
        response.setCode("delete_user_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UserResource>> updatePartialUser(
            @PathVariable Long id,
            @RequestBody UserRequest userRequest
    ) {
        UserResource userResource = userService.partialUpdate(id, userRequest);
        ResponseWrapper<UserResource> response = new ResponseWrapper<>();
        response.setData(userResource);
        response.setMessage("Update user successful");
        response.setCode("update_user_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UserResource>> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequest userRequest
    ) {
        userRequest.setId(id);
        UserResource userResource = userService.save(userRequest);
        ResponseWrapper<UserResource> response = new ResponseWrapper<>();
        response.setData(userResource);
        response.setMessage("Update user successful");
        response.setCode("update_user_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/generate-presigned-upload-avatar-url")
    public ResponseEntity<ResponseWrapper<String>> getPreSignUrlPutAvatar(@RequestParam String extension) {
        ResponseWrapper<String> response = new ResponseWrapper<>();
        response.setData(s3Service.generatePreSignUrlUploadAvatar(extension));
        response.setMessage("create presign upload avatar url successful");
        response.setCode("create_presign_url_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/generate-presigned-get-avatar-url")
    public ResponseEntity<ResponseWrapper<String>> getPreSignUrlGetAvatar(@RequestParam String filename) {
        ResponseWrapper<String> response = new ResponseWrapper<>();
        response.setData(s3Service.generateGetObjectPresignURL(filename));
        response.setMessage("create presign get avatar url successful");
        response.setCode("create_presign_url_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("total")
    public ResponseEntity<ResponseWrapper<Long>> getTotalUsers() {
        ResponseWrapper<Long> response = new ResponseWrapper<>();
        response.setData((Long) redisTemplate.opsForValue().get("totalUser"));
        response.setMessage("get total uses successful");
        response.setCode("get_total_user_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("total-in-db")
    public ResponseEntity<ResponseWrapper<Long>> getTotalUsersInDB() {
        ResponseWrapper<Long> response = new ResponseWrapper<>();
        response.setData(userService.count());
        response.setMessage("get total uses successful");
        response.setCode("get_total_user_successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
