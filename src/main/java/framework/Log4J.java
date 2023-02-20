package framework;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4J {

	private static final Logger logger = LogManager.getLogger(Log4J.class);
	static Logger log = Logger.getLogger(Log4J.class);

	public static void main(String[] args) {

		System.out.println("hello world");

		log.debug("This is a Debug Log");
		log.info("This is a Info Log");
		log.error("Error in a Project");

		logger.debug("This is a Debug Log");
		logger.info("This is a Info Log");
		logger.error("Error in a Project");
		logger.fatal("This is a fatal message");

		System.out.println("complete");
	}

}
