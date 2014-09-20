package search.project.test.entities;

import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import webdriver.BaseEntity;
/**
 * Class for container of work search entity;
 * @author v.katonov
 *
 */
public class WorkSearchResultEntityContainer extends BaseEntity{
	private List<WorkSearchResultEntity> searchResults;
	private int countElement; 
	
	/**
	 * Default constructor;
	 */
	public WorkSearchResultEntityContainer(){
		searchResults = new ArrayList<WorkSearchResultEntity>();
		countElement = 0;
	}	
	/**
	 * Constructor by List;
	 * @param searchResults		<List WebElement>	Search results from page;
	 */
	public WorkSearchResultEntityContainer(List<WebElement> searchResults){
		this.searchResults = convertOnlyHeadLineByWebElement(searchResults);
		//this.searchResults = convertAllByWebElement(searchResults);		
		countElement = this.searchResults.size();
	}
	
	/**
	 * Use for add new search results;
	 * @param searchResults		<List WebElement>	Search results from page;
	 */
	public void addNewResults(List<WebElement> searchResults){
		this.searchResults = convertOnlyHeadLineByWebElement(searchResults);
		//this.searchResults = convertAllByWebElement(searchResults);
		countElement = this.searchResults.size();
	}	
	
	/**
	 * Use for convert only headLine entities from web elements to entities;
	 * @param webElements		<List WebElement>	Search results from page;
	 * @return
	 */
	private List<WorkSearchResultEntity> convertOnlyHeadLineByWebElement(List<WebElement> webElements){
		for (WebElement webElement: webElements){
			WebElement headLine = webElement.findElement(By.xpath(WorkSearchResultEntity.headLineLocator));
			String text = headLine.getText().trim();
			WorkSearchResultEntity result = new WorkSearchResultEntity(text);
			searchResults.add(result);
		}
		return searchResults;
	}

	/**
	 * Use for convert full parameters entities from web elements to entities;
	 * @param webElements		<List WebElement>	Search results from page;
	 * @return
	 */
	private List<WorkSearchResultEntity> convertAllByWebElement(List<WebElement> webElements){
		for (WebElement webElement: webElements){
			WebElement headLine = webElement.findElement(By.xpath(WorkSearchResultEntity.headLineLocator));
			String headLineText = headLine.getText().trim();
			
			WebElement city = webElement.findElement(By.xpath(WorkSearchResultEntity.cityLocator));
			String cityText = city.getText().trim();
			
			cityText = cityText.replaceAll("^[,]\\.*", "");
			
			WebElement company= webElement.findElement(By.xpath(WorkSearchResultEntity.companyLocator));
			String companyText = company.getText().trim();
			
			WorkSearchResultEntity result = new WorkSearchResultEntity(headLineText, cityText, companyText);
			searchResults.add(result);
			logger.info(result.toString());
		}
		return searchResults;		
	}
	
	public int getCountSearchResults(){
		return countElement;
	}
	
	public List<WorkSearchResultEntity> getCountSearchResultsentityContainer(){
		return searchResults;
	}

	@Override
	protected String formatLogMsg(String message) {
		return String.format("%1$s %2$s ", this.getClass(), message);
	}
	
	/**
	 * Use for get count match by search line;
	 * @param searchLine		<String>	Search line;
	 * @param searchParameter	<int>		Parameter of search;
	 * @return
	 */
	public int countMatchSearchResult(String searchLine, int searchParameter){
		boolean result = false;
		int countMatch = 0;
		int countCheck = 0;

		for(WorkSearchResultEntity searchResult : searchResults){
			switch (searchParameter){
				case 0:
					result = searchResult.compareHeadLineByFullMatch(searchLine);
					break;
				case 1:
					result = searchResult.compareHeadLineByMatch(searchLine);
					break;
			}
			countCheck++;
			
			if (result){
				countMatch++;
			}
		}
		logger.info(String.format("Total check: %s from all %s", countCheck, countElement));
		
		return countMatch;
	}
}