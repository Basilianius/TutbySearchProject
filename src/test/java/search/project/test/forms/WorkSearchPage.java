package search.project.test.forms;

import org.openqa.selenium.By;

import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;

/**
 * Class for page about work search; 
 * @author v.katonov
 *
 */
public class WorkSearchPage extends BaseForm {
	//General variables for constructor;
	protected static String formTitle = "WorkSearchPage";
	protected static String formLocator = "//div[@class='indexsearch-tabs']/span[contains(text(),'Вакансии')]";

	//Page elements;
	private TextBox txbInputSearch = new TextBox(By.xpath("//table[@class='searchbox-table']//input[@id='main-search-applicant']"), "InputSearch");
	private Button btnSearch = new Button(By.xpath("//table[@class='searchbox-table']//input[@class='searchbox-submit']"), "Search");
	
	/**
	 * Default constructor;
	 */
	public WorkSearchPage() {
		super(By.xpath(formLocator), formTitle);
	}
	
	/**
	 * Use for input search line and click search button;
	 * @param searchLine	<String>	Line for search;
	 */
	public void searchByStringLine(String searchLine){
		txbInputSearch.type(searchLine);
		btnSearch.click();
	}

}
