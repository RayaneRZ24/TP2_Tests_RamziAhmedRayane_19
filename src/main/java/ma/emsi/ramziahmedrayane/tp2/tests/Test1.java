package ma.emsi.ramziahmedrayane.tp2.tests;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import java.time.Duration;

public class Test1 {

    public static void main(String[] args) {

        String cle = System.getenv("GEMINI_KEY");

        ChatModel modele = GoogleGenAiChatModel.builder()
                .apiKey(cle)
                .modelName("gemini-3.5-flash-lite")
                .temperature(0.7)
                .timeout(Duration.ofSeconds(60)) // si service surchargé
                .responseFormat(ResponseFormat.JSON) // juste pour voir
                .build();

        ChatRequest requete = ChatRequest.builder()
                .temperature(0.7) // juste pour cette requête
                .messages(
                        UserMessage.from("Comment s’appelle le chat de Pierre ?"))
                .build();

        ChatResponse reponse = modele.chat(requete);

        System.out.println(reponse.aiMessage().text());
        System.out.println(reponse.tokenUsage().totalTokenCount());
    }
}