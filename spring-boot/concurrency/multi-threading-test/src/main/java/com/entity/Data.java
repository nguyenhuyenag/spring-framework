package com.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Data {

    private String id;
    private long time;
    private boolean isDone;

}
