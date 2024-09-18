package com.mapper;

import com.entity.SaoKe;
import com.response.SaoKeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaoKeMapper {

    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy")
    List<SaoKeResponse> toSaoKeResponse(List<SaoKe> listSaoKe);

}
