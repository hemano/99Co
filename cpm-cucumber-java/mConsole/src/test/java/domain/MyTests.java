package domain;

import utils.PropertyReader;

/**
 * Created by hemantojha on 21/07/16.
 */
public class MyTests {


    public static void main(String[] args) throws Exception{
        PropertyReader reader = new PropertyReader();
        String  br = reader.readProperty("browser");
        System.out.println("br = " + br);
    }
}
