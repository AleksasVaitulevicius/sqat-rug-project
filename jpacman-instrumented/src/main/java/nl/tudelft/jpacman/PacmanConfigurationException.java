package nl.tudelft.jpacman;import coverageApi.Collect; public class PacmanConfigurationException extends RuntimeException {

	/**
	 * A configuration exception with a direct message.
	 * 
	 * @param message The exception message.
	 */
	public PacmanConfigurationException(String message) {super(message);Collect.Hit("PacmanConfigurationException.java","PacmanConfigurationException(String message)");Collect.Hit("PacmanConfigurationException.java","PacmanConfigurationException(String message)", "|project://sqat-analysis/src/sqat/series2/A1b_DynCov.rsc|(4826,34)");}

	/**
	 * A configuration exception with a root cause and additional explanation.
	 * 
	 * @param message The explanation.
	 * @param cause The root cause.
	 */
	public PacmanConfigurationException(String message, Throwable cause) {super(message, cause);Collect.Hit("PacmanConfigurationException.java","PacmanConfigurationException(String message, Throwable cause)");Collect.Hit("PacmanConfigurationException.java","PacmanConfigurationException(String message, Throwable cause)", "|project://sqat-analysis/src/sqat/series2/A1b_DynCov.rsc|(4826,34)");}
}
