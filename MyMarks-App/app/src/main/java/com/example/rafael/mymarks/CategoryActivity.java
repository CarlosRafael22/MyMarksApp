package com.example.rafael.mymarks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

public class CategoryActivity extends AppCompatActivity {

    private static final String TAG = CategoryActivity.class.getSimpleName();

    private EditText website, website_link;
    private String position = "";
    private static final String FILE_NAME = "categories";


    ListView listView;
    StorageHandler storageHandler = new StorageHandler();

    //ArrayList<Category> categories = new ArrayList<Category>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i(TAG, "Creating Activity3");

        listView = (ListView) findViewById(R.id.website_list);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getString("position");



//        categories = bundle.getParcelableArrayList("categories");


//        categories = intent.getParcelableArrayListExtra("categories");
//        position = intent.getStringExtra("position");


        displayWebsites(position);

//        website = (EditText) findViewById(R.id.add_website_text);
//        website_link = (EditText) findViewById(R.id.add_website_link);

//        Button button = (Button) findViewById(R.id.add_website_button);
//
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//                String website_name = website.getText().toString();
//                String link = website_link.getText().toString();
//
//                //Limpa os EditTexts
//                website.setText("");
//                website_link.setText("");
//
//                //Criando o Website obj pra dps adicionar na categoria
//                Website website_obj = new Website(website_name,link);
//
//
//                //Adicionou website nessa categoria
//                int position_number = Integer.parseInt(position);
//
//
//                //Eu tava pegando as categorias do Intent mas eu nao tava salvando mais no intent
//                //desde que eu fui pra SeeCategories pela primeira vez, ai todas os websites que criei eu nao salvei no intent
//                //e sim no Internal storage por isso que qd ia pegar as categorias e websites de novo ele voltava com nenhum website
//                //eu tenho que pegar do Internal Storage que ta atualizado
//
//                String categories_returned = "";
//                //Tentando pegar os dados salvos em Internal Storage
//                try {
//                    categories_returned = readDatainStorage();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                Gson gson = new Gson();
//                Type collectionType = new TypeToken<Collection<Category>>(){}.getType();
//                ArrayList<Category> categories = gson.fromJson(categories_returned, collectionType);
//
//                //Com isso eu peguei as categorias do Internal Storage
//                // Agora vou pegar os websites
//
//                categories.get(position_number).addWebsite(website_obj);
//
//
//               // categories.get(position_number).addWebsite(website_obj);
//
//
//
//
//                //Agora eu vou tentar salvar a estrutura toda pra Internal Storage
//
//                String categories_json = gson.toJson(categories);
//                Log.i(TAG, categories_json);
//
//                writeDatainStorage(categories_json);
//
//
//                //Dps de adicionar um novo website tem que chamar pra mostrar de novo
//                displayWebsites(position);
//            }
//        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();




                //Criando o AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(CategoryActivity.this);
                builder.setTitle("Add a website");

                final EditText eTwebsite_name = new EditText(CategoryActivity.this);
                eTwebsite_name.setHint("Website name");
                final EditText eTwebsite_link = new EditText(CategoryActivity.this);
                eTwebsite_link.setHint("Website link");

                eTwebsite_name.setInputType(InputType.TYPE_CLASS_TEXT);
                eTwebsite_link.setInputType(InputType.TYPE_CLASS_TEXT);

                //Criando um LinearLayout que vai ter os EditTexts e vai ser o View do AlertDialog
                LinearLayout layout = new LinearLayout(CategoryActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                //Pra setar as margins nao tem como setar isso em Views, como EditText deriva de View nao da
                //so da pra setar em ViewGroups(LinearLayouts,RelatieLayouts...) entao tem que pegar o
                //LinearLayout e fazer isso de baixo:
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(15,0,15,0);
                layout.addView(eTwebsite_name);
                layout.addView(eTwebsite_link);
                builder.setView(layout);


                // Set up the buttons
                builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        ///////////////////////////////////////////////////////////////////////////////////

                        //AGORA VAI PEGAR O QUE FOI ESCRITO NOS EDITTEXTS E SALVAR, ERA O QUE FAZIA
                        //NO ADDWEBSITEACITIVITY

                        ///////////////////////////////////////////////////////////////////////////////////

                        String website_name = eTwebsite_name.getText().toString();
                        String link = eTwebsite_link.getText().toString();

//                //Limpa os EditTexts
//                website.setText("");
//                website_link.setText("");

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

                        Intent intent = new Intent(CategoryActivity.this,CategoryActivity.class);
                        intent.putExtra("position",position);
                        startActivity(intent);


                        Toast.makeText(getApplicationContext(),
                                "Added", Toast.LENGTH_SHORT).show();




                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();









//                Intent intent = new Intent(CategoryActivity.this,AddWebsiteActivity.class);
//                intent.putExtra("position", position);
//                startActivity(intent);
            }
        });
    }


    public void displayWebsites(String position){

        Log.i(TAG, "Displaying websites");
        Log.i(TAG, position);
        int position_number;
        position_number = Integer.parseInt(position);

        // Pegando os sites dessa categoria
        //final ArrayList<Website> category_websites = categories.get(position_number).getWebsites();

        Context context = getApplicationContext();
        storageHandler.setContext(context);
        //Pega as categorias a partir do arquivo que sao gravadas como Json
        ArrayList<Category> categories_returned = storageHandler.getCategoriesInStorage();

        //Pegando os websites da categoria mas com o ArrayList<Category> criado a partir do Internal Storage
        final ArrayList<Website> category_websites = categories_returned.get(position_number).getWebsites();
        Log.i(TAG, category_websites.toString());

        if(category_websites != null) {
            //Botando os nomes dos sites em um ArrayList pra usar o ArrayAdapter e botar em ListView
            final ArrayList<String> websites_names = new ArrayList<String>();
            for (Website website : category_websites) {
                websites_names.add(website.getName());
            }

//            //Criando o ArrayAdapter que liga a essa ViewGroup e cria views para a list_item e texview
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, websites_names);
//
//            //Binding o adapter a listView definida no XML
//            listView.setAdapter(adapter);

            WebsiteListAdapter adapter = new WebsiteListAdapter(this,category_websites);
            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // When clicked, show a toast with the TextView text
                    Log.i(TAG, String.valueOf(position));
//                    Toast.makeText(getApplicationContext(),
//                            ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

                    //Com a posicao do website que ele clicou eu vou pegar o link
                    String link = category_websites.get(position).getLink();
                    link = "http://" + link;
                    Uri site_link = Uri.parse(link);

                    Intent intent = new Intent(Intent.ACTION_VIEW, site_link);

                    String title = "Choose the browser";
                    Intent chooser = Intent.createChooser(intent, title);

                    // Verify the intent will resolve to at least one activity
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(chooser);
                    }
                }
            });

        }else{

            String vazio = "Ta vazio";
            Toast.makeText(getApplicationContext(),
                    vazio, Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onBackPressed() {
        // INTENT FOR YOUR HOME ACTIVITY
        Intent intent = new Intent(CategoryActivity.this,SeeCategoriesActivity.class);
        startActivity(intent);
    }

}
