package com.example.assgd1_androidnetworking_anhphtph08567;

import org.json.JSONException;
import org.json.JSONObject;

class Photos {
    public String title,photo,height_z,width_z,id;


    public Photos (JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("url_z")){
            photo = jsonObject.getString("url_z");
        }
        if (jsonObject.has("title")){
            title = jsonObject.getString("title");
        }
        if (jsonObject.has("height_z")){
            height_z = jsonObject.getString("height_z");
        }
        if (jsonObject.has("width_z")){
            width_z = jsonObject.getString("width_z");
        }
        if (jsonObject.has("id")){
            id = jsonObject.getString("id");
        }

    }

    public String getTitle() {
        return title;
    }

    public String getPhoto() {
        return photo;
    }

    public String getHeight_z() {
        return height_z;
    }

    public String getWidth_z() {
        return width_z;
    }

    public String getId() {
        return id;
    }
}
