package com.zlys.intface;

import java.io.File;
import java.io.IOException;

public interface DirectoryOption {
    boolean add();

    boolean delete();

    void listFile(File dir);

    void selectFile() throws IOException;

    void exit();

    void help();
}
