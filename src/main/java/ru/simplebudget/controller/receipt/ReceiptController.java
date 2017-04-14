package ru.simplebudget.controller.receipt;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.simplebudget.model.out.Receipt;
import java.util.List;


@RestController
@RequestMapping(value = "/ajax/receipts")
public class ReceiptController extends AbstractReceiptController{

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Receipt> getAll(){return super.getAll();}

    /*@RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView toAddReceiptPage() {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("receipt", new Receipt());
        modelMap.put("shopList", shopService.getAll());
        modelMap.put("purseList", purseService.getAll().stream().filter(Purse::isActive).collect(Collectors.toList()));
        modelMap.put("action", "Add a Receipt");
        return new ModelAndView("receipts", modelMap);
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public ModelAndView toUpdatePage(@PathVariable("id") String id){
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("receipt", receiptService.getById(Long.valueOf(id)));
        modelMap.put("shopList", shopService.getAll());
        modelMap.put("purseList", purseService.getAll().stream().filter(Purse::isActive).collect(Collectors.toList()));
        modelMap.put("update", "Update a Receipt");
        return new ModelAndView("editReceipt", modelMap);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addOrUpdateReceipt(HttpServletRequest request)
    {
        String receiptId=request.getParameter("id");
        Receipt receipt=new Receipt();
        receipt.setActive(true);
        receipt.setAmount(Double.valueOf(request.getParameter("value")));
        receipt.setReceiptDate(LocalDate.parse(request.getParameter("dateTime")));
        receipt.setPurse(purseService.getById(Long.valueOf(request.getParameter("purse"))));
        receipt.setShop(shopService.getById(Long.valueOf(request.getParameter("shop"))));
        receipt.setId(receiptId.isEmpty() ? null:Long.valueOf(receiptId));
        if (receipt.getId()==null)
        {
            receiptService.addReceipt(receipt);
        }
        else receiptService.changeReceipt(receipt);
        return new ModelAndView("redirect:/receipts");
    }*/
}
