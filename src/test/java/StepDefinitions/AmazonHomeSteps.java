package StepDefinitions;
import com.qa.amazon.modules.AmazonHomePage;
import cucumber.api.java.en.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import static Properties.PropertyReader.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SerenityRunner.class)
public class AmazonHomeSteps extends PageObject{

    AmazonHomePage amazonHomePage;

    @Managed(driver = "chrome")
    WebDriver driver;

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
        System.out.println("User is in Amazon Home Page");
    }

    @When("User selects the three-star rating filter")
    public void user_selects_three_star_rating_filter() {
        amazonHomePage.selectStarRatingFilter();
        System.out.println("User selects three star rating filter");
    }

    @When("User selects the first search result on the page")
    public void user_selects_the_first_search_result_on_the_page() throws InterruptedException{
        amazonHomePage.selectFirstResult();
        System.out.println("User has selected the first result");
        String a = amazonHomePage.productNameInListing();
        amazonHomePage.switchToChildWindow();
        String b = amazonHomePage.productNameInDetail();
        assertThat("Product Name is Wrong!",a.equalsIgnoreCase(b));
        System.out.println("Product Name is correct!");
    }

    @When("User types {string} in the search bar")
    public void userTypesSearchTermInTheSearchBar(String searchTerm){
        amazonHomePage.clickOnSearchBar(searchTerm);
        System.out.println("User searches for laptop in search bar");
    }

    @And("User selects first two filters under Brands")
    public void userSelectsFirstTwoFiltersUnderBrands() throws InterruptedException{
        for (int i=2;i<=3;i++){
            amazonHomePage.selectBrandFilters(i);
        }
        System.out.println("User selects brand filters");
    }

    @And("User scrolls to the bottom of the result page")
    public void userScrollsToTheBottomOfTheResultPage() {
        amazonHomePage.scrollUptoProductInformation();
        System.out.println("User scrolls up to product information");
    }

    @Then("User views the technical specifications")
    public void userViewsTheTechnicalSpecifications() {
       amazonHomePage.printTechSpecification();
        System.out.println("Product specification printed!");
    }

    //         Configure Chrome to run in headless mode
    public void runInHeadless(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
    }
}