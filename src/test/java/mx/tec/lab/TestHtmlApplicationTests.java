package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

@SpringBootTest
class TestHtmlApplicationTests {

	private WebClient webClient;
	 
	@BeforeEach
	public void init() throws Exception {
	    webClient = new WebClient();
	}
	 
	@AfterEach
	public void close() throws Exception {
	    webClient.close();
	}
	
	@Test
	public void givenAClient_whenEnteringAutomationPractice_thenPageTitleIsCorrect()
	  throws Exception {
	    webClient.getOptions().setThrowExceptionOnScriptError(false);
	    HtmlPage page = webClient.getPage("http://automationpractice.com/index.php");
	    	     
	    assertEquals("My Store", page.getTitleText());
	}
	
	@Test
	public void givenAClient_whenEnteringLoginCredentials_thenAccountPageIsDisplayed()
	  throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
	    HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	    HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
	    emailField.setValueAttribute("karenaliciahernandez@hotmail.com");
	    HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
	    passwordField.setValueAttribute("nidGu2-mugtah-nofzen");
	    HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
	    HtmlPage resultPage = submitButton.click();

	    assertEquals("My account - My Store", resultPage.getTitleText());
	}
	
	
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenAuthenticationPageIsDisplayed()
	  throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
	    HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	    HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
	    emailField.setValueAttribute("karenaliciahernandez@hotmail.com"); 
	    HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
	    passwordField.setValueAttribute("nidGu2");
	    HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
	    HtmlPage resultPage = submitButton.click();

	    assertEquals("Login - My Store", resultPage.getTitleText());
	}	
	
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenErrorMessageIsDisplayed()
	  throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
	    HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	    HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
	    emailField.setValueAttribute("karenaliciahernandez@hotmail.com");
	    HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
	    passwordField.setValueAttribute("nidGu2");
	    HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
	    HtmlPage resultPage = submitButton.click();
	    HtmlListItem listItem = resultPage.getFirstByXPath("//div[@class='alert alert-danger']/ol/li");

	    assertEquals("Authentication failed.", listItem.getTextContent()); 
	}
	
	@Test
	public void givenAClient_whenSearchingNotExistingProduct_thenNoResultsDisplayed()
	  throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
	    HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	    HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
	    emailField.setValueAttribute("karenaliciahernandez@hotmail.com");
	    HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
	    passwordField.setValueAttribute("nidGu2-mugtah-nofzen");
	    HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
	    HtmlPage mainPage = submitButton.click();
		HtmlInput search = (HtmlInput) mainPage.getElementById("search_query_top");
		search.setValueAttribute("boots");
		HtmlButton searchButton = (HtmlButton) mainPage.getFirstByXPath("//button[@class='btn btn-default button-search']");
		HtmlPage results = searchButton.click();
		HtmlParagraph notFound = (HtmlParagraph) results.getFirstByXPath("//p[@class='alert alert-warning']");
		
		assertEquals("No results were found for your search \"boots\"", notFound.getVisibleText());
	}
	
	@Test
	public void givenAClient_whenSearchingEmptyString_thenPleaseEnterDisplayed()
	  throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
	    HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	    HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
	    emailField.setValueAttribute("karenaliciahernandez@hotmail.com");
	    HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
	    passwordField.setValueAttribute("nidGu2-mugtah-nofzen");
	    HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
	    HtmlPage mainPage = submitButton.click();
		HtmlButton search = (HtmlButton) mainPage.getFirstByXPath("//button[@class='btn btn-default button-search']");
		HtmlPage results = search.click();
		HtmlParagraph notFound = (HtmlParagraph) results.getFirstByXPath("//p[@class='alert alert-warning']");
		
		assertEquals("Please enter a search keyword", notFound.getTextContent().trim());
	}

	@Test
	public void givenAClient_whenSigningOut_thenAuthenticationPageIsDisplayed()
	  throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
	    HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	    HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
	    emailField.setValueAttribute("karenaliciahernandez@hotmail.com");
	    HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
	    passwordField.setValueAttribute("nidGu2-mugtah-nofzen");
	    HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
	    HtmlPage mainPage = submitButton.click();
		HtmlAnchor logout = (HtmlAnchor) mainPage.getFirstByXPath("//a[@class='logout']");
		HtmlPage result = logout.click();
		assertEquals("Login - My Store", result.getTitleText());
	}

}
