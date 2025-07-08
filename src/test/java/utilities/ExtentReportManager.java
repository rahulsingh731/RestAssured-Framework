package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {
    private static ExtentReports extent;
    private static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // Initialize ExtentReports
    	File reportsDir = new File("reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
            System.out.println("Created reports directory");
        }
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        System.out.println("ExtentReports initialized");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test in the report
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail("Test failed: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test skipped: " + result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the report
        extent.flush();
    }
}