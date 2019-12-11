package com.zlys.main;

import com.zlys.dao.DirectoryOptionInstance;
import com.zlys.io.Tools;

import java.io.*;
public class main {

    public static void main(String[] args) throws IOException {
        System.out.println("-------------天选之人-------------");
        if (!new Tools().checkDir()) System.exit(0);
        new DirectoryOptionInstance().optionLoop();
    }
}
