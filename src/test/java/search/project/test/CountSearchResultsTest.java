package search.project.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import search.project.test.entities.WorkSearchResultEntityContainer;
import search.project.test.forms.MainPage;
import search.project.test.forms.WorkSearchPage;
import search.project.test.forms.WorkSearchResultPage;
import webdriver.BaseTest;

/**
 * Test for assert a count search results by the search line;
 * On the work page of tut.by;
 * @author v.katonov
 * 
 */
public class CountSearchResultsTest extends BaseTest {
	//Test parameters;
	private String baseUrl;
	private String elementTopMenu;
	private String searchLine;
	private int searchParameter;
	private int numPage;
	private int expextedCount;
	
	//Test values;
	private WorkSearchResultEntityContainer searchResults;
	private int countMatch;

	@Test
	@Parameters({"baseUrl"
		, "elementTopMenu"
		, "searchLine"
		, "searchParameter"
		, "numPage"
		, "expextedCount"})
	public void readParams(String baseUrl
			, String elementTopMenu
			, String searchLine
			, String searchParameter
			, String numPage
			, String expextedCount
			) throws Throwable{
		
		this.baseUrl = baseUrl;
		this.elementTopMenu = elementTopMenu;
		this.searchLine = searchLine;
		
		this.searchParameter = Integer.parseInt(searchParameter);
		this.numPage = Integer.parseInt(numPage);
		this.expextedCount = Integer.parseInt(expextedCount);
	
		xTest();
	}

	@Override
	public void runTest() {
		LogStep();
		logger.info("Open MainPage".toUpperCase());
		browser.navigate(baseUrl);
		MainPage mainPage = new MainPage();

		LogStep();
		logger.info("Open WorkSearchPage".toUpperCase());
		mainPage.clickTopMenuLink(elementTopMenu);
		WorkSearchPage workSearchPage = new WorkSearchPage();

		LogStep();
		logger.info("Search by line".toUpperCase());
		workSearchPage.searchByStringLine(searchLine);
		WorkSearchResultPage workSearchResultPage = new WorkSearchResultPage();

		LogStep();
		logger.info("Find results".toUpperCase());
		searchResults = workSearchResultPage.getSearchResultsByPage(numPage);
	
		logger.info("Count of match search results".toUpperCase());
		countMatch = searchResults.countMatchSearchResult(searchLine, searchParameter);

		logger.info("Check count of match".toUpperCase());
		assertEquals(String.format("Count of match %1$s != expected %2$s", countMatch, expextedCount), expextedCount, countMatch);
		logger.info(String.format("Count of match %1$s = expected %2$s", countMatch, expextedCount));
		
	}

}
