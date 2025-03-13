package com.service;

import lombok.extern.slf4j.Slf4j;
import org.forgerock.opendj.ldap.*;
import org.forgerock.opendj.ldif.ConnectionEntryReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenDjService {

    @Value("${spring.ldap.base}")
    private String base;

    @Value("${spring.ldap.username}")
    private String username;

    @Value("${spring.ldap.password}")
    private String password;

    public void searchTokenZNS() {
        String uid = "5569998187330363604";
        try (LDAPConnectionFactory factory = new LDAPConnectionFactory("192.168.0.98", 389)) {
            Connection connection = factory.getConnection();
            connection.bind(username, password.toCharArray());
            
            String baseDN = "ou=tokenzns," + base;
            final String filter = "(uid=" + uid + ")";
            final ConnectionEntryReader reader = connection.search(baseDN, SearchScope.WHOLE_SUBTREE, filter, "*");
            while (reader.hasNext()) {
                if (reader.isEntry()) {
                    System.out.println(reader.readEntry());
                    return;
                }
            }
        } catch (ErrorResultIOException | SearchResultReferenceIOException | ErrorResultException e) {
            log.error("ErrorResultException: {}", e.getMessage(), e);
        }
    }

}
