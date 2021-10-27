package com.zx5435;

import com.zx5435.echo.iothub.model.vo.DeviceVO;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import java.util.List;

@CheckedTemplate
public class Templates {

    public static native TemplateInstance index(List<DeviceVO> devices);

}
