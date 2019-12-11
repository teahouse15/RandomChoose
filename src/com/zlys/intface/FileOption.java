package com.zlys.intface;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileOption {

    void display(List<String> nameList) throws IOException;

    void quit() throws IOException;

    void exit();

    void help();

    void append(File file) throws IOException;

    void backspace(File file) throws IOException;

    void start(File file);
}
