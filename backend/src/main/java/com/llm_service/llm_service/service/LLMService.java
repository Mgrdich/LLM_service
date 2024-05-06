package com.llm_service.llm_service.service;

import de.kherud.llama.InferenceParameters;
import de.kherud.llama.LlamaModel;
import de.kherud.llama.ModelParameters;
import org.springframework.stereotype.Service;

@Service
public class LLMService {
    final ModelParameters modelParameters;

    final String prefix = "def remove_non_ascii(s: str) -> str:\n    \"\"\" ";
    final String suffix = "\n    return result\n";

    public LLMService() {
        modelParameters = new ModelParameters().setModelFilePath("model/llama-2-13b-chat.Q5_K_S.gguf");
    }

    public StringBuffer generate(String text) {
        try (LlamaModel model = new LlamaModel(modelParameters)) {
            InferenceParameters inferParams =
                    new InferenceParameters(text).setInputPrefix(prefix).setInputSuffix(suffix);

            StringBuffer buffer = new StringBuffer();

            for (LlamaModel.Output output : model.generate(inferParams)) {
                buffer.append(output.text);
                System.out.println(output.text);
            }

            return buffer;
        }
    }
}
