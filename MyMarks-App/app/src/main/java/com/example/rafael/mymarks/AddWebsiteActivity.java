package com.example.rafael.mymarks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.style.EasyEditSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class AddWebsiteActivity extends Activity {

    private static final String TAG = CategoryActivity.class.getSimpleName();

    private EditText website, website_link;
    private String position = "";
    private static final String FILE_NAME = "categories.txt";

    StorageHandler storageHandler = new StorageHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_website);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Context context = getApplicationContext();
        storageHandler.setContext(context);


        website = (EditText) findViewById(R.id.add_website_text2);
        website_link = (EditText) findViewById(R.id.add_website_link2);

        Button button = (Button) findViewById(R.id.add_website_button2);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){



        String website_name = website.getText().toString();
        String link = website_link.getText().toString();

        //Limpa os EditTexts
        website.setText("");
        website_link.setText("");

        String position = getIntent().getStringExtra("position");

        //Criando o Website obj pra dps adicionar na categoria
        Website website_obj = new Website(website_name,link);


        //Adicionou website nessa categoria
        int position_number = Integer.parseInt(position);

        ArrayList<Category> categories = storageHandler.getCategoriesInStorage();

        //Com isso eu peguei as categorias do Internal Storage
        // Agora vou pegar os websites

        categories.get(position_number).addWebsite(website_obj);


        // categories.get(position_number).addWebsite(website_obj);




        //Agora eu vou tentar salvar a estrutura toda pra Internal Storage
        Gson gson = new Gson();
        String categories_json = gson.toJson(categories);
        Log.i(TAG, categories_json);

        storageHandler.writeDatainStorage(categories_json);

        Intent intent = new Intent(AddWebsiteActivity.this,CategoryActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


}
