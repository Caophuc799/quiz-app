package com.caocao.quizzapp.dao;

import com.caocao.quizzapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, String> {

}
