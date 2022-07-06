package com.nttdata.reportservice.application.feign.customer;

import com.nttdata.reportservice.domain.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@FeignClient(value = "customer-service", url = "localhost:8091/customers")
public interface CustomerClient {

  @GetMapping("/{id}")
  CustomerDTO getCustomerById(@PathVariable("id") String id);
}
