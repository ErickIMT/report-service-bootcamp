package com.nttdata.reportservice.application.feign.products;

import com.nttdata.reportservice.domain.dto.BankAccountDTO;
import com.nttdata.reportservice.domain.dto.CreditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "product-service" , url = "https://product-service-bootcamp.azurewebsites.net")
public interface ProductClient {

    @GetMapping("/bankAccounts/{id}")
    BankAccountDTO getBankAccountById(@PathVariable("id") String id);

    @GetMapping("/credits/{id}")
    CreditDTO getCreditById(@PathVariable("id") String id);

    @GetMapping("/credits/user/{id}")
    List<CreditDTO> getCreditByCustomerId(@PathVariable("id") String id);

    @GetMapping("/bankAccounts/user/{id}")
    List<BankAccountDTO> getBankAccountByCustomerId(@PathVariable("id") String id);

}
