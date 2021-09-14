package com.zx5435.echo.iothub.controller;

import com.zx5435.echo.iothub.model.dao.ProductDAO;
import com.zx5435.echo.iothub.model.db.ProductDO;
import com.zx5435.echo.iothub.util.IdWorker;
import com.zx5435.jii.web.ApiData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zx5435
 */
@Controller
public class ProductController {

    @Resource
    ProductDAO productDAO;

    @GetMapping("/products")
    public String listHtml(Model model) {
        List<ProductDO> products = productDAO.findAll();

        model.addAttribute("products", products);
        return "product_list";
    }

    @GetMapping("/api/products")
    @ResponseBody
    public ApiData list() {
        List<ProductDO> products = productDAO.findAll();
        return ApiData.success(products);
    }

    @PostMapping("/api/product")
    @ResponseBody
    public ApiData create(@RequestBody ProductDO product) {
        product.setId(IdWorker.nextId());
        product = productDAO.save(product);
        return ApiData.success(product);
    }

    @DeleteMapping("/api/product/{id}")
    @ResponseBody
    public ApiData delete(@PathVariable long id) {
        productDAO.deleteById(id);
        return ApiData.success("Deleted");
    }

}
