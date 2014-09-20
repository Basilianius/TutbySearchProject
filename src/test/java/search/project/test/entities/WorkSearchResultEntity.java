package search.project.test.entities;

import webdriver.BaseEntity;

/**
 * Class for result entity;
 * @author v.katonov
 *
 */
public class WorkSearchResultEntity extends BaseEntity{
	//Entity parameters;
	private String headLine;
	private String city;
	private String company;
	//Locators of parameters;
	public static String headLineLocator = ".//a[@data-qa='vacancy-serp__vacancy-title']";
	public static String cityLocator = ".//span[@data-qa='vacancy-serp__vacancy-address']";
	public static String companyLocator = ".//a[@data-qa='vacancy-serp__vacancy-employer']";
	
	/**
	 * Constructor by parameters;
	 * @param headLine
	 * @param city
	 * @param company
	 */
	public WorkSearchResultEntity(String headLine, String city, String company){
		this.headLine = headLine;
		this.city = city;
		this.company = company;
	}
	
	/**
	 * Default constructor;
	 * @param headLine	<String>	Title of work; 
	 */
	public WorkSearchResultEntity(String headLine){
		this.headLine = headLine;
	}
	
	@Override
	protected String formatLogMsg(String message) {
		return String.format("%1$s %2$s ", this.getClass(), message);
	}
	
	/**
	 * Use for compare headLines by full match;
	 * @param searchLine	<String>	Search line;
	 * @return				<boolean>	Found or not;
	 */
	public boolean compareHeadLineByFullMatch(String searchLine){
		boolean result = headLine.equalsIgnoreCase(searchLine);	
		printCompareResult(result, headLine, searchLine);
		return result;
	}
	
	public String getHeadLine() {
		return headLine;
	}
	
	public String getCompany() {
		return company;
	}
	
	public String getCity() {
		return city;
	}
	/**
	 * Use for compare headLines by match;
	 * @param searchLine	<String>	Search line;
	 * @return				<boolean>	Found or not;
	 */
	public boolean compareHeadLineByMatch(String searchLine) {
		boolean result = headLine.toLowerCase().contains(searchLine.toLowerCase());
		printCompareResult(result, headLine, searchLine);
		return result;
	}
	
	/**
	 * Use for print results of compare;
	 * @param result
	 * @param expected
	 * @param received
	 */
	private void printCompareResult(boolean result, String received, String expected){
		if(!result){
			logger.warn(String.format("Check %s; Received '%s';\n\t Expected '%s'"
					, String.valueOf(result).toUpperCase(), received, expected));
		}else{
			logger.info(String.format("Check %s; Received '%s';\n\t Expected '%s'"
					, String.valueOf(result).toUpperCase(), received, expected));
		}
	}
	@Override
	public String toString(){
		return String.format("WorkSearchResult head: %s, city: %s, company: %s", headLine, city, company);
	}
}
