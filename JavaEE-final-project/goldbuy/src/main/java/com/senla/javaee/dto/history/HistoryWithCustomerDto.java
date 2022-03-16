package com.senla.javaee.dto.history;

import com.senla.javaee.dto.user.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryWithCustomerDto {
    private HistoryInfoDto history;
    private UserInfoDto customer;

    public HistoryInfoDto getHistory() {
        return history;
    }

    public void setHistory(HistoryInfoDto history) {
        this.history = history;
    }

    public UserInfoDto getCustomer() {
        return customer;
    }

    public void setCustomer(UserInfoDto customer) {
        this.customer = customer;
    }
}
