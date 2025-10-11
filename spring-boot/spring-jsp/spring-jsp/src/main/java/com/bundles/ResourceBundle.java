package com.bundles;

import java.util.ListResourceBundle;

public class ResourceBundle extends ListResourceBundle {
	
	protected Object[][] getContents() {
		return new Object[][] { //
			{ "count.k1", "Hello {0}" }, 		// {0} wil be replaced by parameter value.
			{ "count.k2", "How are you" }, 
			{ "count.k3", "{0}: I am Good" }, 	// {0} will be replaced by parameter
			{ "count.k4", "Bye" }
		};
	}
	
}
