package com.example.rafael.mymarks;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Rafael on 03/11/2015.
 */

class Website{
    private String name;
    private String link;

    public Website(String name, String link){
        this.name = name;
        this.link = link;
    }

    public String getName(){
        return this.name;
    }

    public String getLink(){
        return  this.link;
    }
}


public class Category implements Parcelable{
    private String name;
    private ArrayList<Website> websites = null;

    public Category(String name){
        this.name = name;
        //Ja inicializa o websites pq senao quando for botar o primeiro ele vai dar null object
        this.websites = new ArrayList<Website>();
    }

    public Category(){

    }

    public void addWebsite(Website site){
        websites.add(site);
    }

    public String getCategoryName(){
        return this.name;
    }

//    Overriding o toString() pq pra fazer o ListView eu tenho que usar um ArrayAdapter<String>
//    que leva como quarto parametro um Array de dados que seram mostrados no ListView
//    e para mostrar no TextView do ListView ele vai acessar o toString() de cada objeto do array
    @Override
    public String toString(){
        return this.name;
    }


    public void setCategory(String name){
        this.name = name;
    }

    public ArrayList<Website> getWebsites(){
        return this.websites;
    }

    public void setWebsites(ArrayList<Website> websites){
        this.websites = websites;
    }


//    METODOS QUE TEM QUE OVERRIDE PARA SER PARCELABLE E PODER MANDAR ELE COMO EXTRAS NO INTENT

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Constructs a Category from a Parcel
     * @param parcel Source Parcel
     */

    public Category (Parcel parcel) {
        this.name = parcel.readString();
        this.websites = parcel.readArrayList(null);
    }

    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(name);
        dest.writeList(websites);
    }

    // Method to recreate a Category from a Parcel
    public static Creator<Category> CREATOR = new Creator<Category>() {

        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }

    };

}
