package org.campus02.web;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WebProxy {
    private PageCache cache;
    private int numCacheHits;
    private int numCacheMisses;

    public WebProxy() {
        cache = new PageCache();
    }

    public WebProxy(PageCache cache) {
        this.cache = cache;
    }

    public WebPage fetch(String url) throws UrlLoaderException {
        try {
            WebPage page = cache.readFromCache(url);
            numCacheHits++;
            return page;

        } catch (CacheMissException e) {
            System.out.println("Not found in Cache: " + e);
            numCacheMisses++;
            WebPage page = UrlLoader.loadWebPage(url);
            cache.writeToCache(page);
            return page;
        }


    }

    public String statsHits() {
        return "stat hits: " + numCacheHits;
    }

    public String statsMisses() {
        return "stat misses: " + numCacheMisses;
    }

    public boolean writePageCacheToFile(String pathToFile) {


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathToFile))) {
            for (String url : cache.getCache().keySet()) {
                WebPage webPage = cache.getCache().get(url);
                bw.write(url + ";" + webPage.getContent());
                bw.newLine();

            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
