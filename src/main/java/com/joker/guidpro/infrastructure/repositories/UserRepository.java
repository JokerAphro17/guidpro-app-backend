package com.joker.guidpro.infrastructure.repositories;

import com.joker.guidpro.domains.models.agregates.Admin;
import com.joker.guidpro.domains.models.agregates.Expert;
import com.joker.guidpro.domains.models.agregates.Novice;
import com.joker.guidpro.domains.models.agregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

    List<User> findAll();

    User findByKeycloakId(String keycloakId);

    @Query("SELECT e FROM Expert e")
    List<Expert> findAllExperts();

    @Query("SELECT n FROM Novice n")
    List<Novice> findAllNovices();

    @Query("SELECT a FROM Admin a")
    List<Admin> findAllAdmins();
}