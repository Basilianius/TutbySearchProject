package search.project.test.forms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import search.project.test.entities.WorkSearchResultEntityContainer;
import webdriver.BaseForm;
import webdriver.elements.Label;
import webdriver.elements.Link;

/**
 * Class for search results;
 * @author v.katonov
 *
 */
public class WorkSearchResultPage extends BaseForm {
	//General variables for constructor;
	protected static String formTitle = "WorkSearchResultPage";
	protected static String formLocator = "//div[@class='search__row m-search__title']/div[contains(text(),'Ключевые слова')]";

	//Element and its locator of page number;
	private Link lnkNumPage;
	private String numPageLocator = "//li[@data-qa='pager-page']/a[contains(text(),'%s')]";
	private Label lblCurrentNumPage;
	private String numCurrentPageLocator = "//li[@data-qa='pager-page']/b[contains(text(),'%s')]";
	
	//Page elements;
	private Link lnkNextPage = new Link(By.xpath("//a[@data-qa='pager-next']"), "NextPage");	
	
	//Page entities;
	private String totalSearchResultsLocator = "//tr[contains(@data-qa, 'vacancy-serp__vacancy')]";
	private WorkSearchResultEntityContainer totalSearchResults = new WorkSearchResultEntityContainer();  
	
	/**
	 * Default constructor;
	 */
	public WorkSearchResultPage() {
		super(By.xpath(formLocator), formTitle);
	}
	
	/**
	 * Use for get all results on all pages;
	 * @return
	 */
	public WorkSearchResultEntityContainer getAllSearchResults(){
		getSearchResultsByCurrentPage();
		while (lnkNextPage.isPresent()){
			clickNextPageLink();
			browser.waitForPageToLoad();
			getSearchResultsByCurrentPage();
		}
		
		return totalSearchResults;
	}

	/**
	 * Use for get results on current page;
	 * @return
	 */
	public WorkSearchResultEntityContainer getSearchResultsByCurrentPage(){
		logger.info("Finding search results...");
		List<WebElement> searchResults =  browser.getDriver().findElements(By.xpath(totalSearchResultsLocator));
		totalSearchResults.addNewResults(searchResults);
		
		return totalSearchResults;		
	}	
	
	/**
	 * Use for get results by page number;
	 * @param numPage	<integer>	Page number;
	 * @return
	 */
	public WorkSearchResultEntityContainer getSearchResultsByPage(int numPage){
		if(numPage == 0){
			getAllSearchResults();
		}else{
    		clickNumPageLink(Integer.toString(numPage));
			browser.waitForPageToLoad();    		
    		getSearchResultsByCurrentPage();			
		}
		
		return totalSearchResults;
	}
	
	/**
	 * Use for click NextPageLink;
	 */
	public void clickNextPageLink() {
		if (lnkNumPage.isPresent()){
			lnkNextPage.click();
		}
	}
	
	/**
	 * Use for click link of page number;
	 * @param numPage	<String>	Page name;
	 */
	public void clickNumPageLink(String numPage){
		numPageLocator = String.format(numPageLocator, numPage); 
		lnkNumPage = new Link(By.xpath(numPageLocator), numPage);
		if (lnkNumPage.isPresent()){
			lnkNumPage.click();
		}else{
			numCurrentPageLocator = String.format(numCurrentPageLocator, numPage); 
			lblCurrentNumPage = new Label(By.xpath(numCurrentPageLocator), numPage);			
			if (!lblCurrentNumPage.isPresent()){
				logger.error("");
				logger.error("Link of page number has not clicked. Link is not expected. Please check parameter 'numPage' on 'TestSuite.xml'!");
				logger.error("Checking current page");
				logger.error("");
			}
		}
	}
	
	/**
	 * Use for set search result locator;
	 * @param locator
	 */
	public void setTotalSearchResultsLocator(String locator){
		this.totalSearchResultsLocator = locator;
	}
	
}
