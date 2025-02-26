package com.legacy;

import org.apache.commons.lang3.SystemUtils;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;

/*
    Code trên thực hiện các chức năng:

        Tạo cặp khóa RSA (public/private key)
        Tạo thông tin chủ thể cho CSR (Subject DN)
        Tạo CSR request với thông tin và public key
        Ký CSR bằng private key
        Xuất CSR ra định dạng PEM
        Lưu private key ra file riêng
 */
public class CSRExample {

    private static final Path path = Paths.get(SystemUtils.USER_DIR, "src", "main", "resources", "out");

    static {
        // Đăng ký BouncyCastle Provider
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    public static void main(String[] args) {
        try {
            generateCSR();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateCSR() throws Exception {
        // 1. Tạo cặp khóa RSA
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 2. Tạo thông tin chủ thể
        X500NameBuilder x500NameBuilder = new X500NameBuilder(BCStyle.INSTANCE);
        x500NameBuilder.addRDN(BCStyle.CN, "www.example.com");
        x500NameBuilder.addRDN(BCStyle.O, "Example Company");
        x500NameBuilder.addRDN(BCStyle.OU, "IT Department");
        x500NameBuilder.addRDN(BCStyle.L, "Ho Chi Minh");
        x500NameBuilder.addRDN(BCStyle.ST, "Ho Chi Minh");
        x500NameBuilder.addRDN(BCStyle.C, "VN");
        x500NameBuilder.addRDN(BCStyle.E, "admin@example.com");

        X500Name subject = x500NameBuilder.build();

        // 3. Tạo CSR
        PKCS10CertificationRequestBuilder csrBuilder =
                new JcaPKCS10CertificationRequestBuilder(subject, keyPair.getPublic());

        // 4. Ký CSR
        ContentSigner signer = new JcaContentSignerBuilder("SHA256withRSA")
                .setProvider("BC")
                .build(keyPair.getPrivate());

        PKCS10CertificationRequest csr = csrBuilder.build(signer);

        // 5. Lưu CSR ra file PEM
        saveToPemFile(csr.getEncoded(), "CERTIFICATE REQUEST", "csr.pem");

        // 6. Lưu private key ra file PEM
        saveToPemFile(keyPair.getPrivate().getEncoded(), "PRIVATE KEY", "private_key.pem");

        System.out.println("CSR và Private Key đã được tạo thành công!");
    }

    private static void saveToPemFile(byte[] encoded, String type, String filename) throws Exception {
        PemObject pemObject = new PemObject(type, encoded);
        File directory = new File(String.valueOf(path));
        if (!directory.exists()) {
            directory.mkdirs(); // Tạo thư mục nếu nó chưa tồn tại
        }
        FileWriter fileWriter = new FileWriter(path + "/" + filename);
        PemWriter pemWriter = new PemWriter(fileWriter);
        try {
            pemWriter.writeObject(pemObject);
        } finally {
            pemWriter.close();
            fileWriter.close();
        }
    }

}
