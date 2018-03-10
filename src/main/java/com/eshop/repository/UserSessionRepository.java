package com.eshop.repository;

import com.eshop.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSession, Integer> {

    UserSession findBySessionId(String sessionId);
    UserSession getBySessionId(String sessionId);
}