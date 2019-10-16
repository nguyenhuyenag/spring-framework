package core.entity.manytoone.ecollection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/*-
  	> Insert đối tượng employee với 1 danh sách position
  	
  	> Khi thực hiện insert đối tượng entity vào database nó sẽ tự động insert
  	  bản ghi tương ứng với 2 position trong đối tượng empl vào table empl_position

	Empl entity = new Empl();
 	entity.setName("Java");
  
  	List<String> positions = new ArrayList<>();
  	positions.add("Developer");
  	positions.add("Tester");
  	entity.setPositions(positions);
  
  	empl.save(entity);
*/
@Data
@Entity
@Table(name = "empl_position")
public class EmplPosition {

	@Id
	@Column(name = "empl_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer emplId;

	@Column(name = "position")
	private String position;

}
