# Utilisation de l’IA — TP 2

## Import de GoogleGenAiChatModel

J’ai demandé de l’aide à l’IA parce qu’IntelliJ ne reconnaissait pas l’import de GoogleGenAiChatModel. Le package utilisé était incorrect : il fallait utiliser dev.langchain4j.model.google.genai au lieu de dev.langchain4j.model.googleai.

## Dépendance BGE et Easy RAG

Après avoir commenté la dépendance BGE comme demandé dans le TP, je n’arrivais plus à lancer Test5Rag1. La compilation échouait parce que Test4Embeddings utilisait encore cette dépendance. L’IA m’a aidé à identifier le blocage et m’a proposé de déplacer Test4Embeddings dans un dossier archives, en dehors de src, pour conserver son code sans le compiler.