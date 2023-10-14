package com.lisot.identityserver.repository;

import com.lisot.identityserver.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserCredencialRepository extends JpaRepository<UserCredential, UUID> {
    Optional<UserCredential> findByUserName(String username);
}
