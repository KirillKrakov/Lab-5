package managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс, управляющий чтением введённых в консоль команд
 */
public class ConsoleManager {
    private CommandManager commandManager;
    private BufferedReader bufferedReader;
    private static int scriptCount = 0;
    private BufferedReader previousBufferedReader;

    public ConsoleManager(CommandManager commandManager, BufferedReader bufferedReader) {
        this.commandManager = commandManager;
        this.bufferedReader = bufferedReader;
    }

    /**
     * Метод используется для считывания и выполнения команд, введённых в консоль построчно
     */
    public void interactiveMode() {
        String[] userCommand = {"", ""};
        try {
            while (true) {
                System.out.print(">>> ");
                try {
                    userCommand = (bufferedReader.readLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                    commandManager.doCommand(userCommand[0], userCommand[1]);
                    commandManager.addToHistory(userCommand[0]);
                    if (userCommand[0].equals("exit")) break;
                    if (userCommand[0].equals("execute_script")) {
                        scriptCount += 1;
                        BufferedReader br = new BufferedReader(new FileReader(userCommand[1]));
                        scriptMode(br);
                    }
                } catch (NullPointerException ex) {
                    printError("ctrl d");
                    break;
                }
            }
        } catch (IOException exc) {
            printError("Операция ввода была прервана!");
        }
    }

    /**
     * Метод используется для считывания и выполнения скрипта
     * @param br reader файла со скриптом
     */
    public void scriptMode(BufferedReader br) {
        String[] userCommand = {"", ""};
        try {
            RequestManager rm = new RequestManager(br);
            while (true) {
                try {
                    userCommand = (br.readLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                    System.out.println(">>>" + userCommand[0] + " " + userCommand[1]);
                    commandManager.setRequestCommand(commandManager.getRequestCommand(userCommand[0]),rm);
                    commandManager.addToHistory(userCommand[0]);
                    if (userCommand[0].equals("exit")) break;
                    commandManager.doCommand(userCommand[0], userCommand[1]);
                    if (userCommand[0].equals("execute_script")) {
                        scriptCount += 1;
                        previousBufferedReader = br;
                        BufferedReader br2 = new BufferedReader(new FileReader(userCommand[1]));
                        scriptMode(br2);
                    }
                } catch (NullPointerException ex) {
                    break;
                }
            }
            System.out.println("$\nСкрипт успешно выполнен!");
            scriptCount -= 1;
            if (scriptCount >= 1) {
                scriptMode(previousBufferedReader);
            }
            interactiveMode();
        } catch (FileNotFoundException ex) {
            printError("Файл с таким именем не найден!");
        } catch (IOException ex) {
            printError("Операция ввода была прервана! Проверьте указанный путь до файла.");
        }
    }

    /**
     * Метод выводит ошибки в строковом представлении
     * @param argument описание ошибки в строковом представлении
     */
    public static void printError(String argument) {
        System.out.println("ОШИБКА: " + argument);
    }
}
