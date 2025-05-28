package dev.boot.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Connector {

	private String url;

	public abstract void connect();

}
