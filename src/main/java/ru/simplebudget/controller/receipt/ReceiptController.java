package ru.simplebudget.controller.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.service.purse.PurseService;
import ru.simplebudget.service.receipt.ReceiptService;
import ru.simplebudget.service.shop.ShopService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/receipts")
public class ReceiptController {

    @Autowired
    private
    ReceiptService receiptService;
    @Autowired
    private PurseService purseService;
    @Autowired
    private ShopService shopService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAll() {
        Map<String, Object> modelMap = new HashMap<>();
        List<Receipt> receiptList = receiptService.getAll();
        modelMap.put("receiptList", receiptList);
        Double totalAmount = 0.0;
        for (Receipt receipt : receiptList)
            totalAmount += receipt.getAmount();
        modelMap.put("totalAmount", totalAmount);
        return new ModelAndView("receipts", modelMap);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView toAddReceiptPage() {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("receipt", new Receipt());
        modelMap.put("shopList", shopService.getAll());
        modelMap.put("purseList", purseService.getAll().stream().filter(Purse::isActive).collect(Collectors.toList()));
        modelMap.put("action", "Add a Receipt");
        return new ModelAndView("editReceipt", modelMap);
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public ModelAndView toUpdatePage(@PathVariable("id") String id){
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("receipt", receiptService.getById(Long.valueOf(id)));
        modelMap.put("shopList", shopService.getAll());
        modelMap.put("purseList", purseService.getAll().stream().filter(Purse::isActive).collect(Collectors.toList()));
        modelMap.put("action", "Add a Receipt");
        return new ModelAndView("editReceipt", modelMap);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addOrUpdateReceipt(HttpServletRequest request)
    {
        String receiptId=request.getParameter("id");
        Receipt receipt=new Receipt();
        receipt.setActive(true);
        receipt.setAmount(Double.valueOf(request.getParameter("value")));
        receipt.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
        receipt.setPurse(purseService.getById(Long.valueOf(request.getParameter("purse"))));
        receipt.setShop(shopService.getById(Long.valueOf(request.getParameter("shop"))));
        receipt.setId(receiptId.isEmpty() ? null:Long.valueOf(receiptId));
        if (receipt.getId()==null)
        {
            receiptService.addReceipt(receipt);
        }
        else receiptService.changeReceipt(receipt);
        return new ModelAndView("redirect:/receipts");
    }
}
