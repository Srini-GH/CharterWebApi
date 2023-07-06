package com.charater.api.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoAgrsConstructor
@Builder(toBuilder =true)
@EqualsAndHashCode
public class Rewards{
    int totalRewardPoints;
    int firstMonthRewardPoints;
    int secondMonthRewardPoints;
    int thirdMonthRewardPoints;
}
