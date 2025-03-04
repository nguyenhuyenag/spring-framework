package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheData {

    private String id;
    private int otp;
    private String expired;
    private long expiredTime;

    public CacheData(String id, int otp, long expiredAfterMillis) {
        this.id = id;
        this.otp = otp;
        this.expiredTime = Instant.now().toEpochMilli() + expiredAfterMillis;
        this.expired = formatExpiredTime(this.expiredTime);
    }

    private String formatExpiredTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(new Date(timestamp));
    }

}
