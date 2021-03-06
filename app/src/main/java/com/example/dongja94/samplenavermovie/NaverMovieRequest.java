package com.example.dongja94.samplenavermovie;

import com.begentgroup.xmlparser.XMLParser;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by dongja94 on 2016-02-05.
 */
public class NaverMovieRequest extends NetworkRequest<NaverMovies> {

    String keyword;
    int start, display;
    public NaverMovieRequest(String keyword) {
        this(keyword, 1, 10);
    }

    public NaverMovieRequest(String keyword, int start) {
        this(keyword, start, 10);
    }

    public NaverMovieRequest(String keyword, int start, int display) {
        try {
            this.keyword = URLEncoder.encode(keyword,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.start = start;
        this.display = display;
    }

    private static final String URL_FORMAT = "https://openapi.naver.com/v1/search/movie.xml?target=movie&query=%s&start=%s&display=%s";

    @Override
    public URL getURL() throws MalformedURLException {
        String url = String.format(URL_FORMAT, keyword, start, display);
        return new URL(url);
    }

    @Override
    public void setRequestHeader(HttpURLConnection conn) {
        super.setRequestHeader(conn);
        conn.setRequestProperty("X-Naver-Client-Id", "FRzO_6MMu6zwQYAaXlZr");
        conn.setRequestProperty("X-Naver-Client-Secret","z0iOB55iQk");
    }

    @Override
    protected NaverMovies parse(InputStream is) throws ParseException {
        XMLParser parser = new XMLParser();
        NaverMovies movies = parser.fromXml(is, "channel", NaverMovies.class);
        return movies;
    }
}
