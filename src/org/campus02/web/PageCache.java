package org.campus02.web;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;

public class PageCache {
    private HashMap<String, WebPage> cache = new HashMap<>();

    public HashMap<String, WebPage> getCache() {
        return cache;
    }

    public WebPage readFromCache(String url) throws CacheMissException {
        if (cache.containsKey(url)) {
            return cache.get(url);
        }
        throw new CacheMissException(url + " konnte nicht im Cache gefunden werden");
    }

    public void writeToCache(WebPage webPage) {
        cache.put(webPage.getUrl(), webPage);
    }

    public void warmUp(String pathToUrls) throws UrlLoaderException {
        try (BufferedReader br = new BufferedReader(new FileReader(pathToUrls))) {
            String urlFromFile;
            while ((urlFromFile = br.readLine()) != null) {
                try {
                    WebPage page = UrlLoader.loadWebPage(urlFromFile);
                    writeToCache(page);
                } catch (UrlLoaderException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
