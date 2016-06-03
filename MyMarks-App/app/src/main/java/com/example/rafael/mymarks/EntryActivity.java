package com.example.rafael.mymarks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class EntryActivity extends AppCompatActivity {

    //Int pra ser mandado com o ActivityForResult pra pegar a nova categoria criada
    static final int GET_NEW_CATEGORY = 1;
    private static final String TAG = EntryActivity.class.getSimpleName();

    private static final String FILE_NAME = "categories";

    TextView testView;

    final StorageHandler storageHandler = new StorageHandler();

    static ArrayList<Category> categories = new ArrayList<Category>();

    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        file = new File(getFilesDir().getPath()+"/categories.txt");

        Context context = getApplicationContext();
        storageHandler.setContext(context);

        Button see_category = (Button) findViewById(R.id.see_categories_button);
        see_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String checagem = "Oi";
//                try {
//                    checagem = storageHandler.readDatainStorage();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


                if (file.exists()) {
//                if(!checagem.equals("")){
                    Intent intent = new Intent(EntryActivity.this, SeeCategoriesActivity.class);
//                Log.i(TAG, categories.get(0).getCategoryName());
                    intent.putExtra("categories", categories);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Create a category", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button add_category = (Button) findViewById(R.id.add_categories_button);
        add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntryActivity.this, AddCategoryActivity.class);
                startActivityForResult(intent,GET_NEW_CATEGORY);

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == GET_NEW_CATEGORY) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String category_name = data.getExtras().getString("new_category");
                Category new_category = new Category(category_name);
                testView = (TextView) findViewById(R.id.testView);



                //Se eh a primeira vez ainda nao escreveu nada no File entao vai dar erro

                if(!file.exists()){
                    categories.add(new_category);

                    Gson gson = new Gson();
                    String categories_json = gson.toJson(categories);
                    Log.i(TAG, categories_json);

                    storageHandler.writeDatainStorage(categories_json);
                }else{

                    ArrayList<Category> categoriesStorage = storageHandler.getCategoriesInStorage();

                    categoriesStorage.add(new_category);
                    categories.add(new_category);

                    //Agora eu vou tentar salvar a estrutura toda pra Internal Storage
                    Gson gson = new Gson();
                    String categories_json = gson.toJson(categoriesStorage);
                    Log.i(TAG, categories_json);

                    storageHandler.writeDatainStorage(categories_json);

                    Log.i(TAG, categoriesStorage.toString());

                }


//                String checagem = "Oi";
//                try {
//                    checagem = storageHandler.readDatainStorage();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                if(checagem.equals("")){
//                    categories.add(new_category);
//
//                    Gson gson = new Gson();
//                    String categories_json = gson.toJson(categories);
//                    Log.i(TAG, categories_json);
//
//                    storageHandler.writeDatainStorage(categories_json);
//                }else{
//
//                    ArrayList<Category> categoriesStorage = storageHandler.getCategoriesInStorage();
//
//                    categoriesStorage.add(new_category);
//                    categories.add(new_category);
//
//                    //Agora eu vou tentar salvar a estrutura toda pra Internal Storage
//                    Gson gson = new Gson();
//                    String categories_json = gson.toJson(categoriesStorage);
//                    Log.i(TAG, categories_json);
//
//                    storageHandler.writeDatainStorage(categories_json);
//
//                    Log.i(TAG, categoriesStorage.toString());
//                }

                testView.setText(new_category.getCategoryName());

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
