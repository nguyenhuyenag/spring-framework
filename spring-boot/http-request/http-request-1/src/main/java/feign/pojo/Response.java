package feign.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response {

//	{
//	    "RPLY": {
//	        "ERROR_DESC": "success",
//	        "name": "send_sms_list",
//	        "ERROR": "0"
//	    }
//	}

	@JsonProperty("RPLY")
	private Rply rply;

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Rply {

		@JsonProperty("ERROR_DESC")
		private String desc;

		private String name;

		@JsonProperty("ERROR")
		private String error;
	}

}
