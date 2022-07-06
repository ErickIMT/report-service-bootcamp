package com.nttdata.reportservice.infrastructure.document;

import com.nttdata.reportservice.domain.dto.BankAccountDTO;
import com.nttdata.reportservice.domain.dto.CreditDTO;
import lombok.Data;

import java.util.List;

@Data
public class CustomerProductsRes {

  private String customerId;
  private String customerName;
  private List<BankAccountDTO> bankAccounts;
  private List<CreditDTO> creditsAccounts;
}
