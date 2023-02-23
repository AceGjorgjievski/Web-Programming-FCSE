package mk.finki.ukim.mk.lab.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }


    /**
     *
     * @param driver - toj e kako celosen broswer koj shto si ja pameti svojata
     *               sostojba
     * @param relativeUrl
     */
    static void get(WebDriver driver, String relativeUrl) {
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:9999") + relativeUrl;
        driver.get(url);
    }

}

