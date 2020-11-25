package com.example.justpizza;

import com.google.gson.annotations.SerializedName;

public class PizzaPojo {
    public PizzaPojo(String item_name, String description) {
        this.itemname = item_name;
        this.description = description;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String movie_name) {
        this.itemname = movie_name;
    }

    @SerializedName("itemname")
    private String itemname;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("description")
    private String description;




    public PizzaPojo()
    {

    }

}
