package com.llm_service.llm_service.service;

import de.kherud.llama.InferenceParameters;
import de.kherud.llama.LlamaModel;
import de.kherud.llama.ModelParameters;
import org.springframework.stereotype.Service;

@Service
public class LLMService {
    final private ModelParameters modelParameters;
    final private LlamaModel llamaModel;

    final String prefix = "def remove_non_ascii(s: str) -> str:\n    \"\"\" ";
    final String suffix = "\n    return result\n";

    public LLMService() {
        modelParameters = new ModelParameters().setModelFilePath("model/llama-2-13b-chat.Q5_K_S.gguf");
        llamaModel = new LlamaModel(modelParameters);
    }

    public StringBuffer generate(String text) {
            InferenceParameters inferParams =
                    new InferenceParameters(text)
                            .setNPredict(128);

            StringBuffer buffer = new StringBuffer();

            for (LlamaModel.Output output : llamaModel.generate(inferParams)) {
                buffer.append(output.text);
            }

            return buffer;
    }
}
