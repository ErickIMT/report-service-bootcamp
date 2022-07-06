package com.nttdata.reportservice.domain.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TransactionDTO {

  private String id;
  private String accountId;
  private String typeTransaction;
  private String commerce;
  private float amount;
  @DateTimeFormat(pattern = "yyyy/MMMM/dd HH:mm:ss")
  private Date date;
}
