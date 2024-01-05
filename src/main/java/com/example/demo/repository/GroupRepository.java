package com.example.demo.repository;

import com.example.demo.domain.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    Optional<GroupEntity> findByName(String name);
    @Query("SELECT g FROM GroupEntity g LEFT JOIN FETCH g.users")
    List<GroupEntity> findAllGroupWithUsers();
}
