package dev.dto;

// @Data
// @AllArgsConstructor
public class Info {

	String key;
	String value;

	public Info() {
		super();
	}

	public Info(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
