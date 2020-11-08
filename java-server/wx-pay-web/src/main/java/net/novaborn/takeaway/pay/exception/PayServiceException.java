package net.novaborn.takeaway.pay.exception;

import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;
import net.novaborn.takeaway.common.exception.SysException;

public class PayServiceException  extends SysException {

    public PayServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        super(serviceExceptionEnum);
    }

    public PayServiceException(ServiceExceptionEnum serviceExceptionEnum, Object... objects) {
        super(serviceExceptionEnum, objects);
    }
}
