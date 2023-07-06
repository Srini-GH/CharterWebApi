package com.charter.api.services;

import com.charter.api.models.Rewards;
import com.charter.api.models.TransactionInfo;

public interface RewardsProgramService{

    public Rewards calculateRewards (TransactionInfo info);

    public void validateAccountInfo (TransactionInfo info);

    public TransactionInfo packageParameters (double transactionAmount);
}