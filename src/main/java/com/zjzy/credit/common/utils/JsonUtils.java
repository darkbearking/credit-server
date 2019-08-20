package com.zjzy.credit.common.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Json操作工具类.
 * @title JsonUtils
 * @description Json操作工具类. 
 * @author zhiwei.han
 * @date 2019年8月11日
 */
public class JsonUtils {
    private static final Gson GSON = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .registerTypeHierarchyAdapter(Date.class, new JsonSerializer<Date>() {
                @Override
                public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
                    if (date == null) {
                        return JsonNull.INSTANCE;
                    }
                    return new JsonPrimitive(DateHelper.normalDateFormat(date));
                }
            }).create();

    private JsonUtils() {
    }

    /**
     * 转换json字符串为java bean对象.
     * @param jsonStr json字符串，不能为blank。
     * @param cls 目标类的class
     * @return 目标bean实例.
     */
    public static <T> T fromJson(String jsonStr, Class<T> cls) {
        Preconditions.checkNotNull(jsonStr);
        Preconditions.checkArgument(StringUtils.isNotEmpty(jsonStr));

        return GSON.fromJson(jsonStr, cls);
    }

    /**
     * 转换jsonObject为java对象
     * @param jsonObj json对象，不能为null
     * @param cls 目标类的class
     * @return 目标实例
     */
    public static <T> T fromJson(JsonObject jsonObj, Class<T> cls) {
        Preconditions.checkNotNull(jsonObj);

        return GSON.fromJson(jsonObj, cls);
    }

    /**
     * 将src转换为json字符串.
     * @param src 待转换的对象.
     * @return 转换后的json字符串
     */
    public static String toJson(Object src) {
        Preconditions.checkNotNull(src);

        return GSON.toJson(src);
    }

    /**
     * 将对象src转换为json元素
     * @param src 待转换对象
     * @return 转换后的json元素
     */
    public static JsonElement toJsonTree(Object src) {
        Preconditions.checkNotNull(src);

        return GSON.toJsonTree(src);
    }

    /**
     * 将传入的json字符串转换成相应对象的list.
     * @param jsonStr 待转换的json字符串，不能为null和blank.
     * @param cls 目标List中元素的class
     * @return json数组对应的java 对象list.
     */
    public static <T> List<T> listFromJson(String jsonStr, Class<T> cls) {
        Preconditions.checkNotNull(jsonStr);
        Preconditions.checkArgument(StringUtils.isNotEmpty(jsonStr));

        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(jsonStr);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        List<T> objs = new ArrayList<T>();
        for (JsonElement e : jsonArray) {
            T obj = GSON.fromJson(e, cls);
            objs.add(obj);
        }
        return objs;
    }

    /**
     * 将传入的jsonarray转换成相应对象的list.
     * @param jsonArray 待转换的json数组，不能为null.
     * @param cls 目标List中元素的class
     * @return json数组对应的java 对象list.
     */
    public static <T> List<T> listFromJson(JsonArray jsonArray, Class<T> cls) {
        Preconditions.checkNotNull(jsonArray);

        List<T> objs = new ArrayList<T>();
        for (JsonElement e : jsonArray) {
            T obj = GSON.fromJson(e, cls);
            objs.add(obj);
        }
        return objs;
    }

    /**
     * 判断jsonobj中的成员是否不存在或者为empty.
     * 其中empty的判定标准采用字符串的方式.
     * @param jsonObj
     * @param memberName
     * @return
     */
    public static boolean isMemberEmpty(JsonObject jsonObj, String memberName) {
        if (jsonObj == null) {
            return true;
        }
        if (!jsonObj.has(memberName)) {
            return true;
        }
        if (jsonObj.get(memberName) == null) {
            return true;
        }

        if (StringUtils.isEmpty(jsonObj.get(memberName).getAsString())) {
            return true;
        }
        return false;
    }

    /**
     * 获取json对象的成员字面值
     * @param jsonObj json对象
     * @param member 成员名
     * @return 对应的字面值
     */
    public static String getMemberValue(JsonObject jsonObj, String member) {
        Preconditions.checkNotNull(jsonObj);
        if (!jsonObj.has(member)) {
            return null;
        }

        return jsonObj.get(member).getAsString();
    }

    /**
     * 创建只包含传入指定元素的json数组.
     * @param el 待加入的元素
     * @return 包含传入元素的数组
     */
    public static JsonArray singleElementArray(JsonElement el) {
        JsonArray array = new JsonArray();
        array.add(el);
        return array;
    }

    /**
     * 解析json字符串为json元素
     * @param jsonStr
     * @return
     */
    public static JsonElement parser(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)) {
            return JsonNull.INSTANCE;
        }
        JsonParser parser = new JsonParser();
        return parser.parse(jsonStr);
    }

    /**
     * 返回空数组
     * @return
     */
    public static JsonArray emptyArray() {
        return new JsonArray();
    }

    /**
     * 判断当前array是否为空数组
     * @param array
     * @return
     */
    public static boolean isEmpty(JsonArray array) {
        if (array == null || array.size() == 0) {
            return true;
        }
        return false;
    }
}
