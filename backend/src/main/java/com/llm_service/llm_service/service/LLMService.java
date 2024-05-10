package com.llm_service.llm_service.service;

import com.llm_service.llm_service.dto.Discussion;
import com.llm_service.llm_service.persistance.entities.DiscussionRole;
import de.kherud.llama.InferenceParameters;
import de.kherud.llama.LlamaModel;
import de.kherud.llama.ModelParameters;
import java.io.File;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LLMService {
    private final ModelParameters modelParameters;
    private final LlamaModel llamaModel;

    public static final String USERINST = " [/INST] ";
    public static final String SYSTEMINST = " </s><s>[INST] ";

    final String prefix = "def remove_non_ascii(s: str) -> str:\n    \"\"\" ";
    final String suffix = "\n    return result\n";

    public LLMService() {
        modelParameters =
                new ModelParameters().setModelFilePath("model" + File.separator + "model/llama-2-13b-chat.Q5_K_S.gguf");
        llamaModel = new LlamaModel(modelParameters);
    }

    public StringBuffer generate(String text) {
        InferenceParameters inferParams = new InferenceParameters(text).setNPredict(128);

        StringBuffer buffer = new StringBuffer();

        for (LlamaModel.Output output : llamaModel.generate(inferParams)) {
            buffer.append(output.text);
        }

        return buffer;
    }

    public String generateFullResponse(String text) {
        InferenceParameters inferParams = new InferenceParameters(text).setNPredict(128);
        return llamaModel.complete(inferParams);
    }

    public String initializeModelSystem() {
        return "<s>[INST] <<SYS>>\n"
                + "You are a helpful, respectful and honest assistant. Always answer as helpfully as possible, while being safe. If a question does not make any sense, or is not factually coherent, explain why instead of answering something not correct. If you don't know the answer to a question, please don't share false information.\n"
                + "<</SYS>>\n"
                + " \n";
    }

    public String preprocessPrompt(List<Discussion> discussions) {
        String messages = "";
        for (Discussion discussion : discussions) {
            if (discussion.getPromptRole() == DiscussionRole.USER) {
                messages += discussion.getText() + USERINST;
            } else {
                messages += discussion.getText() + SYSTEMINST;
            }
        }
        return messages;
    }
}
