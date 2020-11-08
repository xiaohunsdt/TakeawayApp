package net.novaborn.takeaway.goods.exception;

import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;
import net.novaborn.takeaway.common.exception.SysException;

public class ProduceServiceException extends SysException {
    public ProduceServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        super(serviceExceptionEnum);
    }

    public ProduceServiceException(ServiceExceptionEnum serviceExceptionEnum, Object... objects) {
        super(serviceExceptionEnum, objects);
    }
}
