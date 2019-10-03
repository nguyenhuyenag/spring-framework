package learn.of.component.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import learn.of.component.Outfit;
import lombok.Data;

@Data
@Component
public class Girl {

	// Đánh dấu để Spring inject một đối tượng Outfit vào đây
	@Autowired
	Outfit outfit;

	// Inject thông qua constructor trước
	public Girl() {

	}

	// @Autowired <- có thể đánh dấu trên method
	// Nếu không tìm thấy Constructor thoả mãn, nó sẽ thông qua setter
	public void setOutfit(Outfit outfit) {
		this.outfit = outfit;
	}
	
}
