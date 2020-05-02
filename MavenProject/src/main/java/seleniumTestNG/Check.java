package seleniumTestNG;

import org.apache.log4j.pattern.ClassNamePatternConverter;
import org.testng.annotations.Test;

import bsh.classpath.BshClassPath.GeneratedClassSource;

public class Check extends ReadProperties{
	
	String ClassName = this.getClass().getSimpleName();
	@Test
	public void checking() {
		
		
		try {
			Excel excel  = new Excel(getPropertyValue("excelPath"),getPropertyValue("sheetName"));
			Excel.getData(ClassName, "DataB");
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
