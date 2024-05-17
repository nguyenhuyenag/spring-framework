package feign.webclient.largefile;

import java.nio.file.Paths;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LargeFileController {

	@GetMapping("large-file")
	ResponseEntity<Resource> get() {
		return ResponseEntity.ok() //
				.body(new FileSystemResource(Paths.get("C:/Users/huyennv/Desktop/new/large.dat")));
	}
}