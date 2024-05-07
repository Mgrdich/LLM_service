package com.llm_service.llm_service.controller.conversation;

import com.llm_service.llm_service.exception.conversation.ConversationNotFoundException;
import com.llm_service.llm_service.model.Conversation;
import com.llm_service.llm_service.service.ConversationService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conversation")
public class ConversationController {

    private final ConversationService conversationService;
    private final ConversationControllerMapper conversationControllerMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Conversation> getConversationById(@PathVariable UUID id)
            throws ConversationNotFoundException {
        Conversation conversation = conversationService.getByID(id);

        return ResponseEntity.status(HttpStatus.OK).body(conversation);
    }

    @PostMapping()
    public ResponseEntity<Conversation> createConversation() {
        Conversation conversation = conversationService.start();

        return ResponseEntity.status(HttpStatus.OK).body(conversation);
    }

    @PutMapping("/{id}/continue")
    public ResponseEntity<ConversationResponse> continueConversation(
            @PathVariable UUID id, @RequestBody ConversationRequest conversationRequest)
            throws ConversationNotFoundException {
        Conversation conversation = conversationService.update(id, conversationRequest);

        return ResponseEntity.status(HttpStatus.OK).body(conversationControllerMapper.map(conversation));
    }

    @ExceptionHandler(ConversationNotFoundException.class)
    public ResponseEntity<String> handleConversationNotFoundException(
            ConversationNotFoundException conversationNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(conversationNotFoundException.getMessage());
    }
}
