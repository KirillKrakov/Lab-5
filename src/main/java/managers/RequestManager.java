package managers;

import data.Coordinates;
import data.Difficulty;
import data.Person;
import exceptions.LowerThanMinValueException;
import exceptions.MustBeNotEmptyException;
import exceptions.NotEnumElementException;
import exceptions.WrongAnswerFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Класс, запрашивающий у пользователя ввод значений элементов объекта - Лабораторной работы
 */
public class RequestManager {
    private BufferedReader reader;

    public RequestManager(BufferedReader reader){
        this.reader = reader;
    }

    /**
     * Метод задаёт объект класса Reader, предназназначенный для считывания ввода
     * @param reader объект класса BufferedReader
     */
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    /**
     * Метод возвращает используемый объект класса Reader
     * @return
     */
    public BufferedReader getReader() {
        return reader;
    }

    /**
     * Метод запрашивает у пользователя название Лабораторной работы
     * @return name
     */
    public String requestName(){
        String name;
        while(true) {
            try {
                System.out.println("Введите название:");
                name = reader.readLine().trim();
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch(IOException ex){
                ConsoleManager.printError("Операция ввода была прервана!");
            } catch (MustBeNotEmptyException ex){
                ConsoleManager.printError("Название не должно быть пустым!");
            }
        }
        return name;
    }

    /**
     * Метод запрашивает у пользователя координату X
     * @return x
     */
    public Double requestX() {
        String strX;
        Double x;
        while (true){
            try{
                System.out.println("Введите координату X:");
                strX = reader.readLine().trim();
                if (strX.equals("")) throw new MustBeNotEmptyException();
                x = Double.parseDouble(strX);
                if (x <= -711) throw new LowerThanMinValueException();
                break;
            } catch(IOException ex) {
                ConsoleManager.printError("Операция ввода была прервана!");
            } catch (MustBeNotEmptyException ex) {
                ConsoleManager.printError("Значение не может быть null!");
            } catch (LowerThanMinValueException ex) {
                ConsoleManager.printError("Значение должно быть больше -711!");
            } catch (NumberFormatException ex) {
                ConsoleManager.printError("Вводимое значение должно быть числом формата Double!");
            }
        }
        return x;
    }

    /**
     * Метод запрашивает у пользователя координату Y
     * @return y
     */
    public Long requestY() {
        String strY;
        Long y;
        while (true){
            try{
                System.out.println("Введите координату Y:");
                strY = reader.readLine().trim();
                if (strY.equals("")) throw new MustBeNotEmptyException();
                y = Long.parseLong(strY);
                break;
            } catch(IOException ex) {
                ConsoleManager.printError("Операция ввода была прервана!");
            } catch (MustBeNotEmptyException ex) {
                ConsoleManager.printError("Значение не может быть null!");
            } catch (NumberFormatException ex) {
                ConsoleManager.printError("Вводимое значение должно быть числом формата Long!");
            }
        }
        return y;
    }

    /**
     * Метод запрашивает у пользователя координаты
     * @return Coordinates
     */
    public Coordinates requestCoordinates() {
        Double x;
        Long y;
        x = requestX();
        y = requestY();
        return new Coordinates(x, y);
    }

    /**
     * Метод запрашивает у пользователя минимальный балл за Лабораторную работу
     * @return minPoint
     */
    public int requestMinimalPoint() {
        String strMinPoint;
        int minPoint;
        while (true){
            try {
                System.out.println("Введите минимальный балл:");
                strMinPoint = reader.readLine().trim();
                minPoint = Integer.parseInt(strMinPoint);
                if (minPoint <= 0) throw new LowerThanMinValueException();
                break;
            } catch(IOException ex) {
                ConsoleManager.printError("Операция ввода была прервана!");
            } catch (LowerThanMinValueException ex) {
                ConsoleManager.printError("Значение должно быть больше 0!");
            } catch (NumberFormatException ex) {
                ConsoleManager.printError("Вводимое значение должно быть числом формата int!");
            }
        }
        return minPoint;
    }

    /**
     * Метод запрашивает у пользователя минимум персональных качеств
     * @return perQualMin
     */
    public Float requestPersonalQualitiesMinimum() {
        String strPersQualMin;
        Float persQualMin;
        while (true){
            try{
                System.out.println("Введите необходимый минимум личных качеств:");
                strPersQualMin = reader.readLine().trim();
                if (strPersQualMin.equals("")) throw new MustBeNotEmptyException();
                persQualMin = Float.parseFloat(strPersQualMin);
                if (persQualMin <= 0) throw new LowerThanMinValueException();
                break;
            } catch(IOException ex) {
                ConsoleManager.printError("Операция ввода была прервана!");
            } catch (LowerThanMinValueException ex) {
                ConsoleManager.printError("Значение должно быть больше 0!");
            } catch (NumberFormatException ex) {
                ConsoleManager.printError("Вводимое значение должно быть числом формата Float!");
            } catch (MustBeNotEmptyException ex) {
                ConsoleManager.printError("Значение не может быть null!");
            }
        }
        return persQualMin;
    }

    /**
     * Метод запрашивает у пользователя средний балл за Лабораторную работу
     * @return averPoint
     */
    public long requestAveragePoint() {
        String strAverPoint;
        long averPoint;
        while (true) {
            try {
                System.out.println("Введите средний балл:");
                strAverPoint = reader.readLine().trim();
                averPoint = Long.parseLong(strAverPoint);
                if (averPoint <= 0) throw new LowerThanMinValueException();
                break;
            } catch(IOException ex) {
                ConsoleManager.printError("Операция ввода была прервана!");
            } catch (LowerThanMinValueException ex) {
                ConsoleManager.printError("Значение должно быть больше 0!");
            } catch (NumberFormatException ex) {
                ConsoleManager.printError("Вводимое значение должно быть числом формата long!");
            }
        }
        return averPoint;
    }

    /**
     * Метод запрашивает у пользователя имя автора
     * @return name
     */
    public String requestPersonName() {
        String name;
        while (true) {
            try {
                System.out.println("Введите имя автора");
                name = reader.readLine().trim();
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch(IOException ex){
                ConsoleManager.printError("Операция ввода была прервана!");
            } catch (MustBeNotEmptyException ex){
                ConsoleManager.printError("Имя не должно быть пустым!");
            }
        }
        return name;
    }

    /**
     * Метод запрашивает у пользователя дату рождения автора
     * @return date
     */
    public LocalDateTime requestPersonBirthday() {
        String strDate;
        LocalDateTime date;
        while (true) {
            try {
                System.out.println("Введите дату рождения автора");
                strDate = reader.readLine().trim();
                if (strDate.equals("")) throw new MustBeNotEmptyException();
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                date = LocalDateTime.parse(strDate, formatter);
                break;
            } catch(IOException ex){
                ConsoleManager.printError("Операция ввода была прервана!");
            } catch (MustBeNotEmptyException ex){
                ConsoleManager.printError("Имя не должно быть пустым!");
            } catch (DateTimeParseException ex) {
                ConsoleManager.printError("Возникла ошибка! Проверьте правильность формата ввода: YYYY-MM-DDThh:mm:ss");
            }
        }
        return date;
    }

    /**
     * Метод запрашивает у пользователя паспортные данные автора
     * @return passport
     */
    public String requestPersonPassportID() {
        String passport;
        while (true) {
            try {
                System.out.println("Введите паспортные данные автора:");
                passport = reader.readLine().trim();
                if (passport.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch(IOException ex){
                ConsoleManager.printError("Операция ввода была прервана!");
            } catch (MustBeNotEmptyException ex){
                ConsoleManager.printError("Имя не должно быть пустым!");
            }
        }
        return passport;
    }

    /**
     * Метод запрашивает у пользователя автора Лабораторной работы
     * @return Person
     */
    public Person requestPerson() {
        String name;
        LocalDateTime birthday;
        String passportID;
        name = requestPersonName();
        birthday = requestPersonBirthday();
        passportID = requestPersonPassportID();
        return new Person(name,birthday,passportID);
    }

    /**
     * Метод запрашивает у пользователя сложность Лабораторной работы
     * @return diff
     */
    public Difficulty requestDifficulty() {
        String strDiff;
        Difficulty diff;
        while (true) {
            try {
                System.out.println("Введите уровень сложности работы: HOPELESS, TERRIBLE, VERY_EASY");
                strDiff = reader.readLine().trim();
                if (strDiff.equals("")) throw new MustBeNotEmptyException();
                strDiff = strDiff.toUpperCase();
                if (!(strDiff.equals("VERY_EASY") || strDiff.equals("HOPELESS") || strDiff.equals("TERRIBLE"))) throw new NotEnumElementException();
                diff = Difficulty.valueOf(strDiff);
                break;
            } catch(IOException ex){
                ConsoleManager.printError("Операция ввода была прервана!");
            } catch (MustBeNotEmptyException ex){
                ConsoleManager.printError("Имя не должно быть пустым!");
            } catch (NotEnumElementException ex){
                ConsoleManager.printError("Введенное значение некорректно! Проверьте, что вы ввели один из 3 вариантов: HOPELESS, TERRIBLE, VERY_EASY");
            }
        }
        return diff;
    }

    /**
     * Метод запрашивает у пользователя ответ на вопрос, необходимо ли обновление данного параметра
     * @param argument
     * @return x - логическая переменная, являющаяся ответом на вопрос. true - ДА, false - НЕТ.
     */
    public boolean requestUpdateQuestion(String argument) {
        String question = argument + "  ДА/НЕТ";
        String answer;
        boolean x;
        while (true) {
            try {
                System.out.println(question);
                answer = reader.readLine().trim();
                if (answer.equals("ДА")) x = true;
                else if(answer.equals("НЕТ")) x = false;
                else throw new WrongAnswerFormatException();
                break;
            } catch(IOException exc){
                ConsoleManager.printError("Операция ввода была прервана!");
            } catch (WrongAnswerFormatException exc) {
                ConsoleManager.printError("Ответ должен быть 'ДА' или 'НЕТ'!");
            }
        }
        return x;
    }
}
