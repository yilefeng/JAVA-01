package com.lefeng;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yilefeng on 2021/1/13.
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clazz = new HelloClassLoader().findClass("Hello");
        Method declaredMethod = clazz.getDeclaredMethod("hello");
        declaredMethod.invoke(clazz.newInstance());
    }


    @Override
    protected Class<?> findClass(String name) {
        byte[] classData = new byte[0];
        try {
            classData = loadFromFile(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, classData, 0, classData.length);
    }

    private byte[] loadFromFile(String name) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(name + ".xlass");
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        int nextValue;
        while ((nextValue = inputStream.read()) != -1) {
            bout.write(nextValue);
        }
        return decode(bout.toByteArray());
    }


    private byte[] decode(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (~bytes[i]);
        }
        return bytes;
    }
}
