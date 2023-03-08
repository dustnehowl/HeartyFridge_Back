package com.example.test.chatgpt.service;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatgptService {
    public String test(String accessToken, String text){
        OpenAiService service = new OpenAiService(accessToken);
        System.out.println(text);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(text)
                .model("ada")
                .echo(true)
                .build();
        List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();
        for(CompletionChoice choice : choices){
            System.out.println(choice.getText());
        }
        return choices.get(1).toString();
    }
}
