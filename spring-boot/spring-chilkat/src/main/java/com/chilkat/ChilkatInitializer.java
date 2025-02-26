package com.chilkat;

import com.chilkatsoft.CkGlobal;
import com.chilkatsoft.CkSFtp;
import com.util.Base64Utils;
import com.util.ConfigReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Component
public class ChilkatInitializer {

    private static final String LIB_NAME = "chilkat";

    static {
        if (libExist()) {
            try {
                System.loadLibrary(LIB_NAME);
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

    @EventListener(ApplicationReadyEvent.class)
    public void unlock() {
        CkGlobal glob = new CkGlobal();
        boolean success = glob.UnlockBundle(Base64Utils.decodeToString(ConfigReader.CHILKAT_LICENSE_KEY));
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

}
