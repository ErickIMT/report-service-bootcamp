package com.nttdata.reportservice.application.api;

import com.nttdata.reportservice.infrastructure.document.CustomerProductsRes;
import com.nttdata.reportservice.infrastructure.document.ProductRes;
import com.nttdata.reportservice.infrastructure.service.CustomerProductsResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reports")
public class ReportController {

  @Autowired
  private CustomerProductsResService customerProductsResService;

  @GetMapping("/customer/{id}/products")
  private Mono<CustomerProductsRes> getCustomerProducts(@PathVariable("id") String id){
    return customerProductsResService.getProductsByCustomer(id);
  }

  @GetMapping("/product/{type}/{account}")
  private Mono<ProductRes> getProductByDate(@RequestParam String from, @RequestParam String to,
                                            @PathVariable("type") String type,
                                            @PathVariable("account") String idAccount) {
    return customerProductsResService.getProductByDate(from, to, type, idAccount);
  }
}
