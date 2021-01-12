package generalClasses;


/**
 * Command used by the client and server to communicate.
 *	Those starting with C are command whose originator is the client, S is for server. If none, then the command is used by both.
 */
public class CommunicationCommands {
	public final static String C_DISCONNECT="#closeConnection";
	//Constant string related to the "Login" and "Sign in" use case
	public static final String STARTING="#starting";
	public static final String LOGIN_CHECK="#login";
	public static final String S_CORRECT="correct";
	public static final String S_INCORRECT="incorrect";
	public static final String SIGN_IN_CHECK="#signin";
	public static final String C_GET_USER="#getCurrentUser";
	public static final String EDIT_CHECK="#Edit";
	
	//Constant string related to the "Play" use case
	public static final String GAME="#game";
	public static final String C_CREATE_GAME="#createGame";
	public static final String C_JOIN_PRIVATE="#joinPrivateGame";
	public static final String C_JOIN_PUBLIC="#joinPublicGame";
	public static final String C_QUIT_GAME="#quitGame";
	public final static String C_START_GAME="#startGame";
	public static final String S_NEW_PLAYER="#newPlayer";
	public static final String S_PLAYER_QUIT="#playerQuit";
	public static final String S_GAME_START= "#gameStarted";
	public static final String S_GAME_JOINED="#gameJoined";
	public static final String S_GAME_NOT_FOUND= "#gameNotFound";
	public static final String S_GAME_ALREADY_STARTED="#gameAlreadyStarted";
	public static final String S_GAME_FULL="#gameFull";
	public static final String S_NEW_HOST="#newHost";
	public static final String S_IS_WAITING="#isWaiting";
	
	//Constant string related to the "Shop" use case
	public static final String SHOP="#shop";
	public static final String C_GETITEMS="#getItems";
	public static final String BUY="#buyItem";
	
	//Constant string related to the "Achievement" use case
		public static final String ACHIEVEMENT="#achievement";
}
