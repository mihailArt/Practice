package market.controllers;

import market.entity.Purchase;
import market.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Purchase> getPurchase(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Purchase purchase = purchaseService.getById(id);

        if (purchase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(purchase, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> purchases = purchaseService.getAll();

        if (purchases.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Purchase> savePurchase(@RequestBody @Valid Purchase purchase) {
        HttpHeaders headers = new HttpHeaders();

        if (purchase == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Purchase checkPurchase = purchaseService.getById(purchase.getId());
        if (checkPurchase == null){
            purchaseService.save(purchase);
            return new ResponseEntity<>(purchase, headers, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(checkPurchase, headers, HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Purchase> updatePurchase(@PathVariable("id") Integer id, @RequestBody @Valid Purchase purchase, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (purchase == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Purchase checkPurchase = purchaseService.getById(id);

        if(checkPurchase != null){
            checkPurchase.setProduct(purchase.getProduct());
            checkPurchase.setClient(purchase.getClient());
            checkPurchase.setDate(purchase.getDate());
            checkPurchase.setCost(purchase.getCost());
            checkPurchase.setNumber(purchase.getNumber());
            purchaseService.save(checkPurchase);
            return new ResponseEntity<>(purchase, headers, HttpStatus.OK);
        }
        else
            return  new ResponseEntity<>( headers, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Purchase> deletePurchase(@PathVariable("id") Integer id) {
        Purchase purchase = purchaseService.getById(id);

        if (purchase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        purchaseService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
