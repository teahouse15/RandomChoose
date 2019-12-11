package com.zlys.dao;

import java.io.*;
import java.util.*;

public class FileOptionInstance implements com.zlys.intface.FileOption {
    int i;
    Writer writer;
    Reader reader;
    Scanner scanner = new Scanner(System.in);
    DirectoryOptionInstance directoryOptionInstance = new DirectoryOptionInstance();
    FileOptionInstance instance;
    static List<Integer> deletedName;

    @Override
    public void append(File file) throws IOException {
        String name;
        System.out.println("请输入要添加的用户名");
        while (true) {
            name = scanner.next();
            if (name.equals("否")) {
                System.out.println("退出用户名追加操作");
                writer.close();
                new FileOptionInstance(true, file);
            } else {
                writer = new FileWriter(file, true);
                writer.write("\r\n" + name);
                writer.flush();
            }
            System.out.println("是否继续添加(是则继续键入名字，否则键入否)");
        }
    }

    @Override
    public void backspace(File file) throws IOException {
        deletedName = new ArrayList<>();
        while (true) {
            int number;
            System.out.println("请输入用户名序号(输入9527删除0退出删除操作)");
            display(getUserName(file));
            while (true) {
                try {
                    number = scanner.nextInt();
                    if (number == 0) {
                        System.out.println("退出删除操作");
                        new FileOptionInstance(true, file);
                    } else if (number == 9527) {
                        System.out.println(intToString(deletedName, file));
                        oldToNew(intToString(deletedName, file), file);
                    } else {
                        deletedName.add(number);
                        System.out.println("是否继续删除(是则继续键入序号，否则键入0)");
                        display(getUserName(file));
                    }
                } catch (InputMismatchException e) {
                    System.out.println("输入错误，请重新输入");
                    scanner.nextLine();
                }
            }
        }
    }

    public List<String> getUserName(File file) throws IOException {
        reader = new FileReader(file);
        StringBuilder sb = new StringBuilder();

        while(true) {
            if (!((i = reader.read()) != -1)) break;
            sb.append((char)i);
        }

        String names = sb.toString();
        List<String> users = Arrays.asList(names.split("\r\n"));
        ArrayList<String> list = new ArrayList<>(users);
        list.remove(0);
        if (list.size() == 0) {
            System.out.println("文件内无内容");
        }
        return list;
    }

    /**
     * 显示所选文件的用户名
     * @param nameList 文件
     * @throws IOException
     * @return
     */
    @Override
    public void display(List<String> nameList) throws IOException {
        int count = 1;
        for (String a : nameList) {
            System.out.println(a + "\t" + count++);
        }
    }

    @Override
    public void quit() throws IOException {
        System.out.println("返回文件夹视图");
        directoryOptionInstance.optionLoop();
    }

    public FileOptionInstance() { }

    public FileOptionInstance(boolean flag, File file) throws IOException {
        instance = new FileOptionInstance();
        if (flag) {
            System.out.println("进入文件视图");
            while (true) {
                System.out.println("(" + file.getName()+ ")[a:append;b:backspace;q:quit;e:exit;d:display;s:start;h:help]");
                String action = scanner.next();
                switch (action) {
                    case "d":
                        instance.display(getUserName(file));
                        break;
                    case "e":
                        instance.exit();
                        break;
                    case "a":
                        instance.append(file);
                        break;
                    case "b":
                        instance.backspace(file);
                        break;
                    case "q":
                        instance.quit();
                        break;
                    case "h":
                        instance.help();
                        break;
                    case "s":
                        instance.start(file);
                        break;
                    default:
                        System.out.println("命令错误，请重试");
                }
            }
        }
    }


    @Override
    public void exit() {
        System.out.println("感谢您的使用");
        System.exit(0);
    }

    @Override
    public void help() {
        System.out.println("append添加用户名\r\nbackspace删除用户名\r\nquit返回文件夹视图\r\nexit退出程序\r\ndisplay显示用户名\r\nstart开始程序\r\nhelp帮助信息");
    }


    /**
     * 检测创建的文件名是否有相同
     * @param fileName 文件名
     * @return
     */
    public boolean checkFileIs(String fileName) {
        File files = new File("姓名文件");
        for (File f : files.listFiles()) {
            if (f.getName().equals(fileName+".txt")) {
                System.out.println("文件名重复，请重试(输入quit退出)");
                return true;
            }
        }
        return false;
    }

    @Override
    public void start(File file) {
        FileOptionInstance instance = new FileOptionInstance();
        List<String> nameList;
        try {
            nameList = instance.getUserName(file);
            System.out.println(nameList);
            int code = new Random().nextInt(nameList.size());
            System.out.println("天选之子就是-->" + nameList.get(code));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将被删除用户名的序号转换为用户名
     * @param deletedName 被删除任命的序号列表
     * @param file 当前操作文件
     * @return
     * @throws IOException
     */
    public List<String> intToString(List<Integer> deletedName, File file) throws IOException {
        List<String> usernames = new ArrayList<String>();
        for (int i : deletedName) {
            usernames.add(new FileOptionInstance().getUserName(file).get(i-1));
        }
        return usernames;
    }

    public void oldToNew(List<String> list, File file) throws IOException {

        instance = new FileOptionInstance();
        List<String> nameList = instance.getUserName(file);
        int number = list.size();

        nameList.removeAll(list);

        System.out.println(nameList);
        file.delete();
        Writer writer = new FileWriter(file);
        writer.write("[name]");
        writer.flush();

        for (String a : nameList) {
            writer.write("\r\n" + a);
            writer.flush();
        }
        writer.close();
    }
}
