package org.campus02.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlLoader {

    public static WebPage loadWebPage(String url) throws UrlLoaderException {

        try {
            URL webpageURL = new URL(url);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(webpageURL.openStream()))) {
                String content = "";
                String line;
                while ((line = br.readLine()) != null) {
                    content = content + line;
                }
                return new WebPage(url, content);

            }
        } catch (MalformedURLException e) {
            throw new UrlLoaderException(url, e);
        } catch (IOException e) {
            throw new UrlLoaderException(url, e);
        }

    }
}
