package com.hdyl.pushbox;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        System.out.println(getClassType(new MyClass<List<Item>,Item>(){

        }) );

    }

    static abstract class MyClass<T,V>{


    }
    static class Item{

    }

    static class DataItem{

    }

    /***
     * 得到T的class
     *
     * @return
     */
    public static Type getClassType(Object t) {
        Type genType = t.getClass().getGenericSuperclass();
        System.out.println(genType);
        if (!(genType instanceof ParameterizedType)) {
            System.out.println("没有填写泛型参数1");
            return null;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return  params[0];
    }
}