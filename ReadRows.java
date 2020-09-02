package verifyDataPack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class ReadRows {

    WebDriver wd;

    @Before
    // Open ChromeBrowser
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver"); // chrome browser
        wd = new ChromeDriver();
        wd.get("https://www.w3schools.com/html/html_tables.asp");
    }

    @Test
    public void VerifyCountry() {
        // Get number of rows in table
        List<WebElement> Row_count = wd.findElements(By.xpath("//*[@id='main']/div[3]/div/table/tbody/tr"));
        System.out.println("Number of Rows= " + Row_count.size());

        // Get number of Columns in table
        List<WebElement> Col_count = wd.findElements(By.xpath("//*[@id='main']/div[3]/div/table/tbody/tr[1]/th"));
        System.out.println("Number of Columns = " + Col_count.size() + "\n");

        // divided Xpath In three parts to pass Row_count and Col_count values.
        String first_part = "//*[@id='main']/div[3]/div/table/tbody/tr[";
        String second_part = "]/td[";
        String third_part = "]";

        // xpath for Row Header and Extract the data value using Xpath
        String final_xpath = first_part + 1 + third_part;
        String Table_data = wd.findElement(By.xpath(final_xpath)).getText();
        for (String data: Table_data.split(" ")) {
            System.out.print(String.format("%-30s ",  data));
        }
        System.out.println();

        // Print out dash line under Row Header
        for (int i = 0; i < 30*3; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Loop to extract all data inside table using xpath
        for (int i = 2; i <= Row_count.size(); i++) {
            for (int j = 1; j <= Col_count.size(); j++) {
                String final_xpath_1 = first_part + i + second_part + j + third_part;
                String Table_data_2 = wd.findElement(By.xpath(final_xpath_1)).getText();
                System.out.print(String.format("%-30s ", Table_data_2));
            }
            System.out.println("");
        }

        // Test to verify the country list
        String countryName = "Italy";  // input country name to verify in Country Column
        boolean isCountryFound = false;

        for (int i = 2; i <= Row_count.size(); i++) {
            String final_xpath_1 = first_part + i + second_part + 3 + third_part;
            String Table_data_2 = wd.findElement(By.xpath(final_xpath_1)).getText();
            if (Table_data_2.contains(countryName)) {
                isCountryFound = true;
                break;
            }
        }

        // Verify the country name from Country Column
        if(isCountryFound){
            System.out.println("\n" + countryName + " Detected in Country Column.");
        }else{
            System.out.println("\n" + countryName + " Not Detected in Country Column.");
        }
    }

    @After
    // Close the browser
    public void Close() throws InterruptedException {
        wd.quit();
    }
}