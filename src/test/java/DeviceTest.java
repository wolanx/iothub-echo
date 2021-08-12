import com.zx5435.iothub.echo.IotHubTest;
import com.zx5435.iothub.echo.model.dao.DeviceDAO;
import com.zx5435.iothub.echo.model.db.DeviceDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = IotHubTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DeviceTest {

    @Resource
    DeviceDAO deviceDAO;

    @Test
    void asdf() {
        DeviceDO dev = new DeviceDO();
        dev.setId(123L);
        dev.setDeviceName("asd");
        dev.setType(1);
        deviceDAO.save(dev);

        List<DeviceDO> all = deviceDAO.findAll();

        for (DeviceDO one : all) {
            System.out.println("one = " + one);
        }
    }

}
