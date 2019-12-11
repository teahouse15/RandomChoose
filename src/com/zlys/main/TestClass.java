package com.zlys.main;

import com.zlys.dao.FileOptionInstance;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TestClass {

    public static void main(String[] args) throws IOException {
        FileOptionInstance instance = new FileOptionInstance();
        File file = new File("姓名文件/用户名.txt");
        List<String> nameList = instance.getUserName(file);
        System.out.println(nameList);
        int code = new Random().nextInt(nameList.size());
        System.out.println(nameList.get(code));
    }
}
