package utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;


/**
 * Created by hemantojha on 10/08/16.
 */
public class ModifyXML {

    Document document;

    public ModifyXML(String filePath){
        try {
            File inputFile = new File(filePath);
            SAXReader reader = new SAXReader();
            document = reader.read(inputFile);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public ModifyXML(File inputFile){
        try {
            SAXReader reader = new SAXReader();
            document = reader.read(inputFile);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void updateNodeValue(String xPath, String newValue) throws Exception{
        try {
            Node node = document.selectSingleNode(xPath);
            node.setText(newValue);
        } catch (Exception e) {
            throw new Exception("Unable to set node value. Incorrect xPath value : " + xPath + "\n" + e);
        }

    }

    public void updateAttributeValue(String xPath, String attrName, String newValue) throws Exception{
        try {
            Node node = document.selectSingleNode(xPath);
            Element element = (Element) node;
            if(null != element.attribute(attrName)){
                element.addAttribute(attrName,newValue);
            }else {
                throw new Exception("Unable to find attribute value for attribute name: " + attrName);
            }

        } catch (Exception e) {
            throw new Exception("Unable to find node or attribute value. xPath  : " + xPath + "\n" + e);
        }
    }

    public String getXML(){
        return document.asXML().toString();
    }

}

//class TestClass{
//
//    public static void main(String[] args) throws Exception{
//        ModifyXML modifyXML = new ModifyXML("mConsole/src/test/resources/data/initialize_payment.xml");
//        modifyXML.updateNodeValue("/root/initialize-payment/transaction/amount", "300");
//        modifyXML.updateAttributeValue("/root/initialize-payment/transaction",
//                "type-id", "newTypeId");
//        System.out.println("modifyXML.getXML() = " + modifyXML.getXML());
//    }
//
//}
