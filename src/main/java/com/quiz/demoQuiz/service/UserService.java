package com.quiz.demoQuiz.service;

import com.quiz.demoQuiz.entity.User;

public interface UserService {

    public User findUserByEmail(String email);
    public void saveUser(User user);
}
