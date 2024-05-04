package com.llm_service.llm_service.service;

import de.kherud.llama.ModelParameters;
import org.springframework.stereotype.Service;

@Service
public class LLMService {
    ModelParameters modelParameters;

    public LLMService() {
        modelParameters = new ModelParameters().setModelFilePath("model/llama-2-13b-chat.Q5_K_S.gguf");
    }

    public String generate(String text) {
        return "";
    }
}
