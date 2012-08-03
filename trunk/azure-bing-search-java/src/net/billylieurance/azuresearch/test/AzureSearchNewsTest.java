package net.billylieurance.azuresearch.test;

import org.testng.annotations.Test;
import org.w3c.dom.Document;

import net.billylieurance.azuresearch.AzureSearchNewsQuery;
import net.billylieurance.azuresearch.AzureSearchNewsResult;
import net.billylieurance.azuresearch.AzureSearchResultSet;

public class AzureSearchNewsTest {

	AzureSearchNewsResult asr;
	
	@Test
	public void TestAppid(){
		final String reason = "You need a valid Azure Appid as the static final String AZURE_APPID in net.billylieurance.azuresearch.test.AzureAppid to run these tests.";
		assert (AzureAppid.AZURE_APPID != null) : reason;
		assert (!AzureAppid.AZURE_APPID.equals("")) : reason;		
	}
	
	@Test 
	(dependsOnMethods = "TestAppid")
	public void TestConstructor(){
		AzureSearchNewsQuery aq = new AzureSearchNewsQuery();
		assert (aq != null) : "Did not generate an actual query object.";
	}
	
	@Test
	(dependsOnMethods = "TestConstructor")
	public void buildQueryResult(){
		AzureSearchNewsQuery aq = new AzureSearchNewsQuery();
		aq.setAppid(AzureAppid.AZURE_APPID);
		aq.setQuery("Oklahoma Sooners");
		
		aq.doQuery();
		Document ad = aq.getRawResult();
		assert (ad != null) : "getRawResult returned null";
		
		AzureSearchResultSet<AzureSearchNewsResult> ars = aq.getQueryResult();
		assert (ars != null) : "getQueryResult returned null";
		assert (ars.getASRs() != null) : "getQueryResult.getASRs returned null";
		assert (!ars.getASRs().isEmpty()) : "getQueryResult returned no results";
	
		asr = ars.getASRs().get(0);
		assert (asr != null) : "Unparseable result from result.";
		
	}
	
	@Test
	(dependsOnMethods = "buildQueryResult")
	public void checkDate(){		
		assert (asr.getDate() != null) : "Unparseable date from result";
	}
	
	@Test
	(dependsOnMethods = "buildQueryResult")
	public void checkSource(){		
		assert (asr.getSource() != null) : "Unparseable Source from result";
	}
	
	
	//Below this are the abstract tests
	
	@Test
	(dependsOnMethods = "buildQueryResult")
	public void checkId(){		
		assert (asr.getId() != null) : "Unparseable ID from result";
	}
	
	@Test
	(dependsOnMethods = "buildQueryResult")
	public void checkTitle(){		
		assert (asr.getTitle() != null) : "Unparseable Title from result";
	}
	
	@Test
	(dependsOnMethods = "buildQueryResult")
	public void checkUrl(){		
		assert (asr.getUrl() != null) : "Unparseable URL from result";
	}
	
	@Test
	(dependsOnMethods = "buildQueryResult")
	public void checkDescription(){		
		assert (asr.getDescription() != null) : "Unparseable Description from result";
	}
	
	
}