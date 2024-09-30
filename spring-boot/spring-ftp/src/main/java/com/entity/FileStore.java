package com.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "file_store")
public class FileStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileId;
    private String fileName;
    private String fileSize;

    @Lob
    private byte[] fileByte; // Lưu file trực tiếp vào DB

    @Lob
    private String fileBase64; // File dạng Base64

    @CreationTimestamp
    private Date created;

    public FileStore() {
        this.fileId = UUID.randomUUID().toString();
    }

}
