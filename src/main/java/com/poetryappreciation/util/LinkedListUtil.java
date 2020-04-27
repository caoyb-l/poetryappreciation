package com.poetryappreciation.util;

import java.util.LinkedList;

public class LinkedListUtil {

    //存储队列中数据
    public static boolean isEmpty(LinkedList<Object> data){
        return data.size()==0?true:false;
    }

    public static void put(Object o,LinkedList<Object> data){
        data.add(o);
    }

    public static Object get(LinkedList<Object> data){
        //从队列中获取元素，获取最先放入元素，获取后从队列中删除该元素
        Object obj=data.getFirst();
        //获取后从队列中删除该元素
        data.removeFirst();
        return obj;
    }
}
