package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListeners implements ITestListener {
    ExtentReports extent;

    ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    @Override
    public void onStart(ITestContext context){
        System.out.println("Test Execution Started");

        extent = ExtentManager.getExtentReports();
    }
    @Override
    public void onTestStart(ITestResult result){

        System.out.println("Test Started: " + result.getName());

        ExtentTest extentTest = extent.createTest(result.getName());

        test.set(extentTest);

        test.get().info("Test Execution Started");
    }
    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("Test Passed: " + result.getName());

        if (test.get() != null) {
            test.get().pass("Test Passed Successfully");
        }
    }
    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("Test Failed: " + result.getName());

        if (test.get() != null) {

            test.get().fail("Test Failed");

            if (result.getThrowable() != null) {
                test.get().fail(result.getThrowable());
            }
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {

        System.out.println("Test Skipped: " + result.getName());

        if (test.get() != null) {
            test.get().skip("Test Skipped");
        }
    }
    @Override
    public void onFinish(ITestContext context) {

        System.out.println("Test Execution Completed");

        if (extent != null) {
            extent.flush();
        }
    }

}
