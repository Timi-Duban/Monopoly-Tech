package server.BL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import generalClasses.User;

import com.lloseng.ocsf.server.ConnectionToClient;

import generalClasses.CommunicationCommands;


public class GameServer {
	public static final int MAX_NUMBER_PLAYER=8;
	
	private List<ConnectionToClient> players=new ArrayList<ConnectionToClient>();
	private boolean isStarted=false;
	private int code;
	
	public GameServer(int code) {
		this.code=code;
	}
	
	/**
	 * add the client to the game
	 * @param client
	 */
	public void addPlayer(ConnectionToClient client) throws IOException {
		players.add(client);
		client.sendToClient(CommunicationCommands.S_GAME_JOINED+" "+getCode()+retrieveListPlayers());
		sendToAllPlayers(CommunicationCommands.S_NEW_PLAYER,client);
	}
	
	/**
	 * remove the client from the game
	 * @param client
	 * @return true if the last player has been removed, else false.
	 */
	public boolean removePlayer(ConnectionToClient client) throws IOException {
		boolean isHost=players.indexOf(client)==0;
		players.remove(client);
		if(players.size()!=0) {
			sendToAllPlayers(CommunicationCommands.S_PLAYER_QUIT,null);
			if(isHost) {
				newHost();
			}
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * To be called when after ServerFacade created a game, or after the current host has been removed from the game.
	 * Send the command that allows to start a game on client side to player who joined first.
	 * @throws IOException
	 */
	public void newHost() throws IOException {
		players.get(0).sendToClient(CommunicationCommands.S_NEW_HOST);
	}
	
	public void startGame() throws IOException {
		this.isStarted=true;
		sendToAllPlayers(CommunicationCommands.S_GAME_START,null);

	}
	
	/**
	 * 
	 * @return true if game is full, else false.
	 */
	public boolean isFull() {
		return this.players.size()==MAX_NUMBER_PLAYER;
	}
	
	/**
	 * 
	 * @return true if the game has started, else false;
	 */
	public boolean isStarted() {
		return this.isStarted;
	}
	
	/**
	 * Send a message to every player of this game, except the one in parameter.
	 * If none must be excluded, then exclusionClient must be null
	 * @param message
	 * @param exclusionClient : the client to exclude
	 * @throws IOException
	 */
	public void sendToAllPlayers(String message, ConnectionToClient exclusionClient) throws IOException {
		for(ConnectionToClient client : players) {
			if(!(client.equals(exclusionClient))) {
				client.sendToClient(message);
			}
		}
	}
	
	public int getCode() {
		return this.code;
	}
	
	/**
	 * 
	 * @return the list of the pseudonym of the players of this game. The separator used is a white space.
	 */
	private String retrieveListPlayers() {
		String listPlayer="";
		String pseudo;
		for(int i=0;i<this.players.size();i++) {
			pseudo=((User)(players.get(i).getInfo("user"))).getPseudo();
			listPlayer+=" "+pseudo;
		}
		
		return listPlayer;
	}
	
	
	
	
}
