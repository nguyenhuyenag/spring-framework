package com.runner;

import com.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Component;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final LdapTemplate ldapTemplate;

    public void search(String uid) {
        LdapQuery query = LdapQueryBuilder.query()
                .base("ou=users") // Correct search base
                .where("uid")
                .is(uid);

//        ldapTemplate.search(query, new AttributesMapper<String>() {
//            @Override
//            public String mapFromAttributes(Attributes attributes) throws NamingException {
//                System.out.println(attributes);
//                return "";
//            }
//        });

        User user = ldapTemplate.findOne(query, User.class);
        System.out.println(user);
    }

    public void modify(String username, String password) {
        Name dn = LdapNameBuilder.newInstance()
                .add("ou", "users")
                .add("cn", username)
                .build();
        DirContextOperations context = ldapTemplate.lookupContext(dn);

//        context.setAttributeValues("objectclass", new String[]
//                {"top",
//                        "person",
//                        "organizationalPerson",
//                        "inetOrgPerson"}
//        );
//        context.setAttributeValue("cn", username);
//        context.setAttributeValue("sn", username);
//        context.setAttributeValue("userPassword", digestSHA(password));

        ldapTemplate.modifyAttributes(context);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ldapTemplate.toString());
        search("0964404020");
    }

}
