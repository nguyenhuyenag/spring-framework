package com.entity;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_history")
public class History {

    @Id
    @Column(name = "token_id")
    private String tokenId;

    private String username;

    @Column(name = "login_at")
    private LocalDateTime loginAt;

    @Column(name = "expired_time")
    private long expiredTime;

}
