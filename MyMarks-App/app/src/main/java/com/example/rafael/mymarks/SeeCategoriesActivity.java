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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SeeCategoriesActivity extends AppCompatActivity {

    private static final String TAG = SeeCategoriesActivity.class.getSimpleName();
    private static final String FILE_NAME = "categories";

    ListView listView;
    StorageHandler storageHandler = new StorageHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i(TAG, "Creating Activity2");
        Intent intent = getIntent();
        final ArrayList<Category> categories = intent.getParcelableArrayListExtra("categories");

        Context context = getApplicationContext();
        storageHandler.setContext(context);

        ArrayList<Category> categories_returned = storageHandler.getCategoriesInStorage();

        Log.i(TAG, String.format("Categorias na AcT2: %s", categories_returned.toString()));

        listView = (ListView) findViewById(R.id.category_list);

        // ISSO FOI UM WORKAROUND PRA NAO TER QUE FAZER UM CUSTOM ARRAYADAPTER AI EU PASSEI AS STRINGS DE CADA CATEGORIA
        // PARA UMA ARRAYLIST QUE VAI SER ACESSADA AO CRIAR O LISTVIEW, ASSIM MOSTRA
        ArrayList<String> categories_names = new ArrayList<String>();

        //Pegando Internal Storage e nao do Intent
        for(Category category:categories_returned){
            categories_names.add(category.getCategoryName());
        }

        //Criando o ArrayAdapter que liga a essa ViewGroup e cria views para a list_item e texview
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,categories_names);
//
//        //Binding o adapter a listView definida no XML
//        listView.setAdapter(adapter);

        CategoryListAdapter adapter = new CategoryListAdapter(this,categories_returned);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Log.i(TAG, String.valueOf(position));
                TextView tvCategory_name = (TextView) findViewById(R.id.category_list_name);
                Toast.makeText(getApplicationContext(),
                        tvCategory_name.getText(), Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(SeeCategoriesActivity.this,CategoryActivity.class);

                intent.putExtra("position",String.valueOf(position));
                intent.putExtra("categories",categories);

                startActivity(intent);
            }
        });


        //Log.i(TAG, categories.get(0).getCategoryName());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void onBackPressed() {
        // INTENT FOR YOUR HOME ACTIVITY
        Intent intent = new Intent(SeeCategoriesActivity.this,EntryActivity.class);
        startActivity(intent);
    }


    @Override
    public void onStart() {
        super.onStart();

        // Emit LogCat message
        Log.i(TAG, "Entered the onStart() method");

        // TODO:
        // Update the appropriate count variable
        // Update the user interface

    }


}
