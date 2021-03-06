import helper.CommonDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import parser.FileNotFoundException;
import parser.JsonParser;
import shop.Cart;
import java.io.File;

/**
 * Created by Ksenia on 17.10.2016.
 */
public class JsonParserTest {

    private JsonParser jsonParser = new JsonParser();
    private Cart cart= new Cart("MyCartTest");


    @Test(groups = "parser")
    public void testWriteToFile(){
        jsonParser.writeToFile(cart);
        File file = new File("src/main/resources/" + cart.getCartName() + ".json");
        Assert.assertTrue(file.exists(), "File doesn't exist");

    }

    @Test(groups = "parser")
    public void testReadFromFile(){
        Cart returnedCart = jsonParser.readFromFile(new File("src/main/resources/MyCartTest.json"));
        Assert.assertEquals(cart.getCartName(), returnedCart.getCartName(), "Carts aren't equals");
    }


    @Test(groups = "parser", dataProviderClass = CommonDataProvider.class, dataProvider = "readFromFileDataProvider", expectedExceptions = FileNotFoundException.class)
    public void testReadFromFileDDT(File file, Cart cart){
        Cart returnedCart = jsonParser.readFromFile(file);
        Assert.assertEquals(returnedCart.getCartName(), cart.getCartName(), "carts aren't equals");

    }



}
