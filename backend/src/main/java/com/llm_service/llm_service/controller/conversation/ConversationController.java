package com.llm_service.llm_service.controller.conversation;

import com.llm_service.llm_service.dto.Conversation;
import com.llm_service.llm_service.dto.Discussion;
import com.llm_service.llm_service.exception.UnAuthorizedException;
import com.llm_service.llm_service.exception.conversation.ConversationNotFoundException;
import com.llm_service.llm_service.service.ConversationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;
    private final ConversationApiMapper conversationApiMapper;

    // TODO this should be paginated , maybe grouped by date
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "List of the conversations with the Chatto",
                        content = {@Content(mediaType = "application/json")})
            })
    @Operation(summary = "Get all conversations")
    @GetMapping(value = "/api/v1/paid/conversation")
    public ResponseEntity<List<ConversationResponseCompact>> getAllConversations() throws UnAuthorizedException {
        return ResponseEntity.ok(conversationService.getAll().stream()
                .map(conversationApiMapper::mapCompact)
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
    @GetMapping("/api/v1/conversation/{id}")
    public ResponseEntity<ConversationResponse> getConversationById(@PathVariable UUID id)
            throws ConversationNotFoundException, UnAuthorizedException {
        Conversation conversation =
                conversationService.getByID(id).orElseThrow(() -> new ConversationNotFoundException(id));

        return ResponseEntity.status(HttpStatus.OK).body(conversationApiMapper.map(conversation));
    }

    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Created new conversation",
                        content = {@Content(mediaType = "application/json")})
            })
    @Operation(summary = "Create new conversation")
    @PostMapping("/api/v1/conversation")
    public ResponseEntity<ConversationResponse> createConversation() throws Exception {
        Conversation conversation = conversationService.start();

        return ResponseEntity.status(HttpStatus.OK).body(conversationApiMapper.map(conversation));
    }

    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Conversation Continuation",
                        content = {@Content(mediaType = "application/json")})
            })
    @Operation(summary = "Continue conversation using conversation ID")
    @PutMapping("/api/v1/conversation/{id}/continue")
    public ResponseEntity<List<DiscussionResponse>> continueConversation(
            @PathVariable UUID id, @RequestBody ConversationRequest conversationRequest)
            throws ConversationNotFoundException, UnAuthorizedException {
        Conversation conversation =
                conversationService.getByID(id).orElseThrow(() -> new ConversationNotFoundException(id));
        List<Discussion> discussions = conversationService.askLlmQuestion(conversation, conversationRequest.getText());
        return ResponseEntity.status(HttpStatus.OK)
                .body(discussions.stream().map(conversationApiMapper::map).toList());
    }

    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Update a conversation",
                        content = {@Content(mediaType = "application/json")})
            })
    @Operation(summary = "update conversation title")
    @PutMapping("/api/v1/conversation/{id}")
    public ResponseEntity<ConversationResponseCompact> editConversation(
            @PathVariable UUID id, @RequestBody ConversationTitleRequest conversationTitleRequest) throws Exception {
        Conversation conversation =
                conversationService.getByID(id).orElseThrow(() -> new ConversationNotFoundException(id));
        conversation = conversationService.editTitle(conversation, conversationTitleRequest.getTitle());

        return ResponseEntity.status(HttpStatus.OK).body(conversationApiMapper.mapCompact(conversation));
    }

    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Deletes a conversation",
                        content = {@Content(mediaType = "application/json")})
            })
    @Operation(summary = "deletes a conversation")
    @DeleteMapping("/api/v1/conversation/{id}")
    public ResponseEntity<Void> deleteConversation(@PathVariable UUID id)
            throws ConversationNotFoundException, UnAuthorizedException {
        conversationService.getByID(id).orElseThrow(() -> new ConversationNotFoundException(id));
        conversationService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Deletes all conversation",
                        content = {@Content(mediaType = "application/json")})
            })
    @Operation(summary = "deletes all conversations")
    @DeleteMapping("/api/v1/conversation")
    public ResponseEntity<Void> deleteConversation() {
        conversationService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ExceptionHandler(ConversationNotFoundException.class)
    public ResponseEntity<String> handleConversationNotFoundException(
            ConversationNotFoundException conversationNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(conversationNotFoundException.getMessage());
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<String> handleUnAuthorized(UnAuthorizedException unAuthorizedException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unAuthorizedException.getMessage());
    }
}
