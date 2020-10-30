package net.novaborn.takeaway.common.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class SysContext {
    private final ThreadLocal<Long> currentAdminId = new ThreadLocal<>();

    private final ThreadLocal<Long> currentStoreId = new ThreadLocal<>();

    public SysContext() {
        this.setCurrentAdminId(0L);
        this.setCurrentStoreId(0L);
    }

    public void setCurrentAdminId(Long adminId) {
        this.currentAdminId.set(adminId);
    }

    public Long getCurrentAdminId() {
        return this.currentAdminId.get();
    }

    public void setCurrentStoreId(Long storeId) {
        this.currentStoreId.set(storeId);
    }

    public Long getCurrentStoreId() {
        return this.currentStoreId.get();
    }
}