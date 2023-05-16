package Examen;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestCases extends PageBase {
	
	@Test 
	public void case1_LoginUser() throws InterruptedException{
			
		Utils.preconditionLoginUser();
		
		Actions action = new Actions(driver);
		WebElement buttonAccount = driver.findElement(By.xpath("//*[@id=\"login-towatch\"]"));
		action.moveToElement(buttonAccount).perform();
		Thread.sleep(4000);
		
		WebElement email = driver.findElement(By.id("customer-email"));
        email.sendKeys("serbanmariadenisa@yahoo.com");
		Thread.sleep(2000);
	
		WebElement password = driver.findElement(By.name("login[password]"));
		password.sendKeys("Maria02!");
		Thread.sleep(2000);
		
		WebElement createAccountButton = driver.findElement(By.linkText("Autentificare"));
		createAccountButton.click();
		Thread.sleep(2000);
		
		Utils.validationLogin();
	}
		
	
	@Test 
	public void case2_MessagePage() throws InterruptedException{
		
		WebElement buttonProd = driver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[6]/button"));
		buttonProd.click();
		Thread.sleep(4000);

		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement category = driver.findElement(By.linkText("Bebe, Jucarii, Articole Copii & LEGO"));
		js.executeScript("arguments[0].scrollIntoView(false);", category);
	    category.click();
		Thread.sleep(5000);

		
		WebElement nonProd = driver.findElement(By.xpath("/html/body/div[2]/section[2]/div[1]/nav/div/div/div/div[8]/ul/li[6]/a/p/span"));
		js.executeScript("arguments[0].scrollIntoView(false);",nonProd);
		nonProd.click();
		Thread.sleep(5000);

		WebElement text = driver.findElement(By.xpath("/html/body/div[2]/main/div[3]/div[1]/div/div[3]/div/div"));
	      if(text.isDisplayed()){
	    	  System.out.println("Textul:" + "\"" +text.getText()+ "\"" + " este afisat pe pagina.");
	      }
	      else {
	          System.out.println("Textul nu este afisat pagina.");
	      }
	}
	
	@Test 
	public void case3_AddProductIntoCart() throws InterruptedException{
		
		WebElement buttonProd = driver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[6]/button"));
		buttonProd.click();
		Thread.sleep(4000);
		
		WebElement category = driver.findElement(By.linkText("TV, Audio-Video, Foto"));
		category.click();
		Thread.sleep(4000);
		
		WebElement categoryTv = driver.findElement(By.xpath("/html/body/div[2]/section[2]/div[1]/nav/div/div/div/div[1]/ul/li[1]/a/p/span"));
		categoryTv.click();
		Thread.sleep(4000);
		
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement buttonCart = driver.findElement(By.xpath("/html/body/div[2]/main/div[3]/div[1]/div/div[3]/div[2]/ol/li[4]/div/div[3]/div[3]/div/div[1]/form/button"));
		js1.executeScript("arguments[0].scrollIntoView(false);", buttonCart);
		buttonCart.click();
		Thread.sleep(6000);

		Utils.validationTvAdded();

		WebElement checkBoxGuarantee = driver.findElement(By.cssSelector("label[for='aj-extrawarranty-15403']"));	
		if(checkBoxGuarantee.isDisplayed() && checkBoxGuarantee.isEnabled()&& !checkBoxGuarantee.isSelected()) {
			checkBoxGuarantee.click();
		}
		Thread.sleep(5000);
	}
	
	@Test
	public void case4_VerifyAndUpdateCart() throws InterruptedException {
	
		Actions action = new Actions(driver);
		WebElement myCart = driver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[1]/div/a"));
		action.moveToElement(myCart).perform();
		Thread.sleep(4000);
		
		Utils.verifyCartNotEmpty();
		
		WebElement viewCart = driver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[1]/div/a/span[2]"));
		viewCart.click();
		Thread.sleep(4000);
		
		Utils.validationTvProdExists();
		
		WebElement orderTotal = driver.findElement(By.xpath("/html/body/div[2]/main/div[3]/div/div[2]/div[1]/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td"));
		String orderIntialTotal = orderTotal.getText();
		
		Select cantTV = new Select(driver.findElement(By.id("cart-21714625-qty")));
		cantTV.selectByValue("4");
		Thread.sleep(5000);
		
		WebElement orderTotalUpdated = driver.findElement(By.xpath("/html/body/div[3]/main/div[3]/div/div[2]/div[1]/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td"));
		String orderFinalTotal = orderTotalUpdated.getText();
		Thread.sleep(4000);
		
		Utils.validationUpdateCart(orderIntialTotal, orderFinalTotal);
		
	}
	
	@Test
	public void case5_favoriteProdListAdded() throws InterruptedException{
		
		Actions action = new Actions(driver);
		WebElement buttonAccount = driver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[5]/a"));
		action.moveToElement(buttonAccount).perform();
		Thread.sleep(5000);
		
		WebElement favoriteListProd = driver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[5]/div/ul/li[6]/a/span"));
		favoriteListProd.click();
		Thread.sleep(3000);
		
		WebElement nrMyFav1 = driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div/div[2]/ul/li[2]/a/span[2]"));
        String myFavorite= nrMyFav1.getText();
        Thread.sleep(3000);
		
        Utils.verifyListFavEmpty();
        
        WebElement buttonProd = driver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[6]/button"));
		buttonProd.click();
		Thread.sleep(2000);
        
		WebElement category = driver.findElement(By.linkText("Telefoane, Tablete, Smartwatch"));
		category.click();
		Thread.sleep(4000);
		
		WebElement categoryPhone = driver.findElement(By.xpath("/html/body/div[2]/section[2]/div[1]/nav/div/div/div/div[1]/ul/li[1]/a/p/span"));
		categoryPhone.click();
		Thread.sleep(4000);
		
		WebElement buttonFav = driver.findElement(By.xpath("/html/body/div[2]/main/div[3]/div[1]/div/div[3]/div[2]/ol/li[1]/div/div[1]/div/a[2]"));
		buttonFav.click();
		Thread.sleep(4000);

		Utils.validationFavProdExists();
		
		WebElement nrMyFav2 = driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div/div[2]/ul/li[2]/a/span[2]"));
        String nrMyFavorite = nrMyFav2.getText();
        
		Utils.validationUpdateListFav(myFavorite,nrMyFavorite);
	
	}
	
	@Test  
	public void case6_RemoveProdFromListFav() throws InterruptedException{
		
		Actions action = new Actions(driver);
		WebElement buttonAccount = driver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[5]/a"));
		action.moveToElement(buttonAccount).perform();
		Thread.sleep(5000);
		
		WebElement favoriteListProd = driver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[5]/div/ul/li[6]/a/span"));
		favoriteListProd.click();
		Thread.sleep(3000);
		
		Utils.verifyListFavNoEmpty();
		
		Utils.validationFavExists();
		
		WebElement buttonRemoveFav = driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[1]/form/div[1]/div/div/div/div[4]/div[3]/a[2]"));
		buttonRemoveFav.click();
		Thread.sleep(4000);
		
		Utils.validationFavProdRemoved();
		
	}
	
	
	@Test  
	public void case7_SearchProd() throws InterruptedException{
		
		WebElement searchBox = driver.findElement(By.id("searchingfield"));
		searchBox.sendKeys("Samsung tv smart");
		Thread.sleep(5000);
		
		WebElement searchList = driver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[7]/div[2]/form/div/div/div/dl[1]/dd[3]"));
		searchList.click();
		Thread.sleep(3000);
	    
	    List<WebElement> allTvOnPage = driver.findElements(By.className("product-item"));	
	    Utils.validationSearchedProd(allTvOnPage);
	}
	
	@Test
	public void case8_FilteredAndSortedPage() throws InterruptedException {
		
		WebElement buttonProd = driver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[6]/button"));
		buttonProd.click();
		Thread.sleep(4000);
		
		WebElement category = driver.findElement(By.linkText("TV, Audio-Video, Foto"));
		category.click();
		Thread.sleep(5000);
		
		WebElement categoryTv = driver.findElement(By.xpath("/html/body/div[2]/section[2]/div[1]/nav/div/div/div/div[1]/ul/li[1]/a/p/span"));
		categoryTv.click();
		Thread.sleep(4000);
		
		WebElement priceSort = driver.findElement(By.xpath("/html/body/div[2]/main/div[3]/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div/ul/li[3]/a/label/span[1]/span[1]"));
		priceSort.click();
		Thread.sleep(4000);
		
		Select sortPage = new Select(driver.findElement(By.id("sorter")));  
   	    sortPage.selectByVisibleText("Pret Descendent"); 
		Thread.sleep(5000);
		
        List<WebElement> allSamsungTvOnPage = driver.findElements(By.className("special-price"));	
		Utils.validationFilteredSortedProd(allSamsungTvOnPage);
	}
	
	
	
	}
	


