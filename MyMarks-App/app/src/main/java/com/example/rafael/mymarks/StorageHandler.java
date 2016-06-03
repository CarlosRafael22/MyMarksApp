package com.example.rafael.mymarks;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Rafael on 06/11/2015.
 */
public class StorageHandler extends Activity {

    private static final String TAG = StorageHandler.class.getSimpleName();

    private static final String FILE_NAME = "categories.txt";

    Context myContext;

    public void setContext(Context context){
        myContext = context;
    }

    public void writeDatainStorage(String data_to_be_stored){

        try {

            //Necessario pra salvar em Internal Storage, tem que escrever em Bytes no OutputStream
            // e dps fecha-lo
            FileOutputStream fileOutputStream = myContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(data_to_be_stored.getBytes());
            fileOutputStream.close();
            Log.i(TAG, "Escreveu Internal");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String readDatainStorage() throws IOException {

        String categories_json;
        String response = "";
        FileInputStream fileInputStream = null;



        try {
            fileInputStream = myContext.openFileInput(FILE_NAME);

            if(fileInputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();

                while ( (categories_json=bufferedReader.readLine() ) != null){
                    stringBuffer.append(categories_json);
                }



                inputStreamReader.close();
                response = stringBuffer.toString();

                Log.i(TAG, response);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return response;

    }

    public ArrayList<Category> getCategoriesInStorage(){

        String categories_returned = "";
        //Pega as categorias que estao no arquivo com Json
        //Tentando pegar os dados salvos em Internal Storage
        try {
            Log.i(TAG, "Tentando ler Data Storage");
            categories_returned = readDatainStorage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Transforma o Json do arquivo em ArrayList<Category> com o Gson
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<Category>>(){}.getType();
        ArrayList<Category> categories = gson.fromJson(categories_returned, collectionType);

        return categories;
    }

}
