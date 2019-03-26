package com.zgadam.sell.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回的最外层视图对象
 * Created by 廖师兄
 * 2017-05-12 14:13
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -1583056233801782816L;

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}
