package com.example.Echo.LogicService;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
public class LogicServiceTest {
	
	@Autowired
	private LogicService logicService;
	
	private List<List<String>> testMatrix;
	
	@BeforeEach
	public void setUp()
	{
		 logicService= new LogicService();
		 testMatrix= Arrays.asList(
	                Arrays.asList("1", "2", "3"),
	                Arrays.asList("4", "5", "6")
	        );
		
	}
	
	@Test
	public void flatenMatrixUnitTest()
	{
		String expectedOutput="1,2,3,4,5,6";
		String actualOutput=logicService.flatenMatrix(testMatrix);
		System.out.print(actualOutput);
		assertEquals(expectedOutput,actualOutput,"FlatenMatrix FAILED: The flattened output did not "
				+ "match the expected comma-separated string.");
	}
	
	@Test
	public void calculateSumUnitTest()
	{
		String expectedSumOutput="21";
		String actualSumOutput= String.valueOf(logicService.calProduct(testMatrix));
		assertEquals(expectedSumOutput,actualSumOutput,"Sum Calculation FAILED: The output did not "
				+ "match the expected Sum.");
	}
	
	@Test
	public void calculateProductUnitTest()
	{
		String expectedSumOutput="720";
		String  actualSumOutput= String.valueOf(logicService.calProduct(testMatrix));
		assertEquals(expectedSumOutput,actualSumOutput,"Product Calculation FAILED: The output did not "
				+ "match the expected Product.");
	}
	
	
	@Test
	public void inverseInputMatrix()
	{
		String expectedSumOutput="6,5,4,3,2,1";
		String  actualSumOutput=logicService.inverRows(testMatrix);
		assertEquals(expectedSumOutput,actualSumOutput,"Inversion of Matrix FAILED: The output did not "
				+ "match the expected Matrix.");
	}
	

}
