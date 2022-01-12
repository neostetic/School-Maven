package cz.polacek;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Application {

    public static void getContent(String url) throws IOException {
        HttpsURLConnection urlConnection = (HttpsURLConnection) new URL(url).openConnection();
        Scanner scanner = new Scanner(urlConnection.getInputStream());
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }

    public static void main(String[] args) throws IOException {
        // getContent("https://hokej.cz/tipsport-extraliga/stats-center?season=2021&competition=6705&stranger=0&stats-all=1&stats-order=ga&stats-direction=desc");
        Document doc = Jsoup.connect("https://hokej.cz/tipsport-extraliga/stats-center?season=2021&competition=6705&stranger=0&stats-all=1&stats-order=ga&stats-direction=desc").get();
        System.out.println(doc.title());

        Elements newsHeadlines = doc.select("body > div.branding-wrapper > div.content-wrapper > div > div.container > table > tbody > tr > td.text-left");
        for (Element headline : newsHeadlines) {
            System.out.println(headline.text());
        }
    }

}
