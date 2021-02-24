package test;

import org.junit.Assert;
import org.junit.Test;

import main.application.strategy.helper.IndexCalcualtionHelper;

public class GridIndexCalculatorTest {

	@Test
	public void testOffSuitedGridCalculator() {

		
		//AKo has index 13
		int index = IndexCalcualtionHelper.getOffsuitedIndex(14,13);
		Assert.assertTrue(String.format("Wrong index calculations, Expected %d, but was %d", 13, index), index == 13);
		
		//K8o has index 79
		index = IndexCalcualtionHelper.getOffsuitedIndex(13,8);
		Assert.assertTrue(String.format("Wrong index calculations, Expected %d, but was %d", 79, index), index == 79);
		
		//43o has index 153
		index = IndexCalcualtionHelper.getOffsuitedIndex(4,3);
		Assert.assertTrue(String.format("Wrong index calculations, Expected %d, but was %d", 153, index), index == 153);

	}
	
	

	@Test
	public void testSuitedGridCalculator() {

		//AKs has index 1
		int index = IndexCalcualtionHelper.getSuitedIndex(14,13);
		Assert.assertTrue(String.format("Wrong index calculations, Expected %d, but was %d", 1, index), index == 1);
		
		//95s has index 74
		index = IndexCalcualtionHelper.getSuitedIndex(9,5);
		Assert.assertTrue(String.format("Wrong index calculations, Expected %d, but was %d", 74, index), index == 74);
		
		
		
	}
}
