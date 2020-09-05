package net.novaborn.takeaway.admin.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SysContext {
    private final ThreadLocal<Long> currentAdminId = new ThreadLocal<>();

    private final ThreadLocal<Long> currentStoreId = new ThreadLocal<>();

    public void setCurrentAdminId(Long adminId) {
        log.info(adminId.toString());
        this.currentAdminId.set(adminId);
    }

    public Long getCurrentAdminId() {
        return this.currentAdminId.get();
    }

    public void setCurrentStoreId(Long storeId) {
        log.info(storeId.toString());
        this.currentStoreId.set(storeId);
    }

    public Long getCurrentStoreId() {
        return this.currentStoreId.get();
    }
}