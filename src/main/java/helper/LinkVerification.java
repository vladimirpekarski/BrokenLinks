package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static webdriversingleton.WebDriverSingleton.getWebdriver;

public class LinkVerification {
    private static List<String> uncheckedLinks = new ArrayList();
    public static Map<String, Integer> brokenLinks = new HashMap<>();

    public static void checkLinks() {
        for(String url: getUrlsAsString()) {
            try {
                if(url.startsWith("https://")) {
                    url = "http://" + url.substring(8);
                }

                if(url.contains("mailto")) {
                    continue;
                }

                HttpURLConnection huc = (HttpURLConnection) new URL(url).openConnection();
                huc.setRequestMethod("GET");
                huc.connect();

                if (huc.getResponseCode() > 400) {
                    brokenLinks.put(url, huc.getResponseCode());
                }

                huc.disconnect();
            } catch (ConnectException e) {
                brokenLinks.put(url, 504);
            } catch (MalformedURLException e) {
                uncheckedLinks.add(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printBrokenLinks() {
        Set<String> keySet = brokenLinks.keySet();
        for (String key : keySet) {
            System.out.println("URL : " + key + " ; Response : " + brokenLinks.get(key));
        }
    }

    public static void printUncheckedLinks() {
        for(String url : uncheckedLinks) {
            System.out.println("Unchecked URL : " + url);
        }
    }

    private static List<WebElement> getUrlsAsWebElements() {
        return getWebdriver().findElements(By.cssSelector("a[href]"));
    }

    private static List<String> getUrlsAsString() {
        List<String> urls = new ArrayList<>();

        for (WebElement webElement: getUrlsAsWebElements()) {
            urls.add(webElement.getAttribute("href"));
        }

        return urls;
    }
}
