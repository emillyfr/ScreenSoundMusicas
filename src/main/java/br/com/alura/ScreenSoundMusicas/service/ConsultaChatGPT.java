package br.com.alura.ScreenSoundMusicas.service;


import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.util.List;

public class ConsultaChatGPT {
    public static String obterInformacao(String texto){
        OpenAiService service  = new OpenAiService(System.getenv("OPENAI_API"));
        ChatCompletionRequest requisicao = ChatCompletionRequest.builder()
                .model("gpt-4o-mini")
                .messages(List.of(
                        new ChatMessage(
                                "user",
                                "Me fale sobre o artista resumidamente: " + texto
                        )
                ))
                .maxTokens(1000)
                .temperature(0.7)
                .build();
        var resposta = service.createChatCompletion(requisicao);
        return resposta.getChoices().get(0).getMessage().getContent();
    }

}
