package football;

public class PlayerDTO {

	private int playerId; // 선수코드
	private String playerName; // 선수이름
	private String teamCode; // 팀코드
	private String teamName; // 팀이름
	private String position; // 포지션
	private int uniformNum; // 등번호
	private String birth; // 생년월일
	private int tall; // 키
	private String playerNation; // 선수국가

	public int getplayerId() {
		return playerId;
	}

	public void setplayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getUniformNum() {
		return uniformNum;
	}

	public void setUniformNum(int uniformNum) {
		this.uniformNum = uniformNum;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getTall() {
		return tall;
	}

	public void setTall(int tall) {
		this.tall = tall;
	}

	public String getPlayerNation() {
		return playerNation;
	}

	public void setPlayerNation(String playerNation) {
		this.playerNation = playerNation;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
