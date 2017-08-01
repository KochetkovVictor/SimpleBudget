package ru.simplebudget.controller.shop;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.simplebudget.model.common.Shop;
import ru.simplebudget.service.shop.ShopService;

import java.util.List;


@RestController
@RequestMapping("/ajax/shops")
public class AjaxShopController extends AbstractShopController {

    public AjaxShopController(ShopService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Shop> getAll() {
        return super.getAll();
    }
}
