package com.nttdata.reportservice.infrastructure.document;

import com.nttdata.reportservice.domain.dto.TransactionDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProductRes {
  private String id;
  private String account;
  private String typeAccount;
  private List<TransactionDTO> transactions;
}
