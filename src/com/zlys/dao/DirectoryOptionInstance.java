package com.zlys.dao;

import com.zlys.intface.DirectoryOption;
import com.zlys.io.Tools;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class DirectoryOptionInstance implements DirectoryOption {
    File file;
    Scanner scanner = new Scanner(System.in);
    File dir = new File("姓名文件");
    FileOptionInstance fileOptionInstance;
    static DirectoryOptionInstance instance = new DirectoryOptionInstance();
    Writer writer;
    Tools tools = new Tools();

    public void optionLoop() throws IOException {
        while (true) {
            System.out.println("[a:add;d:delete;e:exit;l:list;s:select;h:help]");
            String action = scanner.next();
            switch (action) {
                case "s":
                    instance.selectFile();
                    break;
                case "e":
                    instance.exit();
                    break;
                case "l":
                    instance.listFile(dir);
                    break;
                case "a":
                    instance.add();
                    break;
                case "d":
                    instance.delete();
                    break;
                case "h":
                    instance.help();
                    break;
                default:
                    System.out.println("命令错误，请重试");
            }
        }
    }


    @Override
    public boolean add() {
        try {
            tools.createNameFile(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete() {
        System.out.println("请输入被删除文件的序号");
        listFile(dir);
        int count = scanner.nextInt();
        tools.deleteFile(dir.listFiles()[count-1]);
        return false;
    }

    /**
     * 列出“姓名文件”文件夹下的所有文件
     * @param dir 文件列表
     */
    @Override
    public void listFile(File dir) {
        if (dir.listFiles().length == 0) {
            System.out.println("没有任何文件，您可以使用add命令创建一个新的文件");
        } else {
            int count = 1;
            for (File f : dir.listFiles()) {
                System.out.println(f.getName() + "\t\t" + count++);
            }
        }
    }

    /**
     * 选择文件
     */
    @Override
    public void selectFile() throws IOException {
        System.out.println("请输入文件序号");
        listFile(dir);
        try {
            file = dir.listFiles()[scanner.nextInt() - 1];
            System.out.println(file.getName());
            System.out.println("当前选择的文件-->" + file.getName());
            fileOptionInstance = new FileOptionInstance(true, file);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("没有此序号");
            listFile(dir);
        }
    }

    @Override
    public void help() {
        System.out.println("add添加文件\r\ndelete删除文件\r\nlist列出文件\r\nselect选择文件\r\nexit退出\r\nhelp获取帮助");
    }

    @Override
    public void exit() {
        System.out.println("感谢您的使用");
        System.exit(0);
    }
}
