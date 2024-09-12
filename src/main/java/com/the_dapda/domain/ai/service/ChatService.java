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
            String command;
            String tfMode = requestDto.getTfMode();
            if (tfMode == null || tfMode.isEmpty()) {
                // TFmode가 null이거나 비어있으면 기본값을 설정
                tfMode = "default_value";  // 기본값을 원하는 값으로 설정
            }

            // F 모드(감정적 공감)와 T 모드(현실적인 피드백)에 따라 다른 질문 생성
            if ("F".equalsIgnoreCase(tfMode)) {
                // F 모드: 감정적 공감을 해주는 명령어 생성
                command = "You've just answered a question about {question}. " +
                        "I can see that your answer '{answer}' shows great insight and reflection. " +
                        "It must have taken a lot of thought and care to share this. You're doing an amazing job!";
            } else if ("T".equalsIgnoreCase(tfMode)) {
                // T 모드: 현실적인 피드백을 해주는 명령어 생성
                command = "You've just answered a question about {question}. " +
                        "Your answer '{answer}' demonstrates practical and clear thinking. " +
                        "Moving forward, here are some actionable steps you might consider based on your insights.";
            } else {
                throw new IllegalArgumentException("Invalid TF mode: " + requestDto.getTfMode());
            }
            // 템플릿에 사용자 질문과 답변을 추가
            PromptTemplate template = new PromptTemplate(command);
            template.add("question", requestDto.getQuestion());
            template.add("answer", requestDto.getContent());  // "content"는 답변으로 사용됨
            String message = template.render();

            // 유저의 메시지와 시스템 명령 메시지 생성
            Message userMessage = new UserMessage(message);
            Message systemMessage = new SystemMessage("translate to korean");

            // AI 모델 호출
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
            String command = String.format("카테고리와 프롬프트를 참고해서 유저에게 답을 바라는 한 줄짜리 질문을 생성해줘 카테고리는: '%s' 이고 프롬프트는 : '%s'야. 이 질문은 매우 간결하지만 유저가 한 번쯤 고민해보면 좋을 법한 유익하면서 즐겁고 신박한 질문이야. 그리고 한글로 만들어줘", title, prompt);

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