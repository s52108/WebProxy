package org.campus02.web;

public class DemoHelperApp {
    public static void main(String[] args) throws UrlLoaderException {

        WebPage webPage = UrlLoader.loadWebPage("https://orf.at");
        System.out.println(webPage);

        PageCache pageCache = new PageCache();
        pageCache.warmUp("C:\\Users\\postl\\OneDrive\\Dokumente\\Wirtschaftsinformatik\\2. Semester\\Erweiterte Themen der Softwareentwicklung\\Übungsklausur\\demo_urls.txt");
    }
}
