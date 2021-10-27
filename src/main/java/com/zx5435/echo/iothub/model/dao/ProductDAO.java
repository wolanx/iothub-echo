package com.zx5435.echo.iothub.model.dao;

import com.zx5435.echo.iothub.model.db.ProductDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zx5435
 */
public interface ProductDAO extends JpaRepository<ProductDO, Long> {

}
