package edu.eci.arsw.exams.moneylaunderingapi.service;

import edu.eci.arsw.exams.moneylaunderingapi.model.SuspectAccount;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("MoneyLaunderingServiceStub")
public class MoneyLaunderingServiceStub implements MoneyLaunderingService {


    List<SuspectAccount> suspectAccounts;

    public MoneyLaunderingServiceStub() {
        this.suspectAccounts = new ArrayList<>();
        suspectAccounts.add(new SuspectAccount("1", 300));
        suspectAccounts.add(new SuspectAccount("2", 400));
        suspectAccounts.add(new SuspectAccount("3", 500));
    }

    @Override
    public void updateAccountStatus(SuspectAccount suspectAccount) {
        suspectAccounts.forEach(x -> {
            if(x.getAccountId().equals(suspectAccount.getAccountId())) x.setAmountOfSmallTransactions(suspectAccount.getAmountOfSmallTransactions());
        });

    }

    @Override
    public SuspectAccount getAccountStatus(String accountId) {
        for (SuspectAccount s : suspectAccounts) {
            if (s.getAccountId().equals(accountId)) return s;
        }
        return null;
    }

    @Override
    public List<SuspectAccount> getSuspectAccounts() {
       return suspectAccounts;

    }

    public List<SuspectAccount> updateAccountStatus(List<SuspectAccount> suspectAccounts){
        this.suspectAccounts.forEach(x -> {
            suspectAccounts.forEach(y -> {
                if(x.getAccountId().equals(y.getAccountId())){
                    x.setAmountOfSmallTransactions(y.getAmountOfSmallTransactions());
                }
            });
        });

        return this.suspectAccounts;
    }
}
