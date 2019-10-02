package learn.of.unit1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Girl {

	/**
	 * @Autowired Spring Boot sẽ tự inject (tiêm) một instance của Outfit vào thuộc
	 *            tính này khi khởi tạo Girl
	 */
	@Autowired
	Outfit outfit;

	public Girl(Outfit outfit) {
		this.outfit = outfit;
	}

	public Outfit getOutfit() {
		return outfit;
	}

	public void setOutfit(Outfit outfit) {
		this.outfit = outfit;
	}

}
