package managers;

import data.LabWork;

import java.time.LocalDateTime;
import java.util.TreeSet;

/**
 * Класс, управляющий самой коллекцией
 */
public class CollectionManager {
    private TreeSet<LabWork> labWorksCollection;
    private LocalDateTime lastInitialisationTime = null;
    private FileManager fileManager;

    public CollectionManager(FileManager fileManager){
        this.fileManager = fileManager;
        this.labWorksCollection = new TreeSet<>();
        lastInitialisationTime = LocalDateTime.now();
    }

    /**
     * Метод возвращает коллекцию, с которой работает пользователь
     * @return labWorksCollection
     */
    public TreeSet<LabWork> getCollection() {
        return labWorksCollection;
    }

    /**
     * Метод задаёт коллекцию, с которой будет работать пользователь
     * @param collection
     */
    public void setCollection(TreeSet<LabWork> collection) {
        this.labWorksCollection = collection;
    }

    /**
     * Метод возвращает дату и время инициализации коллекции
     * @return lastInitialisationTime
     */
    public LocalDateTime getInitTime() {
        return lastInitialisationTime;
    }

    /**
     * Метод возвращает тип коллекции
     * @return collectionType
     */
    public String collectionType(){
        return labWorksCollection.getClass().getName();
    }

    /**
     * Метод возвращает размер коллекции (количество элементов в нём)
     * @return collectionSize
     */
    public int collectionSize(){
        return labWorksCollection.size();
    }

    /**
     * Метод возвращает первый элемент в коллекции
     * @return first
     */
    public LabWork getFirst(){
        if (labWorksCollection.isEmpty()) {
            System.out.println("В коллекции нет элементов!");
            return null;
        } else {
            return labWorksCollection.first();
        }
    }

    /**
     * Метод возвращает последний элемент в коллекции
     * @return last
     */
    public LabWork getLast(){
        if (labWorksCollection.isEmpty()) {
            System.out.println("В коллекции нет элементов!");
            return null;
        } else {
            return labWorksCollection.last();
        }
    }

    /**
     * Возвращает элемент коллекции с таким же ID
     * @param id
     * @return labWork
     */
    public LabWork getSameId(int id){
        for (LabWork labWork : labWorksCollection) {
            if (labWork.getId() == id) return labWork;
        }
        System.out.println("В коллекции нет элементов с таким ID");
        return null;
    }

    /**
     * Метод добавляет элемент в коллекцию
     * @param labWork
     */
    public void addToCollection(LabWork labWork) {
        labWorksCollection.add(labWork);
    }

    /**
     * Метод удаляет элемент из коллекции
     * @param labWork
     */
    public void removeFromCollection(LabWork labWork) {
        labWorksCollection.remove(labWork);
    }

    /**
     * Метод удаляет из коллекции все элементы, меньшие заданного
     * @param comparableLabWork
     */
    public void removeLower(LabWork comparableLabWork){
        while (labWorksCollection.lower(comparableLabWork) != null){
            labWorksCollection.remove(labWorksCollection.lower(comparableLabWork));
        }
    }

    /**
     * Метод очищает коллекцию (удаляет все элементы)
     */
    public void clearCollecton(){
        labWorksCollection.clear();
    }

    /**
     * Метод генерирует значение ID для нового элемента в коллекции
     * @return lastID
     */
    public int generateNextId() {
        if (labWorksCollection.isEmpty()) {
            return 1;
        } else return labWorksCollection.last().getId() + 1;
    }

    /**
     * Метод сохраняет (загружает) коллекцию в XML-файл
     */
    public void saveCollection() {
        fileManager.fromCollectionToXML(labWorksCollection);
    }

    /**
     * Метод выводить коллекцию в строковом формате
     * @return CollectionToString
     */
    @Override
    public String toString() {
        if (labWorksCollection.isEmpty()) return "Колекция пуста!";
        String str = "Все элементы коллекции:";
        for (LabWork labWork : labWorksCollection) {
            str = str + "\n\n" + labWork;
        }
        return str;
    }
}
