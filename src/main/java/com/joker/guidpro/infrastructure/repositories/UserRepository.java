package com.joker.guidpro.infrastructure.repositories;

import com.joker.guidpro.domains.models.agregates.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}