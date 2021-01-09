package generalClasses;


/**
 * Command used by the client and server to communicate.
 *	Those starting with C are command whose originator is the client, S is for server. If none, then the command is used by both.
 */
public class CommunicationCommands {
	//Constant string related to the "Play" use case
	public static final String STARTING="#starting";
	public static final String LOGIN_CHECK="#login";
	public static final String S_CORRECT="correct";
	public static final String S_INCORRECT="incorrect";
	public static final String SIGN_IN_CHECK="#signin";
	public static final String C_GET_USER="#getCurrentUser";
	
	//Constant string related to the "Play" use case
	public static final String GAME="#game";
	public static final String C_CREATE_GAME=GAME+" #createGame";
	public static final String C_JOIN_PRIVATE=GAME+" #joinPrivateGame";
	public static final String C_JOIN_PUBLIC=GAME+" #joinPublicGame";
	public static final String C_QUIT_GAME=GAME+" #quitGame";
	public final static String C_START_GAME=GAME+" #startGame";
	public static final String S_NEW_PLAYER=GAME+" #newPlayer";
	public static final String S_PLAYER_QUIT=GAME+" #playerQuit";
	public static final String S_GAME_START=GAME+" #gameStarted";
	public static final String S_GAME_JOINED=GAME+" #gameJoined";
	public static final String S_GAME_NOT_FOUND=GAME+" #gameNotFound";
	public static final String S_GAME_ALREADY_STARTED=GAME+" #gameAlreadyStarted";
	public static final String S_GAME_FULL=GAME+" #gameFull";
	public static final String S_NEW_HOST=GAME+" #newHost";
}
