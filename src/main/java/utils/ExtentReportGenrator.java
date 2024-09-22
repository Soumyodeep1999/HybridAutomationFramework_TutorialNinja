package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportGenrator {

	public static ExtentReports generateExtentReport() {

		ExtentReports extentReport = new ExtentReports();

		File extentReportFile = new File(
				System.getProperty("user.dir") + "\\test-output\\ExtentReport\\ExtentReport.html");
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(extentReportFile);
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName("Soumyodeep - Automation Framework");
		sparkReport.config().setDocumentTitle("Tutorial Ninja Testing");
		sparkReport.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentReport.attachReporter(sparkReport);

		Properties prop = new Properties();

		File configProp = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\properties\\" + "config.properties");
		try {
			FileInputStream fis_registerProp = new FileInputStream(configProp);
			prop.load(fis_registerProp);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		extentReport.setSystemInfo("Application URL", prop.getProperty("Url"));

		return extentReport;

	}

}
