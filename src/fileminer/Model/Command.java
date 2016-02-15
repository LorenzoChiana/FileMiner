package fileminer.Model;

public class Command {
	
	String name;
	String desc;
	
	public Command(String name, String desc){
		this.name=name;
		this.desc=desc;
	}
	
	/**
     * 
     * @return command name
     */
	public String getName(){
		return this.name;
	}
	
	/**
     * 
     * @return command description
     */
	public String getDesc(){
		return this.desc;
	}
	
	
}
