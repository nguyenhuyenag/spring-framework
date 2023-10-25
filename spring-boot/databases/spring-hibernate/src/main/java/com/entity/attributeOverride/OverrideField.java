package com.entity.attributeOverride;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;

/**
 * Thay đổi thuộc tính cột của lớp cha trong lớp con mà ta đang ánh xạ vào DB
 */
@AttributeOverrides({
    @AttributeOverride(name = "street", column = @Column(name = "home_street")),
    @AttributeOverride(name = "city", column = @Column(name = "home_city")),
    @AttributeOverride(name = "zipCode", column = @Column(name = "home_zip_code"))
})
public class OverrideField {

}
