package com.charater.api.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoAgrsConstructor
@Builder(toBuilder =true)
@EqualsAndHashCode
public class TransactionInfo{
    String userId;
    String dateStr;
}
