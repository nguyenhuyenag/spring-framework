package com.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.forgerock.opendj.ldap.*;
import org.forgerock.opendj.ldif.ConnectionEntryReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenDjService {

    private final LdapTemplate ldapTemplate;

    @Value("${spring.ldap.username}")
    private String username;

    @Value("${spring.ldap.password}")
    private String password;

    @Value("${spring.ldap.base}")
    private String base;

    private final LDAPConnectionFactory factory = new LDAPConnectionFactory("192.168.0.98", 389);

    @PreDestroy
    public void destroy() {
        factory.close();
    }

    public void searchTokenZNS() throws ErrorResultException {
        // Open connection
        try (Connection connection = factory.getConnection()) {
            connection.bind(username, password.toCharArray());
            // Search
            String uid = "ABC11@abcD.com";
            String baseDN = "ou=users," + base;
            final String filter = "(uid=" + uid + ")";
            final ConnectionEntryReader reader = connection.search(baseDN, SearchScope.WHOLE_SUBTREE, filter, "*");
            while (reader.hasNext()) {
                if (reader.isEntry()) {
                    System.out.println("(1)");
                    System.out.println(reader.readEntry());
                    // return;
                }
            }
        } catch (ErrorResultIOException | SearchResultReferenceIOException e) {
            log.error("ErrorResultException: {}", e.getMessage(), e);
        }
    }

}
