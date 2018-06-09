/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.*;
import java.io.IOException;
import java.util.List;


/**
 *
 * @author Nhan
 */
public class LoadJSon {

    Gson loader;
    File jsonInputFile;// = new File("/Users/java2novice/jsonInput.txt");
    ItemData item;
    FileReader reader;
    int i;
    String store = "";
    String line = "";
    public LoadJSon()
    {
        loader = new Gson();
        try {
            reader = new FileReader("src/Items.json");
            
            //ItemData iitem  = loader.fromJson(reader, ItemData.class);
            while ((i = reader.read()) != -1) {
                    line = "" + (char)i;
                    store = store + line;
                    line = "";
            }
            //System.out.println(loader.fromJson(store, ItemData.class).name);
            
            //item  = loader.fromJson(store, ItemData.class);
            ItemManager.listItem = loader.fromJson(store, new TypeToken<List<ItemData>>(){}.getType());   
            reader.close();
            
            //System.out.println("item Name: " + item.name);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
