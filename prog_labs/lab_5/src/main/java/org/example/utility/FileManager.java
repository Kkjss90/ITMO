package org.example.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
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
            .registerTypeAdapter(Route.class, new CustomConverter())
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
                Console.println("Коллекция успешна сохранена в файл!");
            } catch (IOException exception) {
                Console.printerror("Загрузочный файл является директорией/не может быть открыт!");
            }
        } else Console.printerror("Системная переменная с загрузочным файлом не найдена!");
    }

    /**
     * Читает коллекцию из файла.
     * @return коллекция типа java.util.Vector.
     */
    public Vector<Route> readCollection() {
        if (System.getenv(envVariable) != null) {
            FileInputStream fileInputStream = null;
            BufferedInputStream bufferedInputStream = null;
            StringBuilder stringBuilder = null;
            try {
                fileInputStream = new FileInputStream(System.getenv(envVariable));
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                stringBuilder = new StringBuilder();
                while (bufferedInputStream.read()!=-1){
                    stringBuilder.append((char) bufferedInputStream.read());
                }
                Vector<Route> collection;
                Type collectionType = new TypeToken<Vector<Route>>() {}.getType();
                String json = Files.readString(Paths.get(System.getenv(envVariable)));
                collection = gson.fromJson(json, collectionType);
                Console.println("Коллекция успешна загружена!");
                return collection;
            } catch (FileNotFoundException exception) {
                Console.printerror("Загрузочный файл не найден!");
            } catch (NoSuchElementException exception) {
                Console.printerror("Загрузочный файл пуст!");
            } catch (JsonParseException | NullPointerException exception) {
                Console.printerror("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else Console.printerror("Системная переменная с загрузочным файлом не найдена!");
        return new Vector<Route>();
    }

    @Override
    public String toString() {
        String string = "FileManager (класс для работы с загрузочным файлом)";
        return string;
    }
}
