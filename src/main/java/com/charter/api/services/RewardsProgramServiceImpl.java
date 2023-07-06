package com.charter.api.services;

import com.charter.api.models.Rewards;
import com.charter.api.models.TransactionInfo;
import com.charter.api.models.TransactionsData;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@service
@Slf4j
public class RewardsProgramServiceImpl implements RewardsProgramService{

    public Rewards calculateRewards(TransactionInfo info){

        Rewards rewards;
        int firstMonthRewardPoints = 0;
        int secondMonthRewardPoints = 0;
        int thirdMonthRewardPoints = 0;
        int totalRewardPoints = 0;

        List<TransactionsData> transactionDataList = getUserTransactionsForLastThreeMonths(info.getUserId());

        if(transactionDataList !=null && transactionDataList.size > 0){
            for(TransactionsData transactionData :transactionDataList){
                if(transactionData.getTransactioAmount > 100){
                    if(checkForCurrentMonth(transactionData.getTransactionDate())){
                        firstMonthRewardPoints += (info.getTransactioAmount() -100)*2 + 50;
                        rewards.setFirstMonthRewardPoints(firstMonthRewardPoints);
                    }else if(checkForPreviousMonth(transactionData.getTransactionDate())){
                        secondMonthRewardPoints += (info.getTransactioAmount() -100)*2 + 50;
                        rewards.setSecondMonthRewardPoints(secondMonthRewardPoints);
                    }else if(checkForTwoMonthsAgo(transactionData.getTransactionDate())){
                        thirdMonthRewardPoints += (info.getTransactioAmount() -100)*2 + 50;
                        rewards.setThirdMonthRewardPoints(thirdMonthRewardPoints);
                    }
                }

            }
        }

        totalRewardPoints = firstMonthRewardPoints + secondMonthRewardPoints + thirdMonthRewardPoints
        rewards.setTotalRewardPoints(totalRewardPoints);

        return rewards;
    }


    public void validateAccountInfo (TransactionInfo info){

        if(info.getTransactioAmount < 0){
            log.error("Negative value found during validation");
            throw new Exception (BAD_REQUEST, "Negative ammount");

        }
        log.info ("Validate info");

    }

    public TransactionInfo packageParameters (double transactionAmount){
        TransactionInfo info = new TransactionInfo();
        info.setTransactionAmount(transactionAmount);
        return info;

    }

    private List<TransactionsData> getUserTransactionsForLastThreeMonths (String userId){
        Map<String, List<TransactionsData>> transactionDataMap = getLastThreeMonthsTransactionsMap();
        return transactionsDataMap.get(userId);
    }

    private Map<String, List<TransactionsData>> getLastThreeMonthsTransactionsMap(){

        Map<String, List<TransactionsData>> transactionDataMap = new HashMap<String, List<TransactionsData>> ();

        List<TransactionsData> transactionDataList = new ArrayList<TransactionsData>();

        TransactionsData transactionDataOfUser1 = new TransactionsData();
        transactionDataOfUser1.setTransactionDate("2023-06-27");
        transactionDataOfUser1.setTransactionAmount(120);

        TransactionsData transactionDataOfUser11 = new TransactionsData();
        transactionDataOfUser11.setTransactionDate("2023-06-10");
        transactionDataOfUser11.setTransactionAmount(150);

        TransactionsData transactionDataOfUser2 = new TransactionsData();
        transactionDataOfUser2.setTransactionDate("2023-05-27");
        transactionDataOfUser2.setTransactionAmount(120);

        TransactionsData transactionDataOfUser22 = new TransactionsData();
        transactionDataOfUser22.setTransactionDate("2023-05-10");
        transactionDataOfUser22.setTransactionAmount(150);

        TransactionsData transactionDataOfUser3 = new TransactionsData();
        transactionDataOfUser3.setTransactionDate("2023-04-27");
        transactionDataOfUser3.setTransactionAmount(110);

        TransactionsData transactionDataOfUser33 = new TransactionsData();
        transactionDataOfUser33.setTransactionDate("2023-04-10");
        transactionDataOfUser33.setTransactionAmount(200);

        transactionDataList.add(transactionDataOfUser1);
        transactionDataList.add(transactionDataOfUser11);

        transactionDataList.add(transactionDataOfUser2);
        transactionDataList.add(transactionDataOfUser22);


        transactionDataList.add(transactionDataOfUser3);
        transactionDataList.add(transactionDataOfUser33);

        transactionsDataMap.put("user1", transactionDataList);

        return transactionsDataMap;
    }    

    private boolean checkForCurrentMonth(String date){
        LocalDate givenDate = LocalDate.parse(date);
        int currentMonth = LocalDate.now().getMonthValue();
        return givenDate.getMonthValue() == currentMonth;
    }

    private boolean checkForPreviousMonth(String date){
        LocalDate givenDate = LocalDate.parse(date);
        int previousMonth = LocalDate.now().minusMonths(1).getMonthValue();
        return givenDate.getMonthValue() == previousMonth;
    }

    private boolean checkForCurrentMonth(String date){
        LocalDate givenDate = LocalDate.parse(date);
        int twoMonthsAgo = LocalDate.now().minusMonths(2).getMonthValue();
        return givenDate.getMonthValue() == twoMonthsAgo;
    }

}    