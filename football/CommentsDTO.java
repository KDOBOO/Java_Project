package football;

public class CommentsDTO {
	private String commentNick;
	private String commentCon;
	private String commentDate;
	private int commentNumber;

	private int playerId;
	private String PlayerName;
	private String TeamCode;
	private String TeamName;
	private int matchNumber;
	private String matchDate;
	private String stadiumName;

	public String getCommentNick() {
		return commentNick;
	}

	public void setCommentNick(String commentsNick) {
		this.commentNick = commentsNick;
	}

	public String getCommentCon() {
		return commentCon;
	}

	public void setCommentCon(String commentCon) {
		this.commentCon = commentCon;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getTeamCode() {
		return TeamCode;
	}

	public void setTeamCode(String teamCode) {
		this.TeamCode = teamCode;
	}

	public int getMatchNumber() {
		return matchNumber;
	}

	public void setMatchNumber(int matchNumber) {
		this.matchNumber = matchNumber;
	}

	public int getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}

	public String getPlayerName() {
		return PlayerName;
	}

	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}

	public String getTeamName() {
		return TeamName;
	}

	public void setTeamName(String teamName) {
		this.TeamName = teamName;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	public String getStadiumName() {
		return stadiumName;
	}

	public void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
	}

}