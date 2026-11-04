package ma.emsi.ramziahmedrayane.tp2.tests;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.bgesmallenv15.BgeSmallEnV15EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.CosineSimilarity;
import dev.langchain4j.model.google.genai.GoogleGenAiEmbeddingModel;
import java.time.Duration;

public class Test4Embeddings {

    public static void main(String[] args) {

        EmbeddingModel modele = new BgeSmallEnV15EmbeddingModel();
        EmbeddingModel modeleGoogle = GoogleGenAiEmbeddingModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-embedding-001")
                .taskType(GoogleGenAiEmbeddingModel.TaskTypeEnum.SEMANTIC_SIMILARITY)
                .timeout(Duration.ofSeconds(100))
                .build();

        String phrase1 = "Bonjour, comment allez-vous ?";
        String phrase2 = "Salut, quoi de neuf ?";

        Response<Embedding> reponse1 = modele.embed(phrase1);
        Response<Embedding> reponse2 = modele.embed(phrase2);

        Embedding emb1 = reponse1.content();
        Embedding emb2 = reponse2.content();

        double similarite = CosineSimilarity.between(emb1, emb2);

        System.out.println("Phrase 1 : " + phrase1);
        System.out.println("Phrase 2 : " + phrase2);
        System.out.println("Similarité cosinus : " + similarite);
        Embedding embGoogle1 = modeleGoogle.embed(phrase1).content();
        Embedding embGoogle2 = modeleGoogle.embed(phrase2).content();

        double similariteGoogle = CosineSimilarity.between(embGoogle1, embGoogle2);

        System.out.println("Similarité Google : " + similariteGoogle);
    }
}