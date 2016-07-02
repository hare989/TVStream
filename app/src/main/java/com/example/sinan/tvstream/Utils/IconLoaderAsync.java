package com.example.sinan.tvstream.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Sinan on 7.6.2016.
 */
public class IconLoaderAsync extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;
    private Bitmap iconBitmap;


    public IconLoaderAsync(ImageView imageView){

        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            URL iconUrl = new URL(params[0]);
            this.iconBitmap = BitmapFactory.decodeStream(iconUrl.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return this.iconBitmap;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        this.imageView.setImageBitmap(bitmap);
    }
}
