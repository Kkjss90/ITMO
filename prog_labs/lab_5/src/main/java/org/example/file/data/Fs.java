package org.example.file.data;

import java.io.*;

/**
 * Класс, отвечающий за чтение/запись данных из файла/в файл.
 */
public class Fs implements Data{
    private String filepath;
    private String env;
    public Fs(String filepath, String env){
        this.filepath = filepath;
        this.env = env;
    }
    /**
     * Метод, производящий чтение данных из указанного файла. В случае критических ошибок программа завершает свою работу.
     *
     * @param filepath файл, из которого следует читать данные
     * @return строка, которая хранит все содержимое данного файла
     */
    public String getFromFile(){
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try{
            fileInputStream = new FileInputStream(filepath);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            stringBuilder = new StringBuilder();
            int c;
            while ((c = bufferedInputStream.read())!=1){
                stringBuilder.append((char) c);
            }
        }catch (IOException e) {
            System.err.println("Произошла ошибка при добавлении файла во входящий поток " + e);
            System.exit(-1);
        }catch (NullPointerException e) {
            System.err.println("Не указан файл, из которого следует читать данные " + e);
            System.exit(-1);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Произошла ошибка при закрытии " + e);
            }
        }
        return stringBuilder.toString();
    }
    /**
     * Метод, производящий запись данных в указанный файл.
     *
     * @param filepath файл, куда следует записывать данные
     * @param str      строка, которую следует записать в файл
     */
    public void writeToFile(String str) {
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = new FileOutputStream(filepath);
            bufferedWriter = new BufferedWriter(new PrintWriter(fileOutputStream));
            bufferedWriter.write(str);
        }catch (IOException e) {
            System.err.println("Произошла ошибка при добавлении файла в исходящий поток\n" + e);
        }catch (NullPointerException e){
            System.err.println("Не указан файл, куда следует записывать данные " + e);
        }finally {
            try{
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Произошла ошибка при закрытии " + e);
            }
        }
    }

    /**
     * Метод, производящий чтение данных.
     */
    @Override
    public String getData(){
        getFromFile();
        return "Изъятие данных из файла";
    }

    /**
     * Метод, производящий запись данных.
     */
    @Override
    public String pullData(String str) {
        writeToFile(str);
        return "Запись данных в файл";
    }
}
