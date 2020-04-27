package com.poetryappreciation.util;

public class Demo3 extends Demo1 {

    @Override
    public void b() {
        a();
    }

    public static void main(String[] args){
        new Demo3().b();
    }
}
