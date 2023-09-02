package StepDefinitions;
import com.qa.amazon.modules.AmazonHomePage;
import cucumber.api.java.en.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

import static Properties.PropertyReader.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SerenityRunner.class)
public class AmazonHomeSteps extends PageObject{

    AmazonHomePage amazonHomePage;

    @Given("User is on the Amazon.in website")
    public void user_is_on_the_amazon_in_website() throws IOException {
        System.setProperty("webdriver.chrome.driver","/Users/sakthilavanya/Desktop/amazon/src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(getUrl());
        driver.manage().window().maximize();
        String actualURL = driver.getCurrentUrl();
        amazonHomePage = new AmazonHomePage(driver);
        amazonHomePage = PageFactory.initElements(driver,AmazonHomePage.class);
        assertThat("URL is wrong!",actualURL.equalsIgnoreCase(getUrl()));
    }

    @When("User selects the three-star rating filter")
    public void user_selects_three_star_rating_filter() {
        amazonHomePage.selectStarRatingFilter();
    }

    @When("User selects the first search result on the page")
    public void user_selects_the_first_search_result_on_the_page() throws InterruptedException{
        amazonHomePage.selectFirstResult();
        String a = amazonHomePage.productNameInListing();
        amazonHomePage.switchToChildWindow();
        String b = amazonHomePage.productNameInDetail();
        assertThat("Product Name is Wrong!",a.equalsIgnoreCase(b));
        System.out.println("Product Name is correct!");
    }

    @When("User types {string} in the search bar")
    public void userTypesSearchTermInTheSearchBar(String searchTerm){
        amazonHomePage.clickOnSearchBar(searchTerm);
    }

    @And("User selects first two filters under Brands")
    public void userSelectsFirstTwoFiltersUnderBrands() throws InterruptedException{
        for (int i=1;i<=2;i++){
            amazonHomePage.selectBrandFilters(i);
        }
    }

    @And("User scrolls to the bottom of the result page")
    public void userScrollsToTheBottomOfTheResultPage() {
        amazonHomePage.scrollUptoProductInformation();
    }

    @Then("User views the technical specifications")
    public void userViewsTheTechnicalSpecifications() {
       amazonHomePage.printTechSpecification();
       getDriver().quit();
    }
}