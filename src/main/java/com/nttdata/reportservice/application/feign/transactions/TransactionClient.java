package com.nttdata.reportservice.application.feign.transactions;

import com.nttdata.reportservice.domain.dto.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

import java.util.List;

@FeignClient(value = "transaction-service", url = "localhost:8093/transactions")
public interface TransactionClient {

  @GetMapping("/{id}")
  List<TransactionDTO> getTransactionsByAccountId(@PathVariable("id") String id);
}
