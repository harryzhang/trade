package com.redpack.base.result;

import java.io.Serializable;

/**
 * 
 * @author: liuzg
 * 
 * @date 2014年7月27日 下午3:40:33
 */
public class ResultSupport<T> implements IResult<T>, Serializable {

    private static final long serialVersionUID = 1L;
    public static int        success_code = 0;     // 成功标示
    public static int        errorCode = 1;     // 错误标示
    private boolean           success;              // 成功标示
    private int               resultCode;           // 请求结果状态码
    private String            errorMessage;         // 错误信息
    private T                 model;                // 模型对象

    public ResultSupport() {
    }

    public ResultSupport(boolean success) {
        this.success = success;
    }

    public ResultSupport(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public ResultSupport(int resultCode, String errorMessage) {
        this.resultCode = resultCode;
        this.errorMessage = errorMessage;
        if(success_code == this.resultCode){
    		this.success = true;
    	}else{
    		this.success = false;
    	}
    }

    
    public ResultSupport(int resultCode) {
        this.resultCode = resultCode;
        if(success_code == this.resultCode){
    		this.success = true;
    	}else{
    		this.success = false;
    	}
    }
    
    public ResultSupport(boolean success, T model) {
        this.success = success;
        this.model = model;
    }

    @Override
    public boolean isSuccess() {
    	if(success_code == this.resultCode){
    		return true;
    	}else{
    		return false;
    	}
    }

    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

 
    public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	@Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        this.success = false;
    }

    @Override
    public T getModel() {
        return model;
    }

    @Override
    public void setModel(T model) {
        this.model = model;
    }
    
    public static IResult buildResult(int resultCode ,String msg){
    	return new ResultSupport(resultCode,msg);
    }
    
    public static IResult buildResult(int resultCode ){
    	return new ResultSupport(resultCode);
    }

}
