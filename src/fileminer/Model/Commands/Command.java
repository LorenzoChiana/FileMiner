package fileminer.Model.Commands;

/**
 * @author Daniele
 *
 */
public class Command {
	
    private String name;
	private String desc;
	
	
	/**
	 * @param name nome comando
	 * @param desc descrizione comando
	 */
	public Command(final String name, final String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	/**
     * 
     * @return command name
     */
	public String getName() {
		return this.name;
	}
	
	/**
     * 
     * @return command description
     */
	public String getDesc() {
		return this.desc;
	}
	
	
}
