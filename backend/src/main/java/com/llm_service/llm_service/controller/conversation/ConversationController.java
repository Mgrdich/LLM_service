package com.llm_service.llm_service.controller.conversation;

import com.llm_service.llm_service.dto.Conversation;
import com.llm_service.llm_service.dto.Discussion;
import com.llm_service.llm_service.dto.ValidationErrorResponse;
import com.llm_service.llm_service.exception.UnauthorizedException;
import com.llm_service.llm_service.exception.conversation.ConversationNotFoundException;
import com.llm_service.llm_service.service.ConversationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;
    private final ConversationApiMapper conversationApiMapper;

    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "List of the conversations with the Chatto",
                        content = {@Content(mediaType = "application/json")})
            })
    @Operation(summary = "Get all conversations")
    @GetMapping(value = "/api/v1/paid/conversation")
    public ResponseEntity<List<ConversationResponseCompact>> getAllConversations(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize)
            throws UnauthorizedException {

        List<Conversation> allConversations = conversationService.getAll();

        int start = page * pageSize;
        int end = Math.min(start + pageSize, allConversations.size());
        List<Conversation> paginatedConversations = allConversations.subList(start, end);

        List<ConversationResponseCompact> conversationResponseList = paginatedConversations.stream()
                .map(conversationApiMapper::mapCompact)
                .toList();

        return ResponseEntity.ok(conversationResponseList);
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
            throws ConversationNotFoundException, UnauthorizedException {
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
    public ResponseEntity<?> continueConversation(
            @PathVariable UUID id,
            @Valid @RequestBody ConversationRequest conversationRequest,
            BindingResult bindingResult)
            throws ConversationNotFoundException, UnauthorizedException {
        ResponseEntity<ValidationErrorResponse> errorResponse = getValidationErrorResponseResponseEntity(bindingResult);
        if (errorResponse != null) return errorResponse;
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
    public ResponseEntity<?> editConversation(
            @PathVariable UUID id,
            @Valid @RequestBody ConversationTitleRequest conversationTitleRequest,
            BindingResult bindingResult)
            throws Exception {

        ResponseEntity<ValidationErrorResponse> errorResponse = getValidationErrorResponseResponseEntity(bindingResult);
        if (errorResponse != null) return errorResponse;

        Conversation conversation =
                conversationService.getByID(id).orElseThrow(() -> new ConversationNotFoundException(id));
        conversation = conversationService.editTitle(conversation, conversationTitleRequest.getTitle());

        return ResponseEntity.status(HttpStatus.OK).body(conversationApiMapper.mapCompact(conversation));
    }

    private static @Nullable ResponseEntity<ValidationErrorResponse> getValidationErrorResponseResponseEntity(
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ValidationErrorResponse errorResponse = new ValidationErrorResponse(new HashMap<>());
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorResponse.addError(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorResponse);
        }
        return null;
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
            throws ConversationNotFoundException, UnauthorizedException {
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

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnAuthorized(UnauthorizedException unauthorizedException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedException.getMessage());
    }
}
