package utils;

import org.junit.runner.notification.Failure;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.cucumberjvm.AllureRunListener;

/**
 * Created by hemantojha on 26/07/16.
 */
public class CustomAllureListener extends AllureRunListener{
    @Override
    public void testFailure(Failure failure) {
        super.testFailure(failure);
        if (!failure.getDescription().isSuite()) { // check is needed to avoid double attaching
            attachFailed();
        }
    }


    @Attachment(value = "Message", type = "text/plain")
    public String attachFailed(){
        return "Test failed!";
    }
}
