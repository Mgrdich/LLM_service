package com.llm_service.llm_service.controller.conversation;

import com.llm_service.llm_service.dto.Conversation;
import com.llm_service.llm_service.exception.conversation.ConversationNotFoundException;
import com.llm_service.llm_service.service.ConversationService;
import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Found conversations",
                        content = {@Content(mediaType = "application/json")})
            })
    @Operation(summary = "Get all conversations")
    @GetMapping()
    public ResponseEntity<List<ConversationResponse>> getAllConversations() {
        return ResponseEntity.ok(conversationService.getAll().stream()
                .map(conversationControllerMapper::map)
                .toList());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found the conversation",
                            content = {@Content(mediaType = "application/json")})
            })
    @Operation(summary = "Get conversation by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ConversationResponse> getConversationById(@PathVariable UUID id)
            throws ConversationNotFoundException {
        Conversation conversation =
                conversationService.getByID(id).orElseThrow(() -> new ConversationNotFoundException(id));

        return ResponseEntity.status(HttpStatus.OK).body(conversationControllerMapper.map(conversation));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Created new conversation",
                            content = {@Content(mediaType = "application/json")})
            })
    @Operation(summary = "Create new conversation")
    @PostMapping()
    public ResponseEntity<Conversation> createConversation() {
        Conversation conversation = conversationService.start();

        return ResponseEntity.status(HttpStatus.OK).body(conversation);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Conversation updated",
                            content = {@Content(mediaType = "application/json")})
            })
    @Operation(summary = "Continue conversation using conversation ID")
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
