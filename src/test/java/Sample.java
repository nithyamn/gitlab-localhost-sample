import com.browserstack.local.Local;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Sample {
    public String username = System.getenv("BROWSERSTACK_USERNAME");
    public String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    @Test
    public void test() throws Exception {
        Local bsLocal = new Local();
        HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
        bsLocalArgs.put("key", accessKey);
        bsLocal.start(bsLocalArgs);
        System.out.println(bsLocal.isRunning());

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser","chrome");
        caps.setCapability("os","Windows");
        caps.setCapability("os_version","11");
        caps.setCapability("build","Local Gitlab");
        caps.setCapability("name","test");
        caps.setCapability("browserstack.local","true");

        WebDriver driver = new RemoteWebDriver(new URL("https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"),caps);

        driver.get("http://localhost:3000/");
        driver.quit();
        bsLocal.stop();
    }
}
