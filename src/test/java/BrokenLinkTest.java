import basetest.BaseTest;
import helper.LinkVerification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static helper.LinkVerification.printBrokenLinks;
import static helper.LinkVerification.printUncheckedLinks;

public class BrokenLinkTest extends BaseTest{
    @Test
    public void brokenLinkTest() {
        LinkVerification.checkLinks();

        if(LinkVerification.brokenLinks.size() > 0) {
            printBrokenLinks();
            Assert.fail("There are some link with response more than 400");
        }

        Assert.assertEquals(LinkVerification.brokenLinks.size(), 0);
    }
}
