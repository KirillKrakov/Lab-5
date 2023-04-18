
import managers.*;

import java.io.*;

import static java.lang.System.getenv;

public class Main {
    public static void main(String[] args) {
        String envVar = getenv("lab5");
        System.out.println("Добро пожаловать!");
        System.out.println("Используемый файл: " + envVar);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        RequestManager requestManager = new RequestManager(bufferedReader);
        FileManager fileManager = new FileManager(envVar);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        CommandManager commandManager = new CommandManager(requestManager, collectionManager);
        ConsoleManager consoleManager = new ConsoleManager(commandManager, bufferedReader);
        consoleManager.interactiveMode();
    }
}