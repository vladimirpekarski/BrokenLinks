import basetest.BaseTest;
import helper.BrokenLink;
import org.testng.Assert;
import org.testng.annotations.Test;

import static helper.BrokenLink.printBrokenLinks;
import static helper.BrokenLink.printUncheckedLinks;

public class BrokenLinkTest extends BaseTest{
    @Test
    public void brokenLinkTest() {
        BrokenLink.checkBrokenLinks();

        if(BrokenLink.brokenLinks.size() > 0) {
            printBrokenLinks();
            Assert.fail("There are some link with response more than 400");
        }

        if(BrokenLink.uncheckedLinks.size() > 0) {
            printUncheckedLinks();
        }

        Assert.assertEquals(BrokenLink.brokenLinks.size(), 0);
    }
}
