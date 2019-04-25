package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.Money;

@RunWith(Parameterized.class)//注释
public class TestClass {

	private static Money m;
	private int input;//定义参数
	private boolean flag;
	
	public TestClass(int input, boolean flag) {
		// TODO Auto-generated constructor stub
		this.input = input;
		this.flag = flag;
	}
	
	@Parameters
	public static Collection<Object[]> getData(){
	return Arrays.asList(new Object[][]{ {1,true},
	{0,true},
	{1000,false}
	}); 
	}
	
	@BeforeClass
	public static void setUp() {
		m = new Money();
		m.init();
	}
	
	@Test
	public void testJudge() {
		assertEquals(m.Judge(input), flag);
	}

	@AfterClass
	public static void end() {
		
	}

}
