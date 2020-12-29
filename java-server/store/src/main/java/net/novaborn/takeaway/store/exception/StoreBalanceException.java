package net.novaborn.takeaway.store.exception;

import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;
import net.novaborn.takeaway.common.exception.SysException;

public class StoreBalanceException extends SysException {
    public StoreBalanceException(ServiceExceptionEnum serviceExceptionEnum) {
        super(serviceExceptionEnum);
    }

    public StoreBalanceException(ServiceExceptionEnum serviceExceptionEnum, Object... objects) {
        super(serviceExceptionEnum, objects);
    }
}
