package ren.com.cn.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ren.com.cn.common.base.ResponseDTO;
import ren.com.cn.common.base.ReturnCode;
import ren.com.cn.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/10/26 23:56
 * @email: renhongqiang1397@gmail.com
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseDTO handleMethodArgumentNotValidException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        log.error("MethodArgumentNotValidException", ex);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) ex;
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            return ResponseDTO.error(errorList.get(0).getDefaultMessage(), null);
        }
        return ResponseDTO.error(ex.getMessage(), null);
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseDTO handleBizException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        log.error("ServiceException {}", ex);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        return ResponseDTO.error(ReturnCode.RE_SUBMIT);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseDTO handleSysException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        log.error("SystemException {}", ex);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        return ResponseDTO.error(ReturnCode.SERVER_ERROR);
    }
}
