package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.util.KafkaUtils;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	/* WAR */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	// @Autowired
	// private Ipsum ipsum = new Ipsum();
	  
//	  public MyClass() {
//	    this.loremIpsum = new LoremIpsum();
//	  }

//	  public MyData createDummyData() {
//	    MyData data = new MyData();
//	    
//	    // returns 50 words of lorem ipsum text
//	    data.setText( loremIpsum.getWords( 50 ) );
//
//	    // returns 150 words of lorem ipsum text starting with
//	    // the third word (text will not begin with "Lorem ipsum")
//	    data.setAnotherText( loremIpsum.getWords( 150, 2 ) );
//	    
//	    // returns two paragraphs of lorem ipsum
//	    data.setYetAnotherText( loremIpsum.getParagraphs( 2 ) );
//	    
//	    return data;
//	  }

	@Override
	public void run(String... args) throws Exception {
		KafkaUtils.showTopicsInfor();
		// System.out.println(loremIpsum.getWords( 150, 2 ));
	}

}
