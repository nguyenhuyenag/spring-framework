package core.entity.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Tourist {

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String name;

	@OneToOne
	@JoinColumn(name = "room_id")
	private Room room;

}
