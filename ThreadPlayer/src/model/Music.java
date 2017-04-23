package model;

public class Music {

	private String name;
	private String path;
	
	public Music(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
	public String getName() {
		return name;
	}
	
	public String toCsv() {
		StringBuilder csv = new StringBuilder();
		
		csv.append(name).append(",");
		csv.append(path);
		
		return csv.toString();
	}
	
}
