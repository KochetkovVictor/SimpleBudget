package ru.simplebudget.controller.shopNet;

import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.common.ShopNet;
import ru.simplebudget.service.shopNet.ShopNetService;

import java.util.List;

public abstract class AbstractShopNetController {

    private final
    ShopNetService service;

    @Autowired
    public AbstractShopNetController(ShopNetService service) {
        this.service = service;
    }

    List<ShopNet> getAll(){
        return service.getAll();
    }

    public List<ShopNet> getByTemplate(String template) {
        return service.getByTemplate(template);
    }
}
