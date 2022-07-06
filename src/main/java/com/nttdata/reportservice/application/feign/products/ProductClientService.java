package com.nttdata.reportservice.application.feign.products;

import com.nttdata.reportservice.domain.dto.BankAccountDTO;
import com.nttdata.reportservice.domain.dto.CreditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductClientService {
    @Autowired
    private ProductClient productClient;

    private BankAccountDTO getBankAccountById(String id){
        return productClient.getBankAccountById(id);
    }

    private CreditDTO getCreditById(String id){
        return productClient.getCreditById(id);
    }

    private List<BankAccountDTO> getBankAccountsByCustomerId(String id) {
        return productClient.getBankAccountByCustomerId(id);
    }

    private List<CreditDTO> getCreditsByCustomerId(String id) {
        return productClient.getCreditByCustomerId(id);
    }

}
