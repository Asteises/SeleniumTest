package ru.asteises.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelTravel {

    private WebDriver webDriver;
    private final String url = "https://level.travel/";

    private final String inputCountry = "/html/body/header/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[2]/div/div/div/div[1]/div[1]/input";
    private final String inputDate = "/html/body/header/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[2]/div/div/div/div[1]/div[2]/div[1]/span";

    private final String inputNights = "/html/body/header/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[2]/div/div/div/div[1]/div[3]/div[1]/span";

    private final String inputPerson = "/html/body/header/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[2]/div/div/div/div[1]/div[4]/div[1]";

    private final String inputBaby = "/html/body/header/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[2]/div/div/div/div[1]/div[4]/div[2]/ul/li/select";

    private final String select8 = "/html/body/header/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[2]/div/div/div/div[1]/div[4]/div[2]/ul/li/select";
    private final String findButton = "/html/body/header/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[2]/div/div/div/div[2]/button/span";

    private final String price = "/html/body/section/div/div/div/div[2]/div[2]/div[2]/aside/div/div/div[5]/div[2]/div/label[1]/div[1]/input";

    private String hotelUrl = "https://level.travel/hotels/72323-Sural_Resort?adults=2&from=Moscow-RU&kids=2&kids_ages=8%2C10&nights=16&offer_date=2022-09-01&offer_instant_confirm=false&offer_lt_extras=&offer_operators=&start_date=01.09.2022";
    private String[] changeDate = hotelUrl.split("&");

    public void execute() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
//        webDriver.get(url);
//        WebElement countrySearch = webDriver.findElement(By.xpath(inputCountry));
//        countrySearch.sendKeys("Crystal Flora Beach Resort");
//        webDriver.findElement(By.xpath(inputDate)).click();
//        WebElement rightButtonMonth = webDriver.findElement(By.className("md-angle-right"));
//        Thread.sleep(100);
//        rightButtonMonth.click();
//        rightButtonMonth.click();
//        rightButtonMonth.click();
//        webDriver.findElement(By.xpath(inputNights)).click();
//        WebElement rightButtonNights = webDriver.findElement(By.className("md-plus"));
//        Thread.sleep(100);
//        rightButtonNights.click();
//        rightButtonNights.click();
//        rightButtonNights.click();
//        webDriver.findElement(By.xpath(inputPerson)).click();
//        Thread.sleep(100);
//        webDriver.findElement(By.xpath(inputBaby)).click();
//        Thread.sleep(100);
//        Select selectBaby = new Select(webDriver.findElement(By.xpath(select8)));
//        List<WebElement> webElements = selectBaby.getOptions();
//        Thread.sleep(100);
//        for (WebElement wE: webElements) {
//            if (wE.getText().equals("8 ���") || wE.getText().equals("10 ���")) {
//                wE.click();
//            }
//        }
//        Thread.sleep(100);
//        webDriver.findElement(By.xpath(findButton)).click();
//        Thread.sleep(500);
        Map<String, Integer> nights14 = new HashMap<>();
        List<Integer> nights15 = new ArrayList<>();
        List<Integer> nights16 = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            StringBuilder date = new StringBuilder("offer_date=2022-09-0" + i);
            hotelUrl = "https://level.travel/hotels/72323-Sural_Resort?adults=2&from=Moscow-RU&kids=2&kids_ages=8%2C10&nights=16&" + date + "&offer_instant_confirm=false&offer_lt_extras=&offer_operators=&start_date=01.09.2022";
            System.out.println(hotelUrl);
            webDriver.get(hotelUrl);
            webDriver.manage().window().fullscreen();
            Thread.sleep(7000);
            List<WebElement> prices = webDriver.findElements(By.xpath("//span[@class='HotelOfferPrice__StyledPrice-sc-1v3l0l6-3 hlbfbO']"));

//        List<WebElement> prices = webDriver.findElements(By.xpath("div[@class='HotelOfferPrice__StyledHotelOfferPrice-sc-1v3l0l6-4 fgqQHT']"));
            System.out.println(prices.size());
            List<Integer> priceList = prices.stream().map(webElement -> Integer.valueOf(webElement.getText().replaceAll("[^0-9]", ""))).toList();
            nights14.put(String.valueOf(date), priceList.get(0));
            nights14.put(String.valueOf(date), priceList.get(5));
            nights15.add(priceList.get(1));
            nights15.add(priceList.get(6));
            nights16.add(priceList.get(2));
            nights16.add(priceList.get(7));

        }
        for (String offerDate: nights14.keySet()) {
            System.out.println("Цены на 14 ночей: " + offerDate + " " + nights14.get(offerDate).toString());
        }

        System.out.println("Цены на 15 ночей: "+ nights15.toString());
        System.out.println("Цены на 16 ночей: " + nights16.toString());
    }
}
