package com.caocao.quizzapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuizSubmit {
    public String id;

    public String getResponse() {
        return response;
    }

    public String getId() {
        return id;
    }

    public String response;
}
