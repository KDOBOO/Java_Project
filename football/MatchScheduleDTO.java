package football;

public class MatchScheduleDTO {
	
	private int matchNumber;
	private String matchDate;
	private String homeName;
	private String awayName;
	private int homeScore;
	private int awayScore;
	private String mainReferee;
	private int points;
	
	
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	private String subReferee;
	private String subReferee2;
	private String stadium;
	
	private int homenum;
	private int awaynum;
	
	
	public int getHomenum() {
		return homenum;
	}
	public void setHomenum(int homenum) {
		this.homenum = homenum;
	}
	public int getAwaynum() {
		return awaynum;
	}
	public void setAwaynum(int awaynum) {
		this.awaynum = awaynum;
	}
	public String getStadium() {
		return stadium;
	}
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}
	

	public int getMatchNumber() {
		return matchNumber;
	}
	public void setMatchNumber(int matchNumber) {
		this.matchNumber = matchNumber;
	}
	public String getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	public String getHomeName() {
		return homeName;
	}
	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}
	public String getAwayName() {
		return awayName;
	}
	public void setAwayName(String awayName) {
		this.awayName = awayName;
	}
	public int getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}
	public int getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}
	public String getMainReferee() {
		return mainReferee;
	}
	public void setMainReferee(String mainReferee) {
		this.mainReferee = mainReferee;
	}
	public String getSubReferee() {
		return subReferee;
	}
	public void setSubReferee(String subReferee) {
		this.subReferee = subReferee;
	}
	public String getSubReferee2() {
		return subReferee2;
	}
	public void setSubReferee2(String subReferee2) {
		this.subReferee2 = subReferee2;
	}

}
