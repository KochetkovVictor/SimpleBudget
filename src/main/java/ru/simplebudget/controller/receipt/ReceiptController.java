package ru.simplebudget.controller.receipt;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.simplebudget.model.out.Receipt;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = "/ajax/receipts")
public class ReceiptController extends AbstractReceiptController {

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

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public Receipt getById(@PathVariable("id") Long id) {
        return super.getReceipt(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateOrCreate(Receipt receipt) {
        if (receipt.getId() == 0L) {

            super.addReceipt(receipt);
        } else {
            super.updateReceipt(receipt);
        }
    }
}
