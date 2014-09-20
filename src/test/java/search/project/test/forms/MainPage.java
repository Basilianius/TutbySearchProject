package search.project.test.forms;


import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Link;

/**
 * Class for main page of tut.by; 
 * @author v.katonov
 *
 */
public class MainPage extends BaseForm {
	//General variables for constructor;
	protected static String formTitle = "MainPage";
	protected static String formLocator = "//div[@class='b-tags']/ul[@class='b-auxlist']/li[contains(text(),'Горячие темы')]";

	//Element and its locator of top menu;
	private Link lnkElementTopMenu;
	private String elementTopMenuLocator = "//div[@class='b-topbar-h']/ul[@class='b-topbar-i']//a[@title='%s']";

	/**
	 * Default constructor;
	 */
	public MainPage() {
		super(By.xpath(formLocator), formTitle);
	}	

	/**
	 * Click element of top menu by name of element;
	 * @param elementName	<String>	Name of element of top menu;
	 */
	public void clickTopMenuLink(String elementName){
		elementTopMenuLocator = String.format(elementTopMenuLocator, elementName); 
		lnkElementTopMenu = new Link(By.xpath(elementTopMenuLocator), elementName);
		lnkElementTopMenu.click();
	}

}
