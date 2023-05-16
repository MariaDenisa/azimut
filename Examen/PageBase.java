package Examen;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class PageBase {
static WebDriver driver;	

	@Before
	public void initDriver() throws InterruptedException{
		
		ChromeOptions option=new ChromeOptions();    
    	option.addArguments("--remote-allow-origins=*"); 
    	

    	option.addArguments("user-data-dir=C:\\Users\\Deni\\AppDat\\Local\\Google\\Chrome\\User Data\\Default");
    	option.addArguments("--profile-directory=Default");
    	
    	System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
    	driver = new ChromeDriver(option);
    	driver.manage().window().maximize();						
        driver.navigate().to(Constants.BASE_URL);	
        Thread.sleep(2000);
        

	}
	
	@After
	public void closeConnection() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}
}
