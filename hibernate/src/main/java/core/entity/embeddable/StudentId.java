package core.entity.embeddable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

// @EqualsAndHashCode
// @Getter
// @Data
@Embeddable
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private Integer id;

	@Column
	private Integer code;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
