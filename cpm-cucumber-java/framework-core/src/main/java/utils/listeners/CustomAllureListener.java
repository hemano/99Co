package utils.listeners;

import ru.yandex.qatools.allure.cucumberjvm.AllureRunListener;

/**
 * Created by hemantojha on 26/07/16.
 */
public class CustomAllureListener extends AllureRunListener{
//    @Override
//    public void testFailure(Failure failure) {
//        super.testFailure(failure);
//        if (!failure.getDescription().isSuite()) { // check is needed to avoid double attaching
//            attachFailed();
//        }
//    }
//
//
//
//    @Attachment(value = "Screenshot", type = "image/png")
//    public byte[] attachFailed(){
//        return ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
//    }
}
