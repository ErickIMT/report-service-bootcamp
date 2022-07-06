package com.nttdata.reportservice.application.feign.transactions;

import com.netflix.discovery.converters.Auto;
import com.nttdata.reportservice.domain.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionClientService {

  @Autowired
  private TransactionClient transactionClient;

  public List<TransactionDTO> getTransactionsByAccountId(String id) {
    return transactionClient.getTransactionsByAccountId(id);
  }
}
