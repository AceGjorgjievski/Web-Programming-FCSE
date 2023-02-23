package mk.finki.ukim.mk.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class BalloonsPage extends AbstractPage {

    /**
     * najdi gi site 'tr' elementi koi shto imaat klasi balloon
     * i onie elementi koi shto kje gi najde, kje gi stavi vo listata 'balloonRows'.
     *
     * Ova kje ovozmozhi za proverka na toa kolku redici ima vo tabelata t.e.
     * kolku baloni ima vo tabelata
     */
    @FindBy(css = "tr[class=balloon]")
    private List<WebElement> balloonRows;


    @FindBy(css = ".delete-balloon")
    private List<WebElement> deleteButtons;


    /**
     * kaj nekoj css-selektor, kaj nekoi classname
     * (kaj kopchinjata)
     */
    @FindBy(className = "edit-balloon")
    private List<WebElement> editButtons;


    @FindBy(css = ".cart-balloon")
    private List<WebElement> cartButtons;


    @FindBy(css = ".add-balloon-btn")
    private List<WebElement> addBalloonButton;

    public BalloonsPage(WebDriver driver) {
        super(driver);
    }

    public static BalloonsPage to(WebDriver driver) {
        get(driver, "/balloons");
        return PageFactory.initElements(driver, BalloonsPage.class);
    }

    /**
     *
     * so ovie 'intovi' kje proverime kolku od
     * soodvetnite elementi se postaveni
     *
     * - so ova, nie vsushnost go postignuvame testiranjeto t.e.
     *  imame method koj shto ni ovozmozhuva da proverime shto se gleda
     *  i dali se gleda onaka kako shto se ochekuva
     */
    public void assertElemts(int balloonsNumber,
                             int deleteButtons,
                             int editButtons,
                             int cartButtons,
                             int addButtons) {
        Assert.assertEquals("rows do not match", balloonsNumber, this.getBalloonRows().size());
        Assert.assertEquals("delete do not match", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editButtons, this.getEditButtons().size());
        Assert.assertEquals("cart do not match", cartButtons, this.getCartButtons().size());
        Assert.assertEquals("add is visible", addButtons, this.getAddBalloonButton().size());
    }
}
