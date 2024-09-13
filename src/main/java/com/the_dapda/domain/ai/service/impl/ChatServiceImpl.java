package com.the_dapda.domain.ai.service.impl;

import com.the_dapda.domain.ai.dto.request.ChatRequestDto;
import com.the_dapda.domain.ai.dto.response.ChatResponseDto;
import com.the_dapda.domain.ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    @Value("${chat.command.f}")
    private String fModeCommand;

    @Value("${chat.command.t}")
    private String tModeCommand;

    @Value("${chat.command.default}")
    private String defaultCommand;

    @Value("${chat.command.category}")
    private String categoryCommand;

    private final ChatModel chatModel;

    public ChatResponseDto getAnswerAboutQuestion(ChatRequestDto requestDto) {
        try {
            String command;
            String tfMode = requestDto.getTfMode();
            if (tfMode == null || tfMode.isEmpty()) {
                tfMode = "default_value";
            }

            if ("F".equalsIgnoreCase(tfMode)) {
                command = fModeCommand;
            } else if ("T".equalsIgnoreCase(tfMode)) {
                command = tModeCommand;
            } else {
                throw new IllegalArgumentException(defaultCommand.replace("{tfMode}", tfMode));
            }

            PromptTemplate template = new PromptTemplate(command);
            template.add("question", requestDto.getQuestion());
            template.add("answer", requestDto.getContent());

            String message = template.render();

            Message userMessage = new UserMessage(message);
            Message systemMessage = new SystemMessage("translate to korean");

            String response = chatModel.call(userMessage, systemMessage);

            log.info("Response = " + response);
            return new ChatResponseDto(response);
        } catch (Exception e) {
            log.error("Error occurred while processing chat request", e);
            throw new RuntimeException("Chat processing failed", e);
        }
    }


    public ChatResponseDto getQuestionAboutCategory(String title, String prompt) {
        try {
            // Command creation using the title and prompt
            String command = String.format(categoryCommand, title, prompt);

            // Creating the prompt template with the command
            PromptTemplate template = new PromptTemplate(command);

            // Rendering the command into a usable message
            String message = template.render();

            // Constructing user message for the chat model
            Message userMessage = new UserMessage(message);

            String response = chatModel.call(userMessage);

            // Logging the response for debugging purposes
            log.info("Response = " + response);

            // Returning the response wrapped in a DTO
            return new ChatResponseDto(response);
        } catch (Exception e) {
            // Logging error and throwing a runtime exception if any error occurs
            log.error("Error occurred while processing chat request", e);
            throw new RuntimeException("Chat processing failed", e);
        }
    }
}