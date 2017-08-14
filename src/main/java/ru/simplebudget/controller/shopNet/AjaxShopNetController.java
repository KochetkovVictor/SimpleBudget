package ru.simplebudget.controller.shopNet;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.simplebudget.model.common.ShopNet;
import ru.simplebudget.service.shopNet.ShopNetService;

import java.util.List;

@RestController
@RequestMapping(value = "/ajax/shopNet")
public class AjaxShopNetController extends AbstractShopNetController {

    public AjaxShopNetController(ShopNetService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<ShopNet> getAll() {
        return super.getAll();
    }
}
