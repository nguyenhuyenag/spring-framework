package core.entity.manytoone.ecollection;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

@Data
@Entity
@Table(name = "empl")
public class Empl {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@ElementCollection(fetch = FetchType.EAGER)		// Một đối tượng empl chứa 1 tập các position
	@Column(name = "position") 						// Cột position trong bảng empl_position
	@JoinTable(
		name = "empl_position",						// Bảng chứa collection
		joinColumns = @JoinColumn(name = "empl_id")	// Cột thực hiện mapping tới empl
	)
	private List<String> listPositions;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
