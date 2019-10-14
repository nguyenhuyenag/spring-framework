//package core.entity.embeddable;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
//
//import lombok.RequiredArgsConstructor;
//
///**
// * class phải implement Serializable
// */
//@Embeddable
//@RequiredArgsConstructor
//public class StudentId implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//
//	@Column
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//
//	@Column
//	private Integer code;
//
//	public StudentId(final int code) {
//		this.code = code;
//	}
//
//	@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
//	}
//
//}
