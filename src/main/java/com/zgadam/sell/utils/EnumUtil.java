package com.zgadam.sell.utils;

import com.zgadam.sell.enums.CodeEnum;

/**
 * 通用枚举工具类
 * 2017-07-16 18:36
 */
public class EnumUtil {

    /**
     * 通过枚举的值获取描述
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
