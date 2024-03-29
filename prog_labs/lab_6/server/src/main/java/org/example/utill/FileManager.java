package org.example.utill;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.example.ServerApp;
import org.example.data.Route;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * Класс, отвечающий за загрузку/выгрузку коллекции из файла/в файл.
 */
public class FileManager {
    /**
     * Парсер формата json
     */
    private Gson gson = new GsonBuilder()
            .setDateFormat("SSS")
            .registerTypeAdapter(Route.class, new org.example.utility.CustomConverter())
            .create();
    /**
     * Переменная окружения
     */
    private String envVariable;
    /**
     * Устанавливает имя файла.
     * @param filename имя файла
     */
    public void setFilename(String filename) {
        this.envVariable = filename;
    }

    /**
     * Конструктор класса
     * @param envVariable переменная окружения
     */
    public FileManager(String envVariable) {
        this.envVariable = envVariable;
    }

    /**
     * Запись коллекции в файл.
     * @param collection Коллекция для записи.
     */
    public void writeCollection(Collection<?> collection) {
        if (System.getenv(envVariable)!= null) {
            try (BufferedWriter collectionFileWriter = new BufferedWriter(new FileWriter(System.getenv(envVariable)))) {
                collectionFileWriter.write(gson.toJson(collection));
                ServerApp.logger.info("Коллекция успешна сохранена в файл!");
            } catch (IOException exception) {
                ServerApp.logger.warn("Загрузочный файл является директорией/не может быть открыт!");
            }
        } else ServerApp.logger.warn("Системная переменная с загрузочным файлом не найдена!");
    }

    /**
     * Переменная, в которой хранится путь к папке с файлами.
     */
    public static String path;

    /**
     * Метод для получения имени файла из переменной окружения.
     * @return имя файла
     */
    public static String getFileName() {
        try {
            path = System.getenv("LAB5");
            String[] checkPaths = path.split(";");
            if (checkPaths.length > 1) {
                ServerApp.logger.warn("В этой переменной содержится более одного пути к файлам. Работа сервера завершена.");
                System.exit(1);
            }
        } catch (NullPointerException e) {
            ServerApp.logger.warn("Некорректная переменная окружения. Работа сервера завершена.");
            System.exit(1);
        }
        File name = new File(path);
        return name.getName();
    }

    /**
     * Читает коллекцию из файла.
     * @return коллекция типа java.util.Vector.
     */
    public Vector<Route> readCollection() {
        path = System.getenv("LAB5");
        if (path != null) {
            FileInputStream fileInputStream = null;
            BufferedInputStream bufferedInputStream = null;
            StringBuilder stringBuilder = null;
            try {
                fileInputStream = new FileInputStream(path);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                stringBuilder = new StringBuilder();
                while (bufferedInputStream.read()!=-1){
                    stringBuilder.append((char) bufferedInputStream.read());
                }
                Vector<Route> collection;
                Type collectionType = new TypeToken<Vector<Route>>() {}.getType();
                String json = Files.readString(Paths.get(path));
                collection = gson.fromJson(json, collectionType);
                ServerApp.collectionManager.setCollection(collection);
                ServerApp.logger.info("Коллекция успешна загружена!");
                return collection;
            } catch (FileNotFoundException exception) {
                ServerApp.logger.warn("Загрузочный файл не найден!");
            } catch (NoSuchElementException exception) {
                ServerApp.logger.warn("Загрузочный файл пуст!");
            } catch (JsonParseException | NullPointerException exception) {
                ServerApp.logger.warn("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException exception) {
                ServerApp.logger.warn("Непредвиденная ошибка!");
                System.exit(0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else ServerApp.logger.warn("Системная переменная с загрузочным файлом не найдена!");
        return new Vector<Route>();
    }

    @Override
    public String toString() {
        String string = "FileManager (класс для работы с загрузочным файлом)";
        return string;
    }
}
