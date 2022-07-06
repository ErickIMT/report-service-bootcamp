package com.nttdata.reportservice.infrastructure.service;

import com.nttdata.reportservice.application.feign.customer.CustomerClient;
import com.nttdata.reportservice.application.feign.products.ProductClient;
import com.nttdata.reportservice.application.feign.transactions.TransactionClient;
import com.nttdata.reportservice.domain.dto.BankAccountDTO;
import com.nttdata.reportservice.domain.dto.CreditDTO;
import com.nttdata.reportservice.domain.dto.CustomerDTO;
import com.nttdata.reportservice.domain.dto.TransactionDTO;
import com.nttdata.reportservice.infrastructure.document.CustomerProductsRes;
import com.nttdata.reportservice.infrastructure.document.ProductRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerProductsResServiceImpl implements CustomerProductsResService{

  @Autowired
  private ProductClient productClient;

  @Autowired
  private CustomerClient customerClient;

  @Autowired
  private TransactionClient transactionClient;

  @Override
  public Mono<CustomerProductsRes> getProductsByCustomer(String id) {

    CustomerDTO customerDTO = customerClient.getCustomerById(id);

    /*Validar si el usuario existe*/
    if(customerDTO.equals(null)) {
      return Mono.error(new Exception("No se encontro IdCustomer"));
    }

    /*Buscando cuentas asociadas al ID*/
    List<CreditDTO> credits = productClient.getCreditByCustomerId(id);
    List<BankAccountDTO> bankAccounts = productClient.getBankAccountByCustomerId(id);

    /*Armando Respuesta*/
    CustomerProductsRes customerProductsRes = new CustomerProductsRes();
    customerProductsRes.setCustomerId(id);
    customerProductsRes.setCustomerName(customerDTO.getName() + " " + customerDTO.getLastName());
    customerProductsRes.setBankAccounts(bankAccounts);
    customerProductsRes.setCreditsAccounts(credits);

    return Mono.just(customerProductsRes);
  }

  @Override
  public Mono<ProductRes> getProductByDate(String from, String to, String type, String idAccount) {

    ProductRes productRes = new ProductRes();
    Date fromDate, toDate;

    try {
      fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(from);
      toDate = new SimpleDateFormat("dd/MM/yyyy").parse(to);

    } catch (ParseException e) {
      throw new RuntimeException(e);
    }

    if (type.equals("Credit")) {
      CreditDTO creditDTO = productClient.getCreditById(idAccount);
      if(creditDTO.equals(null)) {return Mono.error(new Exception("No se encontro Credito con IdAccount"));}

      productRes.setAccount(creditDTO.getCreditAccount());

    } else if (type.equals("BankAccount")) {
      BankAccountDTO bankAccountDTO = productClient.getBankAccountById(idAccount);
      if(bankAccountDTO.equals(null)) {return Mono.error(new Exception("No se encontro Credito con IdAccount"));}

      productRes.setAccount(bankAccountDTO.getAccount());
    }

    List<TransactionDTO> transactions = transactionClient.getTransactionsByAccountId(idAccount).stream()
      .filter(transactionDTO -> transactionDTO.getDate().after(fromDate))
      .filter(transactionDTO -> transactionDTO.getDate().before(toDate))
      .collect(Collectors.toList());

    productRes.setId(idAccount);
    productRes.setTypeAccount(type);
    productRes.setTransactions(transactions);

    return Mono.just(productRes);
  }


}
