package com.model;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Id;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Entry(base = "ou=users,dc=ts24id,dc=com,dc=vn", objectClasses = {"users", "top"})
public class User {

    @Id
    private Name dn;

    @Attribute(name = "uid")
    private String uid;

    @Attribute(name = "fullname")
    private String fullName;

    @Attribute(name = "emailAddress")
    private String emailAddress;

    // Getters and Setters
}
