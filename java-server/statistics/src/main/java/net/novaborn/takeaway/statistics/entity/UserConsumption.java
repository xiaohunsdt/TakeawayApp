package net.novaborn.takeaway.statistics.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class UserConsumption {
    private String userId;
    private Integer totalOrderCount;
    private Integer totalOrderItemCount;
    private Integer totalOrderPrice;
    private Integer averageOrderPrice;
    private Integer averageDailyPrice;

    public UserConsumption(String userId) {
        this.userId = userId;
        this.totalOrderCount = 0;
        this.totalOrderItemCount = 0;
        this.totalOrderPrice = 0;
        this.averageOrderPrice = 0;
        this.averageDailyPrice = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserConsumption that = (UserConsumption) o;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
