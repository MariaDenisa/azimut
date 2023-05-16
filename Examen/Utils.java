package Examen;

import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.WebElement;

public class Utils  extends PageBase{
	

	public static void validationLogin() {
		boolean conditionLogin = driver.getPageSource().contains(" Bun venit, Denisa Serban");
		Assert.assertTrue("Nu s-a putut face login utilizator!", conditionLogin);
	}

	public static void preconditionLoginUser() {
		boolean conditionLogin = driver.getPageSource().contains("Bun venit,");
		
		Assume.assumeFalse("Utilizatorul este deja logat cu un cont!", conditionLogin);
	}
	
	public static void validationTvAdded() {
		boolean conditionTvAdded = driver.getPageSource().contains(" Produsul a fost adaugat in cos");
		Assert.assertTrue("Nu s-a putut adauga produsul in cos", conditionTvAdded);
	}
	
	public static void verifyCartNotEmpty() {
		boolean conditionCartNotEmpty = driver.getPageSource().contains("Nu aveti produse in cos.");
		Assert.assertFalse("Cosul de cumparaturi este gol", conditionCartNotEmpty);
	}
	
	public static void validationTvProdExists() {
		boolean conditionTvProdExists = driver.getPageSource().contains("Televizor Smart LED, Samsung 32T5372, 80 cm, Full HD, Clasa G");
		Assert.assertTrue("Nu exista produsul in cos!", conditionTvProdExists);
	}
	
	public static void validationUpdateCart(String orderIntialTotal, String orderFinalTotal) {
		boolean conditionUpdateCart = (orderIntialTotal.equals(orderFinalTotal));
		Assert.assertFalse("Nu s-a facut update pe cantitatea de produse", conditionUpdateCart);
	}
	
	
	public static void verifyListFavEmpty() {
		boolean conditionFavProdEmpty = driver.getPageSource().contains("Nu ai nici un produs in lista cu favorite");
		Assert.assertTrue("Ai produse in lista cu favorite",conditionFavProdEmpty);
	}
	
	public static void validationFavProdExists() {
		boolean conditionFavProdExists = driver.getPageSource().contains(" Telefon mobil Apple iPhone 13 5G, 128GB, Midnight a fost adaugat in lista de favorite. Da click ");
		Assert.assertTrue("Nu exista produsul in cos!", conditionFavProdExists);
	}
	
	public static void validationUpdateListFav(String myFavorite, String nrMyFavorite) {
		boolean conditionUpdateListFav = (myFavorite.equals(nrMyFavorite));
		Assert.assertFalse("Nu s-a facut update pe cantitatea de produse",conditionUpdateListFav );
	}
	
	public static void verifyListFavNoEmpty() {
		boolean conditionFavProdNoEmpty = driver.getPageSource().contains("Nu ai nici un produs in lista cu favorite");
		Assert.assertFalse("Ai produse in lista cu favorite",conditionFavProdNoEmpty);
	} 
	
	public static void validationFavExists() {
		boolean conditionFavProdExist = driver.getPageSource().contains("Telefon mobil Apple iPhone 13 5G, 128GB, Midnight");
		Assert.assertTrue("Nu exista produsul in cos!", conditionFavProdExist);
	}
	public static void validationFavProdRemoved() {
		boolean conditionFavProdRemoved = driver.getPageSource().contains("Telefon mobil Apple iPhone 13 5G, 128GB, Midnight a fost sters din lista de favorite.");
		Assert.assertTrue("Nu s-a putut sterge produsul din cos!", conditionFavProdRemoved);
	}
	
	
	public static boolean verifySearchedProd(List<WebElement> allTvOnPage) {
		
		boolean searched = true;
		
		for(int i=0; i<allTvOnPage.size(); i++) {
			String eachTv = allTvOnPage.get(i).getText();	
		
			if( allTvOnPage.contains(eachTv)) {	
				searched = false;
			}
		}
		return searched;
	}
	
	public static void validationSearchedProd(List<WebElement> allTvOnPage) {
		Assert.assertTrue("Nu s-a facut corect cautarea produselor!",verifySearchedProd(allTvOnPage));
	}
	
	public static boolean verifyFilteredAndSortedProd(List<WebElement> allSamsungTvOnPage) {
		boolean filtered = true;
		boolean sorted = true;
		Float[] SamsungTv = new Float[allSamsungTvOnPage.size()];
		for(int i=0; i<allSamsungTvOnPage.size(); i++) {
			String eachSamsungTv = allSamsungTvOnPage.get(i).getText().substring(0,5);	
			float eachSamsungTvPrice = Float.parseFloat(eachSamsungTv);
			SamsungTv[i] = eachSamsungTvPrice;
			if(eachSamsungTvPrice<2.000 || eachSamsungTvPrice>3.000) {
				filtered = false;
			}
		}
		
		for(int i=0; i<SamsungTv.length-1; i++) {		
			if(SamsungTv[i]<SamsungTv[i+1]) {
				sorted = false;
			}
		}
		return (filtered && sorted);
	}
	
	public static void validationFilteredSortedProd(List<WebElement> allSamsungTvOnPage) { 
		Assert.assertTrue("Nu s-a facut filtrarea/sortarea dupa pret!", verifyFilteredAndSortedProd(allSamsungTvOnPage));
	}
	
	
}

