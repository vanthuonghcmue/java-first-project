package com.example.demo.api.rest;

import com.example.demo.domain.dto.request.RegisterUserRequest;
import com.example.demo.domain.dto.resource.GroupResource;
import com.example.demo.domain.dto.resource.UserResource;
import com.example.demo.mapper.impl.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.GroupService;
import com.example.demo.service.impl.S3Service;
import com.example.demo.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GroupApi.class)

class GroupApiMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @MockBean
    private S3Service s3Service;



//    @Test
//    void getGroupById() throws Exception {
//        Long groupId = 1L;
//        GroupResource expectedGroupResource = new GroupResource();
//        // ... set resource fields
//
//        when(groupService.getById(groupId)).thenReturn(expectedGroupResource);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/groups/{id}", groupId))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.data").value(expectedGroupResource))
//                .andExpect(jsonPath("$.message").value("Get group successful"))
//                .andExpect(jsonPath("$.code").value("get_group_successful"));
//    }
}
