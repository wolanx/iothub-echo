import com.zx5435.echo.iothub.IotHubApplication;
import com.zx5435.echo.iothub.model.dao.DeviceDAO;
import com.zx5435.echo.iothub.model.db.DeviceDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = IotHubApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
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
