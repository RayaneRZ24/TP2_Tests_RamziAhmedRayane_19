package ma.emsi.ramziahmedrayane.tp2.tests;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import java.time.Duration;

public class Test2Couts{

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
                        UserMessage.from("Je dois partir dans 2 jours à Paris ; j’y resterai 3 jours. Est-ce que je dois mettre un parapluie dans mes valises ?"))
                .build();

        ChatResponse reponse = modele.chat(requete);

        System.out.println(reponse.aiMessage().text());
        System.out.println("Tokens d'entrée : " + reponse.tokenUsage().inputTokenCount());
        System.out.println("Tokens de sortie : " + reponse.tokenUsage().outputTokenCount());
        System.out.println("Total : " + reponse.tokenUsage().totalTokenCount());

        double cout = (
                reponse.tokenUsage().inputTokenCount() * 0.30
                        + reponse.tokenUsage().outputTokenCount() * 2.50
        ) / 1_000_000;

        System.out.printf("Coût de la requête : %.8f $%n", cout);
        System.out.printf("Nombre de requêtes pour 1 dollar : %.0f%n", 1 / cout);
    }
}