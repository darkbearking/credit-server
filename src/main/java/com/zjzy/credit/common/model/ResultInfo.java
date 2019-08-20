package com.zjzy.credit.common.model;

import com.zjzy.credit.common.consts.Consts;

/**
 * 客户端的HTTP调用的应答结果类.
 * @title ResultInfo
 * @description 应答结果类，返回信息包括调用状态、可读的msg、结果数据.
 * @author hanzhiwei
 * @date 2016年10月20日
 * @version 1.0
 */
public class ResultInfo<T> {
    private int status = Consts.RESULT_CODE_SUCCESS;
    private String statusInfo = "SUCCESS"; // 操作结果描述信息

    private T data;// 操作返回数据绑定

    public final static ResultInfo<?> SUCCESSRESULT = new ResultInfo<Object>();

    public static <T> ResultInfo<T> error() {
        ResultInfo<T> res = new ResultInfo<T>(Consts.RESULT_CODE_COMMONERR, "ERROR");
        return res;
    }

    public static <T> ResultInfo<T> errorMessage(String errorMessage) {
        ResultInfo<T> res = new ResultInfo<T>(Consts.RESULT_CODE_COMMONERR, errorMessage);
        return res;
    }

    public static <T> ResultInfo<T> errorMessage(String errorMessage, T data) {
        ResultInfo<T> res = new ResultInfo<T>(Consts.RESULT_CODE_COMMONERR, errorMessage);
        res.setData(data);
        return res;
    }

    public static <T> ResultInfo<T> result(int status, String info) {
        ResultInfo<T> res = new ResultInfo<T>();
        res.status = status;
        res.statusInfo = info;
        return res;
    }

    public static <T> ResultInfo<T> result(int status, String info, T data) {
        ResultInfo<T> res = new ResultInfo<T>();
        res.status = status;
        res.statusInfo = info;
        res.data = data;
        return res;
    }

    public static <T> ResultInfo<T> success() {
        ResultInfo<T> res = new ResultInfo<T>();
        return res;
    }

    public static <T> ResultInfo<T> success(T data) {
        ResultInfo<T> res = new ResultInfo<T>();
        res.setData(data);
        return res;
    }

    public static <T> ResultInfo<T> successMessage(String message) {
        ResultInfo<T> res = new ResultInfo<T>(Consts.RESULT_CODE_SUCCESS, message);
        return res;
    }

    public ResultInfo() {
    }

    public ResultInfo(int status, String statusInfo) {
        this.status = status;
        this.statusInfo = statusInfo;
    }

    public ResultInfo(int status, String statusInfo, T data) {
        super();
        this.status = status;
        this.statusInfo = statusInfo;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + status;
        result = prime * result + ((statusInfo == null) ? 0 : statusInfo.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ResultInfo<?> other = (ResultInfo<?>) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        if (status != other.status) {
            return false;
        }
        if (statusInfo == null) {
            if (other.statusInfo != null) {
                return false;
            }
        } else if (!statusInfo.equals(other.statusInfo)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ResultInfo [status=" + status + ", statusInfo=" + statusInfo + ", data=" + data + "]";
    }

}
