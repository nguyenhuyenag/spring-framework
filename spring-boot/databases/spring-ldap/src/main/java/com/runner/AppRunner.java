package com.runner;

import com.model.User;
import com.service.OpenDjService;
import lombok.RequiredArgsConstructor;
import org.forgerock.opendj.ldap.*;
import org.forgerock.opendj.ldif.ConnectionEntryReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Component;

import javax.naming.Name;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final LdapTemplate ldapTemplate;

    @Value("${spring.ldap.base}")
    private String base;

    @Value("${spring.ldap.username}")
    private String username;

    @Value("${spring.ldap.password}")
    private String password;

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

//    public void searchTokenZNS() throws ErrorResultException {
//        String uid = "5569998187330363604";
//        try (LDAPConnectionFactory factory = new LDAPConnectionFactory("192.168.0.98", 389)) {
//            Connection connection = factory.getConnection();
//            connection.bind(username, password.toCharArray());
//            String baseDN = "ou=tokenzns," + base;
//            final String filter = "(uid=" + uid + ")";
//            final ConnectionEntryReader reader = connection.search(baseDN, SearchScope.WHOLE_SUBTREE, filter, "*");
//            while (reader.hasNext()) {
//                if (reader.isEntry()) {
//                    Entry entry = reader.readEntry();
//                    System.out.println("entry = " + entry);
//                    return;
//                }
//            }
//        } catch (ErrorResultIOException | SearchResultReferenceIOException e) {
//            e.printStackTrace();
//        }
//    }

    private final OpenDjService openDjService;

    @Override
    public void run(String... args) throws Exception {
        // System.out.println(ldapTemplate.toString());
        openDjService.searchTokenZNS();
    }

}
