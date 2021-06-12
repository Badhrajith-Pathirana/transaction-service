package lk.rythmogrp.transactionservice.config;

import lk.rythmogrp.transactionservice.entity.Transaction;
import lk.rythmogrp.transactionservice.repository.TransactionRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = TransactionRepository.class)
@EntityScan(basePackageClasses = Transaction.class)
@EnableJpaAuditing
public class SpringJPAConfig {
}
