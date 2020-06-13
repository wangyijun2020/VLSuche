package main;

// class
public class Vorlesung {
	// className
	private String vorlesung;
	// teacher name
	private String profName;
	// period
	private String semester;

	public Vorlesung() {
		super();
	}

	public Vorlesung(String vorlesung, String profName, String semester) {
		super();
		this.vorlesung = vorlesung;
		this.profName = profName;
		this.semester = semester;
	}

	public String getVorlesung() {
		return vorlesung;
	}

	public void setVorlesung(String vorlesung) {
		this.vorlesung = vorlesung;
	}

	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String info() {
		return "vorlesung[" + this.getVorlesung() + "],profName[" + this.getProfName() + "],semester["
				+ this.getSemester() + "]";
	}

	@Override
	public String toString() {
		return "Vorlesung [vorlesung=" + vorlesung + ", profName=" + profName + ", semester=" + semester + "]";
	}
	
	

}
