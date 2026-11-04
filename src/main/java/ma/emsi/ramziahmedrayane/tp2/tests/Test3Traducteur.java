package ma.emsi.ramziahmedrayane.tp2.tests;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import java.time.Duration;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import java.util.Map;

public class Test3Traducteur {

    public static void main(String[] args) {

        String cle = System.getenv("GEMINI_KEY");

        ChatModel modele = GoogleGenAiChatModel.builder()
                .apiKey(cle)
                .modelName("gemini-3.5-flash-lite")
                .temperature(0.7)
                .timeout(Duration.ofSeconds(60)) // si service surchargé
                .responseFormat(ResponseFormat.JSON) // juste pour voir
                .build();
        PromptTemplate template = PromptTemplate.from(
                "Traduis le texte suivant en {{langue}} : {{texte}}");

        Prompt prompt = template.apply(Map.of(
                "langue", "espagnol",
                "texte", "Bonjour, je suis étudiant en informatique."));

        String reponse = modele.chat(prompt.text());
        System.out.println(reponse);

    }
}