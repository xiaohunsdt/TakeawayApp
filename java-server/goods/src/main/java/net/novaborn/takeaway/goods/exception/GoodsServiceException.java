package net.novaborn.takeaway.goods.exception;

import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;
import net.novaborn.takeaway.common.exception.SysException;

public class GoodsServiceException extends SysException {
    public GoodsServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        super(serviceExceptionEnum);
    }

    public GoodsServiceException(ServiceExceptionEnum serviceExceptionEnum, Object... objects) {
        super(serviceExceptionEnum, objects);
    }
}
