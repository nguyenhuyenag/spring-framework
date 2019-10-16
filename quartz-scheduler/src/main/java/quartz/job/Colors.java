
package quartz.job;

public enum Colors {

	RESET("\033[0m"), // RESET

	// Regular colors
	BLACK("\033[0;30m"),	// BLACK
	RED("\033[0;31m"), 		// RED
	GREEN("\033[0;32m"), 	// GREEN
	YELLOW("\033[0;33m"), 	// YELLOW
	BLUE("\033[0;34m"), 	// BLUE
	MAGENTA("\033[0;35m"),	// MAGENTA
	CYAN("\033[0;36m"), 	// CYAN
	WHITE("\033[0;37m"), 	// WHITE

	// Bold
	BOLD_BLACK("\033[1;30m"),
	BOLD_RED("\033[1;31m"),
	BOLD_GREEN("\033[1;32m"),
	BOLD_YELLOW("\033[1;33m"),
	BOLD_BLUE("\033[1;34m"),
	BOLD_MAGENTA("\033[1;35m"),
	BOLD_CYAN("\033[1;36m"),
	BOLD_WHITE("\033[1;37m"),

	// Background
	BACKGROUND_BLACK("\033[40m"),
	BACKGROUND_RED("\033[41m"),
	BACKGROUND_GREEN("\033[42m"),
	BACKGROUND_YELLOW("\033[43m"),
	BACKGROUND_BLUE("\033[44m"),
	BACKGROUND_MAGENTA("\033[45m"),
	BACKGROUND_CYAN("\033[46m"),
	BACKGROUND_WHITE("\033[47m"),

	// High Intensity backgrounds
	BACKGROUND_BRIGHT_BLACK("\033[0;100m"),
	BACKGROUND_BRIGHT_RED("\033[0;101m"),
	BACKGROUND_BRIGHT_GREEN("\033[0;102m"),
	BACKGROUND_BRIGHT_YELLOW("\033[0;103m"),
	BACKGROUND_BRIGHT_BLUE("\033[0;104m"),
	BACKGROUND_BRIGHT_MAGENTA("\033[0;105m"),
	BACKGROUND_BRIGHT_CYAN("\033[0;106m"),
	BACKGROUND_BRIGHT_WHITE("\033[0;107m");

	private String code;

	private Colors(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return this.code;
	}

}
