package com.zx5435.echo.iothub.model.dao;

import com.zx5435.echo.iothub.model.db.ProductDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zx5435
 */
@Repository
public interface ProductDAO extends JpaRepository<ProductDO, Long> {

}
