package com.the_dapda.domain.ai.service;

import com.the_dapda.domain.ai.dto.request.ChatRequestDto;
import com.the_dapda.domain.ai.dto.response.ChatResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatModel chatModel;

    public ChatResponseDto getAnswerAboutQuestion(ChatRequestDto requestDto) {
        try {
            String command = "You've just answered a question about {question}. " +
                    "I can see that your answer '{answer}' shows great insight! Keep up the great work, and feel proud of your thoughts.";
            PromptTemplate template = new PromptTemplate(command);
            template.add("question", requestDto.getQuestion());
            template.add("content", requestDto.getContent());
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
}