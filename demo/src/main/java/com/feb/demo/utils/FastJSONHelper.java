package com.feb.demo.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.LinkedList;
import java.util.List;

public class FastJSONHelper {

    /**
     * 将java类型的对象转换为JSON格式的字符串
     *
     * @param object java类型的对象
     * @return JSON格式的字符串
     */
    public static <T> String serialize(T object) {
        return JSON.toJSONString(object);
    }

    /**
     * 将JSON格式的字符串转换为java类型的对象或者java数组类型的对象，不包括java集合类型
     *
     * @param json JSON格式的字符串
     * @param clz  java类型或者java数组类型，不包括java集合类型
     * @return java类型的对象或者java数组类型的对象，不包括java集合类型的对象
     */
    @Nullable
    public static <T> T deserialize(String json, Class<T> clz) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        LogUtils.e(json);
        try {
            return JSON.parseObject(json, clz);
        } catch (Exception e) {
            LogUtils.e("Json解析错误", e);
            return null;
        }
    }
//	  Person[] persons = FastJSONHelper.deserialize(json, Person[].class);


    /**
     * 将JSON格式的字符串转换为List<T>类型的对象
     *
     * @param json JSON格式的字符串
     * @param clz  指定泛型集合里面的T类型
     * @return List<T>类型的对象
     */
    @NonNull
    public static <T> List<T> deserializeList(String json, Class<T> clz) {
        if (TextUtils.isEmpty(json)) {
            return new LinkedList<>();
        }
        try {
            return JSON.parseArray(json, clz);
        } catch (Exception e) {
            LogUtils.e("Json解析错误", e);
            return new LinkedList<>();
        }

    }
//	List<Person> personList = FastJSONHelper.deserializeList(json, Person.class);

//	List<String> list = new ArrayList<String>();
//	list = FastJSONHelper.deserializeList(json, String.class);


    /**
     * 将JSON格式的字符串转换成任意Java类型的对象
     *
     * @param json JSON格式的字符串
     * @param type 任意Java类型
     * @return 任意Java类型的对象
     */
    @Nullable
    public static <T> T deserializeAny(String json, TypeReference<T> type) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        LogUtils.showLargeLog(json,3900);
        try {
            return JSON.parseObject(json, type);
        } catch (Exception e) {
            LogUtils.e("Json解析错误", e);
            return null;
        }
    }

    /**
     * @param content
     * @param type
     * @return T    返回类型
     * @throws
     * @Title: deserializeAny
     * @date 2016-4-1 下午4:57:08
     * @author (黄赛赛)
     * @Description: TODO()
     */
    @Nullable
    public static <T> T deserializeAny(byte[] content, TypeReference<T> type) {
        if (content == null || content.length == 0) {
            return null;
        }
        String str = new String(content);
        return deserializeAny(str, type);
    }

//	List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
//	list = FastJSONHelper.deserializeAny(json,
//			               new TypeReference<List<HashMap<String, Object>>>() {
//			                });
}
