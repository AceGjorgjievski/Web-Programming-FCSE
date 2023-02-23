package mk.finki.ukim.mk.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class CartPage extends AbstractPage{

    @FindBy(css = "tr[class=cart-item]")
    private List<WebElement> cartRows;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public static CartPage init(WebDriver driver) {
        return PageFactory.initElements(driver, CartPage.class);
    }


    /**
     *
     * @param cartItemsNumber - da proverime kolku elementi imame
     */
    public void assertElemts(int cartItemsNumber) {
        Assert.assertEquals("rows do not match", cartItemsNumber, this.getCartRows().size());
    }

}
