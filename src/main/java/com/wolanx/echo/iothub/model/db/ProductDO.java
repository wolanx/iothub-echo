package com.wolanx.echo.iothub.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wolanx
 */
@Entity
@Table(name = "iot_product")
@Data
public class ProductDO implements Serializable {

    private static final long serialVersionUID = 1743314721123240445L;

    @Id
    private Long id;

    @Column(unique = true)
    private String productKey;

    @Column(unique = true)
    private String productName;

}
