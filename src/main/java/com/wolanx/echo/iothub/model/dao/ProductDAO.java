package com.wolanx.echo.iothub.model.dao;

import com.wolanx.echo.iothub.model.db.ProductDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wolanx
 */
@Repository
public interface ProductDAO extends JpaRepository<ProductDO, Long> {

}
