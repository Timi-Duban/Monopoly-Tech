package generalClasses;

public enum SquareName {
	GO("Go"),OLD_KENT("Old Kent Road"),COMMUNITY1("Community"),WHITE_CHAPEL("Whitechapel Road"),
	GO2("Go"),OLD_KENT2("Old Kent Road"),COMMUNITY12("Community"),WHITE_CHAPEL2("Whitechapel Road"),
	GO3("Go"),OLD_KENT3("Old Kent Road"),COMMUNITY13("Community"),WHITE_CHAPEL3("Whitechapel Road"),
	GO4("Go"),OLD_KENT4("Old Kent Road"),COMMUNITY14("Community"),WHITE_CHAPEL4("Whitechapel Road"),
	GO5("Go"),OLD_KENT5("Old Kent Road"),COMMUNITY15("Community"),WHITE_CHAPEL5("Whitechapel Road"),
	GO6("Go"),OLD_KENT6("Old Kent Road"),COMMUNITY16("Community"),WHITE_CHAPEL6("Whitechapel Road"),
	GO7("Go"),OLD_KENT7("Old Kent Road"),COMMUNITY17("Community"),WHITE_CHAPE7L("Whitechapel Road"),
	GO8("Go"),OLD_KENT8("Old Kent Road"),COMMUNITY18("Community"),WHITE_CHAPEL8("Whitechapel Road"),
	GO9("Go"),OLD_KENT9("Old Kent Road"),COMMUNITY19("Community"),WHITE_CHAPEL9("Whitechapel Road"),
	GO10("Go"),OLD_KENT10("Old Kent Road"),COMMUNITY110("Community"),WHITE_CHAPEL10("Whitechapel Road"),
	GO444("Go"),OLD_KEN4T("Old Kent Road"),COMMUNITY41("Community"),WHITE_CHA4PEL("Whitechapel Road");
	private String value;
	
	SquareName(String val){
		this.value=val;
	}
	public String getValue() {
		return	this.value;
	}
}
