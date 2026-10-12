package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "a_coquanbhxh")
public class CoQuanBhxh implements Serializable {

    private static final long serialVersionUID = 1L;

    /** id int auto_increment not null */
    @Id
    @Field(type = FieldType.Integer, name = "id")
    private Integer id;

    /** tencoquan varchar(200) */
    @Field(type = FieldType.Text, name = "tencoquan")
    private String tenCoQuan;

    /** macoquan varchar(10) - mã cqbhxh 5 số ibhxh */
    @Field(type = FieldType.Keyword, name = "macoquan")
    private String maCoQuan;

    /** machabhxh varchar(10) - mã cha của mã cqbhxh ivan_ibhxh */
    @Field(type = FieldType.Keyword, name = "machabhxh")
    private String maChaBhxh;

    /** macqbhxh varchar(10) - mã cqbhxh của ivan_bhxh */
    @Field(type = FieldType.Keyword, name = "macqbhxh")
    private String maCqBhxh;

    /** matinh varchar(10) */
    @Field(type = FieldType.Keyword, name = "matinh")
    private String maTinh;

    /** mathamchieu varchar(10) */
    @Field(type = FieldType.Keyword, name = "mathamchieu")
    private String maThamChieu;

    /** maloai varchar(10) */
    @Field(type = FieldType.Keyword, name = "maloai")
    private String maLoai;

    /** maquan varchar(5) - Mã phường/xã sau 1/7/25 */
    @Field(type = FieldType.Keyword, name = "maquan")
    private String maQuan;

    /** maphuongxa varchar(10) - Mã phường/xã sau 1/7/25 */
    @Field(type = FieldType.Keyword, name = "maphuongxa")
    private String maPhuongXa;

    /** tenphuongxa varchar(255) */
    @Field(type = FieldType.Text, name = "tenphuongxa")
    private String tenPhuongXa;

    /** tentinhtp varchar(200) */
    @Field(type = FieldType.Text, name = "tentinhtp")
    private String tenTinhTp;

    /** kkqm int default 0 */
    @Builder.Default
    @Field(type = FieldType.Integer, name = "kkqm")
    private Integer kkqm = 0;

    /** DiaChiAnChi varchar(255) */
    @Field(type = FieldType.Text, name = "diachianchi")
    private String diaChiAnChi;

    /** matinhbhxh varchar(10) */
    @Field(type = FieldType.Keyword, name = "matinhbhxh")
    private String maTinhBhxh;
}
