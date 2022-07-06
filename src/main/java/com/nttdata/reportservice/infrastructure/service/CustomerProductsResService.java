package com.nttdata.reportservice.infrastructure.service;

import com.nttdata.reportservice.infrastructure.document.CustomerProductsRes;
import com.nttdata.reportservice.infrastructure.document.ProductRes;
import reactor.core.publisher.Mono;

public interface CustomerProductsResService {

  Mono<CustomerProductsRes> getProductsByCustomer(String id);

  Mono<ProductRes> getProductByDate(String from, String to, String type, String idAccount);
}
