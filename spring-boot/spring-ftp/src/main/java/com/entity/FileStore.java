package com.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "file_store")
public class FileStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @GeneratedValue(generator = "uuid")
    // @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    // @Column(length = 36)
    private String fileId;

    private String fileName;

    @Lob
    private String fileContent;

    @CreationTimestamp
    private Date created;

    public FileStore() {
        this.fileId = UUID.randomUUID().toString();
    }

}
