package com.example.dongja94.samplenavermovie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dongja94 on 2016-02-05.
 */
public class ImageRequest extends NetworkRequest<Bitmap> {
    String url;
    public ImageRequest(String url) {
        this.url = url;
    }
    @Override
    public URL getURL() throws MalformedURLException {
        return new URL(url);
    }

    @Override
    protected Bitmap parse(InputStream is) throws ParseException {
        Bitmap bm = BitmapFactory.decodeStream(is);
        return bm;
    }
}
