package loglistener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class LogListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println(result.getName() + " test case started");
        System.out.println("Testing started in " + result.getStartMillis() + " milliseconds");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getName() + " testcase is PASSED");
        System.out.println("---------------------------");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getName() + " testcase is FAILED");
        System.out.println("---------------------------");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(result.getName() + " testcase is SKIPPED");
        System.out.println("---------------------------");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("==============================");
        System.out.println("PLANE TICKET PURCHASE PROJECT ON ENUYGUN");
        System.out.println("Starting date and time of the test : " + context.getStartDate());
        System.out.println("==============================");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("==============================");
        System.out.println("Ending date and time of the test : " + context.getEndDate());
        System.out.println("Failed tests : " + context.getFailedTests());
        System.out.println("==============================");

    }
}
