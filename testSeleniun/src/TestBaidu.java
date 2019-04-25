

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import jxl.*;
import jxl.read.biff.BiffException;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

@RunWith(Parameterized.class)//注释
public class TestBaidu {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;

    static Workbook workbook;
    static Sheet sheet;
    static List<String> ids;
    static List<String> names;
    static List<String> urls;

    private String cur_id;
    private String cur_pwd;
    private String cur_name;
    private String cur_url;

    private StringBuffer verificationErrors = new StringBuffer();


    public TestBaidu(String cur_id, String cur_pwd, String cur_name, String cur_url){
        this.cur_id = cur_id;
        this.cur_pwd = cur_pwd;
        this.cur_name = cur_name;
        this.cur_url = cur_url;
    }

    static {
        ids = new ArrayList<>();
        urls = new ArrayList<>();
        names = new ArrayList<>();
        try {
            workbook=Workbook.getWorkbook(new File("/Users/mengfanbo/Desktop/大三下/软件测试技术/lab2/软件测试名单.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        sheet=workbook.getSheet(0);

        Cell cell;
        for(int i=2;i<sheet.getRows();i++){
            cell=sheet.getCell(1,i);
            ids.add(cell.getContents());

            cell = sheet.getCell(2,i);
            names.add(cell.getContents());

            cell = sheet.getCell(3,i);
            urls.add(cell.getContents());

        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData(){
        ArrayList<Object[]> ret = new ArrayList<>();
        for(int i = 0; i < ids.size(); i++){
            ArrayList<Object> temp = new ArrayList<>();
            temp.add(ids.get(i));
            temp.add(ids.get(i).substring(4));
            temp.add(names.get(i));
            temp.add(urls.get(i));
            ret.add(temp.toArray());
        }

        return ret;
    }

    @BeforeClass
    public static void loadData(){

    }

    @Before
    public void setUp() throws Exception {
        String driverPath = System.getProperty("user.dir") + "/src/resources/geckodriver";
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        baseUrl = "http://121.193.130.195:8800";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testBaidu() throws Exception {

        //进行登录
            driver.get(baseUrl + "/");

            WebElement id = driver.findElement(By.name("id"));
            id.click();
            id.clear();
            id.sendKeys(cur_id);
            WebElement pwd = driver.findElement(By.name("password"));
            pwd.click();
            pwd.clear();
            pwd.sendKeys(cur_pwd);
            driver.findElement(By.id("btn_login")).click();

            String web_name = driver.findElement(By.id("student-name")).getText();
            String web_id = driver.findElement(By.id("student-id")).getText();
            String web_url = driver.findElement(By.id("student-git")).getText();

            assertEquals(web_name,cur_name);
            assertEquals(web_id,cur_id);
            assertEquals(web_url,cur_url);


    }

    @After
    public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
    }

    @AfterClass
    public static void close(){
        //最后一步：关闭资源
        workbook.close();
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}

