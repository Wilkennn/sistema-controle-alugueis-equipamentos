package src.entities;

public class Constants {

    public static final String ALUGUEL_PATH_FILE = "../resources/Aluguel.json";
    public static final String IMPORT_GSON_PATH_FILE = "../lib/gson-gson-parent-2.10.1/gson/src/main/java/com/google/gson/Gson.java";
    public static final String IMPORT_GSON_BUILDER_PATH_FILE = "../lib/gson-gson-parent-2.10.1/gson/src/main/java/com/google/gson/GsonBuilder.java";
    
    // Construtor privado para evitar instanciar a classe de constantes
    private Constants() {
        throw new AssertionError("Esta classe não deve ser instanciada.");
    }
}
