package edu.eci.arsw.moneylaundering;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MoneyLaunderingThread extends Thread{

    private TransactionAnalyzer transactionAnalyzer;
    private TransactionReader transactionReader;
    private AtomicInteger amountOfFilesProcessed;
    private List<File> transactionFiles;
    private boolean pause = true, firstRun=true;

    public MoneyLaunderingThread(TransactionAnalyzer transactionAnalyzer, TransactionReader transactionReader, AtomicInteger amountOfFilesProcessed, List<File> transactionFiles){
        this.transactionAnalyzer = transactionAnalyzer;
        this.amountOfFilesProcessed = amountOfFilesProcessed;
        this.transactionReader = transactionReader;
        this.transactionFiles = transactionFiles;

    }

    public synchronized void unpause() {
        this.pause = true;
        this.notify();
    }

    public void pause() {
        this.pause = false;

    }

    public boolean isPaused(){
        return pause;
    }
    public boolean isFirstRun() {
        return firstRun;
    }

    public void setFirstRun(boolean firstRun) {
        this.firstRun = firstRun;
    }

    public void run(){
        int contador = 0;
        while(contador<transactionFiles.size()){
            while (!pause) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(File transactionFile : transactionFiles)
            {

                List<Transaction> transactions = transactionReader.readTransactionsFromFile(transactionFile);
                for(Transaction transaction : transactions)
                {
                    transactionAnalyzer.addTransaction(transaction);
                }
                amountOfFilesProcessed.incrementAndGet();
                contador++;
            }

        }



    }
}
