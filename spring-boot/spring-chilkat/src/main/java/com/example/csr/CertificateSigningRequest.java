package com.example.csr;

import com.chilkatsoft.*;
import com.util.Base64Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class CertificateSigningRequest {

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

    public static void main(String[] argv) {
        // Note: Requires Chilkat v9.5.0.65 or greater.

        // This requires the Chilkat API to have been previously unlocked.
        // See Global Unlock Sample for sample code.

        // First generate an RSA private key.
        CkRsa rsa = new CkRsa();

        // Generate a random 2048-bit RSA key.
        boolean success = rsa.GenerateKey(2048);
        if (!success) {
            System.out.println(rsa.lastErrorText());
            return;
        }

        // Get the private key
        CkPrivateKey privKey = rsa.ExportPrivateKeyObj();

        // Create the CSR object and set properties.
        CkCsr csr = new CkCsr();

        // Specify the Common Name.  This is the only required property.
        // For SSL/TLS certificates, this would be the domain name.
        // For email certificates this would be the email address.
        csr.put_CommonName("mysubdomain.mydomain.com");

        // Country Name (2 letter code)
        csr.put_Country("GB");

        // State or Province Name (full name)
        csr.put_State("Yorks");

        // Locality Name (eg, city)
        csr.put_Locality("York");

        // Organization Name (eg, company)
        csr.put_Company("Internet Widgits Pty Ltd");

        // Organizational Unit Name (eg, secion/division)
        csr.put_CompanyDivision("IT");

        // Email address
        csr.put_EmailAddress("support@mydomain.com");

        // Create the CSR using the private key.
        String pemStr = csr.genCsrPem(privKey);
        if (!csr.get_LastMethodSuccess()) {
            System.out.println(csr.lastErrorText());
            return;
        }

        // Save the private key and CSR to a files.
        privKey.SavePkcs8EncryptedPemFile("password", "qa_output/privKey1.pem");

        CkFileAccess fac = new CkFileAccess();
        fac.WriteEntireTextFile("qa_output/csr1.pem", pemStr, "utf-8", false);

        // Show the CSR.
        System.out.println(pemStr);

        // Sample output:
        // The CSR PEM can be checked here:
        // https://www.networking4all.com/en/support/tools/csr+check/
        // Copy-and-paste the PEM into the online CSR Decoding / CSR Verification form
    }

}
