package com.quiz.demoQuiz.dao;

import com.quiz.demoQuiz.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
