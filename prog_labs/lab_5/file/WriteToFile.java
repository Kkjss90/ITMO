package file;

import java.io.*;

/**
 * Класс, отвечающий за запись данных в файл.
 */
public class WriteToFile {
    private String filepath;
    private String str;
    public WriteToFile(String filepath, String str){
        this.filepath=filepath;
        this.str=str;
    }
    /**
     * Метод, производящий запись данных в указанный файл.
     *
     * @param filepath файл, куда следует записывать данные
     * @param str      строка, которую следует записать в файл
     */
    public void writeToFile() {
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
}
