package net.novaborn.takeaway.pay.common.aop;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.common.tips.ErrorTip;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author xiaohun
 * @date 2016年11月12日 下午3:19:56
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截jwt相关异常
     */
    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorTip jwtException(JwtException e) {
        log.error(null, e);
        return new ErrorTip(SysExceptionEnum.TOKEN_ERROR.getCode(), SysExceptionEnum.TOKEN_ERROR.getMessage());
    }

    @ExceptionHandler(SysException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorTip sysException(SysException e) {
        log.error(null, e);
        return new ErrorTip(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorTip mismatchErrorHandler(MethodArgumentTypeMismatchException e) {
        log.error("方法:{},字段:{},参数:{},错误信息:{}", e.getParameter().getMethod(), e.getName(), e.getValue(), e.getMessage());
        return new ErrorTip(500, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorTip handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ErrorTip(
                SysExceptionEnum.ARGUMENT_VALID_ERROR.getCode(),
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
        );
    }

    /**
     * 请求的 URL 参数检验
     *
     * @param e 异常信息
     * @return 返回提示信息
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorTip handleConstraintViolationException(ConstraintViolationException e) {
        return new ErrorTip(
                SysExceptionEnum.ARGUMENT_VALID_ERROR.getCode(),
                SysExceptionEnum.ARGUMENT_VALID_ERROR.getMessage()
        );
    }
}
