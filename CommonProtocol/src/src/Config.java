package src;

public class Config {
	public static final String bindCommand = "bind";
	public static final String loginCommand = "login";
	public static final String registerCommand = "register";
	public static final String messageCommand = "message";
	public static final String inviteCommand = "invite";
	public static final String gameOverCommand="gameOver";

	// from client to server
	public static final String Clogin = "#login:";
	public static final String CRegister = "#register:";
	public static final String Cmessage = "#message";
	public static final String Cinvite = "#invite:";
	public static final String Caccept = "#accept:";
	public static final String Creject = "#reject:";

	public static final String Cclose = "#close";
	public static final String CsendGlobalMessage = "#sendChat";
	public static final String Cexit = "exit";

	public static final String CrematchAccept = "#rematcaccept";
	public static final String CrematchReject = "#rematchReject";
	public static final String RexitAccept="exitAccept";
	public static final String RglobalMessage = "#recvchatMessage";
	public static final String RloginSucceed = "#LoginSucceed";
	public static final String RregisterSucceed = "#RegisterSucceed";
	public static final String Ruserlist = "#userList";

	public static final String RcloseSocket = "#CloseSocket";
	public static final String Rinvite = "#inviteFrom:";
	public static final String Raccept = "#acceptFrom:";
	public static final String Rreject = "#rejectFrom:";
	public static final String RbusyStatus = "#busy";
	public static final String RclientNotFound = "#clientNotFound";
	public static final String RSimpleMessage = "#simpleMessage";

	public static final String GCbet = "#myBet";
	public static final String GChit = "#iHit";
	public static final String GCstand = "#iStand";

	public static final String GRscore = "#yourScore:";
	public static final String GRHighestScore = "#yourHighestScore:";
	public static final String GRreqBet = "#requestBet";
	public static final String GRwaitForBet = "#waitForBet";
	public static final String GRtheBetIs = "#betIs:";
	public static final String GRnewHand = "#newHand:";
	public static final String GRnewGameNotify = "#newGame";
	public static final String GRwaitForAnotherToStand = "waitForStand";
	public static final String GCtoLobby = "#toLobby";
	public static final String Crematch = "#rematchRequest";
	public static final String clearGameData = "#clearGameData";
	public static final String GRnewRivalCard = "newRivalCard";
	public static final String GRnewPlayerCard="newPlayerCard";
	public static final String GRGameoverStatus = "#gameOverStatus";
	public static final String GRrematchOffer = "#rematchOffer";
	

}
