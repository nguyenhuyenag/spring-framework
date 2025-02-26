package com.example;

import com.chilkatsoft.*;
import com.util.Base64Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class BouncedMail {

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

    public static void main(String argv[]) {
        // This example requires the Chilkat API to have been previously unlocked.
        // See Global Unlock Sample for sample code.

        CkBounce bounce = new CkBounce();

        // The mailman object is used for receiving (POP3)
        // and sending (SMTP) email.
        CkMailMan mailman = new CkMailMan();

        // Set the POP3 server's hostname
        mailman.put_MailHost("mail.chilkatsoft.com");

        // Set the POP3 login/password.
        mailman.put_PopUsername("frompostmail@gmail.com");
        mailman.put_PopPassword("pcorcvumvovkbuje");

        // Copy the all email from the user's POP3 mailbox
        // into a bundle object.  The email remains on the server.
        // (There are other methods for deleting email from a POP3 server.)
        CkEmailBundle bundle = mailman.CopyMail();
        if (mailman.get_LastMethodSuccess() == false) {
            System.out.println(mailman.lastErrorText());
            return;
        }

        int i = 0;
        CkEmail email;
        boolean success;
        while (i < bundle.get_MessageCount()) {
            email = bundle.GetEmail(i);

            success = bounce.ExamineEmail(email);
            if (success == false) {
                System.out.println(bounce.lastErrorText());

                return;
            }

            if (bounce.get_BounceType() == 1) {
                // Hard bounce, log the email address
                System.out.println("Hard Bounce: " + bounce.bounceAddress());
            }

            if (bounce.get_BounceType() == 2) {
                // Soft bounce, log the email address
                System.out.println("Soft Bounce: " + bounce.bounceAddress());
            }

            if (bounce.get_BounceType() == 3) {
                // General bounce, no email address available.
                System.out.println("General Bounce: No email address");
            }

            if (bounce.get_BounceType() == 4) {
                // General bounce, log the email address
                System.out.println("General Bounce: " + bounce.bounceAddress());
            }

            if (bounce.get_BounceType() == 5) {
                // Mail blocked, log the email address
                // A bounce occured because the sender was blocked.
                System.out.println("Mail Blocked: " + bounce.bounceAddress());
            }

            if (bounce.get_BounceType() == 6) {
                // Auto-reply, log the email address
                System.out.println("Auto-Reply: " + bounce.bounceAddress());
            }

            if (bounce.get_BounceType() == 7) {
                // Transient (recoverable) Failure, log the email address
                System.out.println("Transient Failure: " + bounce.bounceAddress());
            }

            if (bounce.get_BounceType() == 8) {
                // Subscribe request, log the email address
                System.out.println("Subscribe Request: " + bounce.bounceAddress());
            }

            if (bounce.get_BounceType() == 9) {
                // Unsubscribe Request, log the email address
                System.out.println("Unsubscribe Request: " + bounce.bounceAddress());
            }

            if (bounce.get_BounceType() == 10) {
                // Virus Notification, log the email address
                System.out.println("Virus Notification: " + bounce.bounceAddress());
            }

            if (bounce.get_BounceType() == 11) {
                // Suspected bounce.
                // This should be rare.  It indicates that the Bounce
                // component found strong evidence that this is a bounced
                // email, but couldn't quite recognize everything it
                // needed to be 100% sure.  Feel free to notify
                // support@chilkatsoft.com regarding emails having this
                // bounce type.
                System.out.println("Suspected Bounce!");
            }

            if (bounce.get_BounceType() == 12) {
                // Challenge/Response - Auto-reply message sent by SPAM software
                // where only verified email addresses are accepted.
                System.out.println("Challenge: " + bounce.bounceAddress());
            }

            if (bounce.get_BounceType() == 13) {
                // Address Change Notification Message.
                System.out.println("Address Change: " + bounce.bounceAddress());
            }

            if (bounce.get_BounceType() == 14) {
                // Success DSN indicating that the message was successfully relayed.
                System.out.println("DSN Successful Relay: ");
            }

            i = i + 1;
        }

        success = mailman.Pop3EndSession();
    }

}
