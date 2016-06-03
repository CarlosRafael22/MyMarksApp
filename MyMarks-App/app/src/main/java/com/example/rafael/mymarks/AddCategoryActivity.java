package com.example.rafael.mymarks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCategoryActivity extends AppCompatActivity {

    private EditText add_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        add_category = (EditText) findViewById(R.id.add_category_text);

        Button button = (Button) findViewById(R.id.add_category_button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Cria a nova categoria e manda de volta pra a EntryActivity
//                Category newCategory = new Category(category_name);

                Intent intent = new Intent(AddCategoryActivity.this,EntryActivity.class);

                String category_name = add_category.getText().toString();

                intent.putExtra("new_category",category_name);

                //Pra mandar de volta tem que fazer isso e nao chamar startAcitivity
                setResult(RESULT_OK, intent);
                finish();
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

}
