package net.novaborn.takeaway.coupon.exception;

import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;
import net.novaborn.takeaway.common.exception.SysException;

public class CouponServiceException extends SysException {
    public CouponServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        super(serviceExceptionEnum);
    }

    public CouponServiceException(ServiceExceptionEnum serviceExceptionEnum, Object... objects) {
        super(serviceExceptionEnum, objects);
    }
}
