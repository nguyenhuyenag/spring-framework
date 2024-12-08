# Spring Boot Events

- Khi Publisher phát hành một sự kiện thì Listener sẽ lắng nghe và xử lý sự kiện.

- Ví dụ
    
	+ Publisher
    
		applicationEventPublisher.publishEvent(new CustomEvent(this, "message"));

    + Listener
	
		@EventListener
		public void handleCustomEvent(CustomEvent event) {
			System.out.println("Received event: " + event.getMessage());
		}
    