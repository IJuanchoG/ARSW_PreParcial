package edu.eci.arsw.exams.moneylaunderingapi;


import edu.eci.arsw.exams.moneylaunderingapi.model.SuspectAccount;
import edu.eci.arsw.exams.moneylaunderingapi.service.MoneyLaunderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MoneyLaunderingController
{
    @Autowired
    @Qualifier("MoneyLaunderingServiceStub")
    MoneyLaunderingService moneyLaunderingService;

    @RequestMapping( method = RequestMethod.GET, value = "/fraud-bank-accounts")
    public ResponseEntity<?> offendingAccounts() {
        return new ResponseEntity<>(moneyLaunderingService.getSuspectAccounts(),HttpStatus.ACCEPTED);
    }

    @RequestMapping( method = RequestMethod.POST, value = "/fraud-bank-accounts")
    public ResponseEntity<?> offendingAccountsPost() {
        return new ResponseEntity<>(moneyLaunderingService.getSuspectAccounts(), HttpStatus.ACCEPTED);
    }

    @RequestMapping( method = RequestMethod.GET, value = "/fraud-bank-accounts/{accountId}")
    public ResponseEntity<?> offedingAccount(@PathVariable("accountId") String accountId){

        return new ResponseEntity<>(moneyLaunderingService.getAccountStatus(accountId), HttpStatus.ACCEPTED);
    }

    @RequestMapping( method = RequestMethod.POST, value = "/fraud-bank-accounts/{accountId}")
    public ResponseEntity<?> offedingAccountPost(@PathVariable("accountId") String accountId){

        return new ResponseEntity<>(moneyLaunderingService.getAccountStatus(accountId), HttpStatus.CREATED);
    }





}
