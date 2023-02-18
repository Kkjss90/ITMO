package file.data;

import java.io.*;

/**
 * Класс, отвечающий за чтение данных из файла.
 */
public class Fs implements Data{
    private String filepath;
    public Fs(String filepath){
        this.filepath = filepath;
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
    @Override
    public String getData(){
        return getFromFile();
    }
}
