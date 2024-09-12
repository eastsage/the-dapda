package com.the_dapda.domain.ai.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/ai")
public class ChatController {

    private final ChatModel chatModel;

    public ChatController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/chat")
    public String test() {
        String response;
        try {
            String command = "Tell me a {adjective} joke about {content}";
            PromptTemplate template = new PromptTemplate(command);
            template.add("adjective", "funny");
            template.add("content", "life");
            String message = template.render();
            Message userMessage = new UserMessage(message);
            Message systemMessage = new SystemMessage("translate to korean");

            // Prompt prompt = new Prompt(List.of(userMessage, systemMessage)); -> 필요 없다면 주석 처리
            response = chatModel.call(userMessage, systemMessage);
            log.info("Response = " + response);  // System.out.println 대신 로깅 사용
        } catch (Exception e) {
            log.error("Error occurred while processing chat request", e);
            return e.getMessage();  // 에러 메시지를 반환하도록 변경
        }
        return response;
    }
}
