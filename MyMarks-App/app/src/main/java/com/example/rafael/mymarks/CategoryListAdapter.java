package com.example.rafael.mymarks;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rafael on 07/11/2015.
 */
public class CategoryListAdapter extends ArrayAdapter<Category> {


    private Integer[] folderIcons = {R.drawable.folder};

    public CategoryListAdapter(Context context, ArrayList<Category> categories){
        super(context,0,categories);
    }

    //Pra fazer um Custom ArrayAdapter tem que override esse metodo
    //Ele transforma o objeto, no caso o Category, em uma View que vai ser usada na ListView
    //recebe a posicao em que essa nova View vai ser colocada, uma view antiga que vai ser convertida na nova
    //e a View em que a nova view sera colocada

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get the data item for this position
        Category category = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_single_item, parent, false);
        }

        // Lookup view for data population
        ImageView starIcon = (ImageView) convertView.findViewById(R.id.category_list_icon);
        TextView category_name = (TextView) convertView.findViewById(R.id.category_list_name);

        // Populate the data into the template view using the data object
        starIcon.setImageResource(folderIcons[0]);
        category_name.setText(category.getCategoryName());

        // Return the completed view to render on screen
        return convertView;
    }
}
