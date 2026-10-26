package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getExtentReports(){
        if (extent == null){
            String folderPath = "test-output";
            File folder = new File(folderPath);

            if (!folder.exists()){
                folder.mkdirs();
            }

            String reportPath = folderPath + "/ExtentReport.html";


            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Tester","Automation");

            extent.setSystemInfo("Browser","Chrome");

            extent.setSystemInfo("Environment","QA");


            extent.setSystemInfo("Project","TestAutomation2026");

        }
        return  extent;
    }
}
