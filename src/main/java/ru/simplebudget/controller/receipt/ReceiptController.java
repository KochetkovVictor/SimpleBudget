package ru.simplebudget.controller.receipt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.service.purse.PurseService;
import ru.simplebudget.service.receipt.ReceiptService;
import ru.simplebudget.service.shop.ShopService;

import java.time.LocalDate;

import java.util.List;


@RestController
@RequestMapping(value = "/ajax/receipts")
public class ReceiptController extends AbstractReceiptController {

    private final
    ShopService shopService;
    private final
    PurseService purseService;

    @Autowired
    public ReceiptController(ReceiptService service, ShopService shopService, PurseService purseService) {
        super(service);
        this.shopService = shopService;
        this.purseService = purseService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Receipt> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Receipt> getByPeriod(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                     @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return super.getByPeriod(startDate, endDate);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteReceipt(@PathVariable("id") Long id) {
        super.deleteReceipt(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Receipt getById(@PathVariable("id") Long id) {
        return super.getReceipt(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateOrCreate(@RequestParam(value = "editedShop") Long shopId,
                               @RequestParam(value="receiptDate")LocalDate receiptDate,
                               @RequestParam(value="amount")Double amount,
                               @RequestParam(value="editedPurse") Long purseId,
                               @RequestParam(value="id")Long id) {
        Receipt receipt=new Receipt();
        receipt.setId(id);
        receipt.setReceiptDate(receiptDate==null? LocalDate.now():receiptDate);
        receipt.setAmount(amount==null? 0.0:amount);
        receipt.setShop(shopService.getById(shopId));
        receipt.setPurse(purseService.getById(purseId, LoggedUser.id()));
        if (receipt.getId() == 0L) {
            receipt.setActive(true);
            super.addReceipt(receipt);
        } else {
            super.updateReceipt(receipt);
        }
    }
}
