package com.mapper;

import com.entity.SaoKe;
import com.response.SaoKeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SaoKeMapper {

    @Mapping(target = "date", expression = "java(formatDate(entity.getDate()))")
    SaoKeResponse toSaoKeResponse(SaoKe entity);

    List<SaoKeResponse> toSaoKeResponse(List<SaoKe> listSaoKe);

    default String formatDate(LocalDate date) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date != null ? date.format(formatters) : null;
    }

}
