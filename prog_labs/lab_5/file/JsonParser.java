package file;
import collections.*;
import com.google.gson.*;
import java.util.Vector;

import java.io.InputStreamReader;
import java.io.Reader;

public class JsonParser{
    private Gson gson;
    public Route<> parseToCollection(String str){
        var collectionType = new TypeToken<Vector<Route>>() {}.getType();
        try{
            str = str.trim();
            Vector<Route> route = gson.fromJson(str, collectionType);
            console.println("Коллекция успешна загружена!");
            return route;
        }catch (IllegalStateException | IOException exception) {
            console.printError("Непредвиденная ошибка!");
            System.exit(0);
        }
        return new Vector<>();
    }

}
