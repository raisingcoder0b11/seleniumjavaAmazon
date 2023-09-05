package com.qa.amazon.modules;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Iterator;
import java.util.Set;

public class AmazonHomePage {

    @FindBy(css = "#twotabsearchtextbox")
    WebElement searchBar;

   @FindBy(xpath = "//div[@id=\"brandsRefinements\"]//div[@class=\"a-checkbox a-checkbox-fancy s-navigation-checkbox aok-float-left\"]")
   WebElement brandFilters;

   @FindBy(css = ".a-star-medium-5")
    WebElement starRatingFilter;

    @FindBy(xpath = "//span[contains(@class,'a-size-medium a-color-base')][1]")
    WebElement firstResult;

    @FindBy(css = "#prodDetails > h2")
    WebElement productInformation;

    @FindBy(xpath = "(//table[@id='productDetails_techSpec_section_1']//td)[2]")
    WebElement techSpecification;

    @FindBy(xpath = "//span[@id='productTitle']")
    WebElement productTitle;

   WebDriver webDriver;
   JavascriptExecutor js;

    public AmazonHomePage(WebDriver driver) {
       webDriver = driver;
        js = (JavascriptExecutor) driver;
    }
    public void clickOnSearchBar(String searchTerm){
       searchBar.sendKeys(searchTerm);
       searchBar.click();
       searchBar.sendKeys(Keys.ENTER);
   }

   public void selectBrandFilters(int i) throws InterruptedException {
           String dynamicPath = "(//div[@id=\"brandsRefinements\"]//div[@class=\"a-checkbox a-checkbox-fancy s-navigation-checkbox aok-float-left\"])"+"["+i+"]";
           WebElement element = webDriver.findElement(By.xpath(dynamicPath));
           js.executeScript("arguments[0].click()",element);
   }

   public void selectStarRatingFilter(){
        try{
        if(starRatingFilter.isDisplayed()){
           starRatingFilter.click();}}
        catch (Exception e){
            System.out.println("Element is not present");
        }
           WebDriverWait webDriverWait = new WebDriverWait(webDriver,2000);
           webDriverWait.until(ExpectedConditions.elementToBeClickable(firstResult));
   }

   public void selectFirstResult() throws InterruptedException {
        js.executeScript("arguments[0].click()",firstResult);
   }

   public String productNameInListing(){
       return firstResult.getText();
   }
   public void switchToChildWindow(){
        //Here parentWindow is AmazonHomePage
       String parentWindow = webDriver.getWindowHandle();
       Set<String> s= webDriver.getWindowHandles();
       Iterator<String> I1= s.iterator();
       while(I1.hasNext())
       {
           //Here ChildWindow is Search Result Page
           String child_window=I1.next();
           if(!parentWindow.equals(child_window)){
               webDriver.switchTo().window(child_window);
               System.out.println("Title :"+webDriver.switchTo().window(child_window).getTitle());
           }

       }
   }

   public String productNameInDetail(){
       return productTitle.getText();
   }

   public void scrollUptoProductInformation(){
        js.executeScript("arguments[0].scrollIntoView(true)",productInformation);
   }

   public void printTechSpecification(){
       System.out.println("Tech Specification :"+techSpecification.getText());
       webDriver.quit();
   }

}
