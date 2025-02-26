package com.example;

import com.chilkatsoft.CkBinData;
import com.chilkatsoft.CkGlobal;
import com.chilkatsoft.CkSFtp;
import com.util.Base64Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class Base64Zip {

    private static final String LIB_NAME = "chilkat";
    private static final Path path = Paths.get(SystemUtils.USER_DIR, "src", "main", "resources", "data");

    static {
        if (libExist()) {
            try {
                System.loadLibrary(LIB_NAME);
                unlock();
                log.info("Library {} loaded successfully.", LIB_NAME);
            } catch (UnsatisfiedLinkError e) {
                log.error("Native code library failed to load.", e);
                System.exit(1);
            }
        } else {
            log.error("Chilkat library does not exist.");
        }
    }

    private static boolean libExist() {
        String libraryName = System.mapLibraryName(LIB_NAME); // "chilkat.dll" or "libchilkat.so"
        String[] libPaths = System.getProperty("java.library.path").split(File.pathSeparator);
        for (String path : libPaths) {
            if (path != null && path.contains(LIB_NAME)) {
                return Files.exists(Paths.get(path, libraryName));
            }
        }
        return false;
    }

    public static void unlock() {
        CkGlobal glob = new CkGlobal();
        boolean success = glob.UnlockBundle(Base64Utils.decodeToString("VFNEUENILkNCMTEyMjAyMl90ZzZLZGt4TjE3cGg="));
        if (!success) {
            log.info("Unlock failed: {}", glob.lastErrorText());
            return;
        }

        int status = glob.get_UnlockStatus();
        if (status == 2) {
            log.info("Unlocked using purchased unlock code.");
        } else {
            log.info("Unlocked in trial mode.");
        }
        CkSFtp sftp = new CkSFtp();
        log.info("Chilkat version: {}", sftp.version());
    }

    // Base64 to zip
    public static void main(String[] args) {
        CkBinData pdfData = new CkBinData();

        boolean success = pdfData.LoadFile(path + "/helloWorld.pdf");
        if (!success) {
            System.out.println("failed to load PDF file.");
            return;
        }
        String b64 = pdfData.getEncoded("base64");

        CkBinData zipData = new CkBinData();
        if (zipData.AppendEncoded(b64, "base64")) {
            success = zipData.WriteFile(path + "/out.zip");
            if (!success) {
                System.out.println("failed to write Zip file.");
            }
        }
    }

}
