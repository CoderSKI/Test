package com.jvm;


import java.io.*;
import java.lang.reflect.Method;


public class MyClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class clazz = new MyClassLoader().loadClass("Hello");
            Method method = clazz.getMethod("hello");
            method.invoke(clazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Class<?> findClass(String name) {
        try {
            FileInputStream in = new FileInputStream(new File("D:\\Projects\\JVM\\src\\com\\jvm\\Hello.xlass"));
            byte[] clazzByte = new byte[in.available()];
            for (int i = 0; i < clazzByte.length; i++) {
                clazzByte[i] = (byte) (255 - in.read());
            }
            return defineClass(name, clazzByte, 0, clazzByte.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
