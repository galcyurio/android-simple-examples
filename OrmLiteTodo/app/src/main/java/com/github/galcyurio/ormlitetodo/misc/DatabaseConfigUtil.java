package com.github.galcyurio.ormlitetodo.misc;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    public static void main(String[] args) throws IOException, SQLException {
        writeConfigFile(new File("app/src/main/res/raw/ormlite_config.txt"));
    }
}
