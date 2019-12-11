package com.zlys.io;

import com.zlys.dao.DirectoryOptionInstance;
import com.zlys.dao.FileOptionInstance;

import java.io.*;
import java.util.Scanner;

public class Tools {
    private static Reader reader;
    private static Writer writer;
    private static Scanner scanner = new Scanner(System.in);
    private static Tools tools = new Tools();


//    public static void main(String[] args) throws IOException {
////         tools.chooseOption(dir);
////         tools.DirChoice(dir, scanner.next()); //获取用户选项
//    }


    /**
     * 检查姓名文件是否存在
     * 检测姓名文件内是否有文件
     * @return 返回一个布尔值
     * @throws IOException
     */
    public boolean checkDir() throws IOException {
        boolean flag = true;
        File dir = new File("姓名文件");
        // 检测文件夹是否存在，没有则创建
        if (!dir.exists()) {
            System.out.println("文件夹未创建");
            // 创建一个文件夹
            dir.mkdir();
            System.out.println("已创建文件夹");
        }
        //如果文件夹里的文件个数等于0
        if (dir.listFiles().length == 0) {
            System.out.println("系统检测到没有姓名文件,是否创建?(是=创建;否=退出)");
            String yesOrNo = scanner.next();
            if (yesOrNo.equals("是")) {
                tools.createNameFile(writer);
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }


    /**
     * 创建文件
     * @param writer 创建文件所需要的字符输出流
     * @throws IOException
     */
    public void createNameFile(Writer writer) throws IOException {
        scanner = new Scanner(System.in);
        String path;
        do {
            System.out.print("请输入文件名(默认以txt结尾): ");
            path = scanner.next();
            if (path.equals("quit")) {
                new DirectoryOptionInstance().optionLoop();
            }
        } while (new FileOptionInstance().checkFileIs(path));
        File newFile = new File("姓名文件/" + path + ".txt");
        writer = new FileWriter(newFile);
        writer.write("[name]");
        writer.flush();
        System.out.println(newFile.getName() + "创建成功!");
        writer.close();
    }


    /**
     * 这删除方法真草率
     * 没有我发挥的余地了(笑哭)
     * @param file
     * @return
     */
    public boolean deleteFile(File file) {
        if (file.delete()){
            return true;
        }
        return false;
    }
}
