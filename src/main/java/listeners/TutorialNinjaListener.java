package listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.ExtentReportGenrator;
import utils.Utility;

public class TutorialNinjaListener implements ITestListener {

	ExtentReports extentreport;
	ExtentTest extentTest;

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentreport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + " started successfully");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName() + " Passed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		extentTest.addScreenCaptureFromPath(Utility.captureScreenshot(driver, result.getName()));
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " got Failed");

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + " got Skipped");
	}

	@Override
	public void onStart(ITestContext context) {

		extentreport = ExtentReportGenrator.generateExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {

		extentreport.flush();

		String pathOfExtent = System.getProperty("user.dir") + "\\test-output\\ExtentReport\\ExtentReport.html";
		File extentReportFile = new File(pathOfExtent);
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
