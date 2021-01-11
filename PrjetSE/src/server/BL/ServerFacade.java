package server.BL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import com.lloseng.ocsf.server.ConnectionToClient;
import com.lloseng.ocsf.server.ObservableOriginatorServer;
import com.lloseng.ocsf.server.OriginatorMessage;

import generalClasses.User;
import generalClasses.CommunicationCommands;
import generalClasses.Item;
import generalClasses.PasswordUtils;
import server.PL.AbstractFactoryDAO;
import server.PL.DAO;
import server.PL.ItemMySQLDAO;
import server.UI.ServerController;

/**
 * 
 */
@SuppressWarnings("deprecation")
public class ServerFacade implements Observer {
	
	final public static int DEFAULT_PORT = 5555;
	
	private ObservableOriginatorServer serverCL;
	private ServerController controller;
	
	private LinkedList<ConnectionToClient> waitingPlayers=new LinkedList<ConnectionToClient>();
	private Map<Integer,GameServer> listGames=new HashMap<Integer,GameServer>();
	
	
   
	/**
     * Default constructor
     */
    public ServerFacade() {
        this.serverCL=new ObservableOriginatorServer(DEFAULT_PORT);
        this.serverCL.addObserver(this);
        this.controller=ServerController.getInstance();
    }



   /**
    * @param message : the message received from the client
    * @param originator : the client
    */
    private void handleMessageFromClient(String message,ConnectionToClient originator) throws IOException {
        String[] mes=message.split(" ");
        switch(mes[0]) {
        case CommunicationCommands.GAME:
        	handleGameCommand(message, originator);
        	break;
        case CommunicationCommands.STARTING:
        	handleStartingCommand(message,originator);
        	break;
        case CommunicationCommands.SHOP:
        	handleShopCommand(message,originator);
        	break;
        
        
        
        
       
        case CommunicationCommands.C_DISCONNECT:
        	originator.close();
        	break;
        }
        		
    }

    /**
     * Handle command related to signin/login
     * @param mesReceived
     * @param originator
     * @throws IOException
     */
    private void handleStartingCommand(String mesReceived, ConnectionToClient originator) throws IOException {
    String[] mes=mesReceived.split(" ");
    String command=mes[1];
    switch(command) {
    case CommunicationCommands.LOGIN_CHECK:
    	//test if the id (mes[1] and mes [2]) are correct. Store User (setInfo("user",User))
    	if(mes.length>=3) {
    		try {
    			AbstractFactoryDAO.openConnectionDatabase();
    			DAO<User> userDAO = AbstractFactoryDAO.getInstance().createUserDAO();
    			User user=userDAO.findByMail(mes[2]);
    			AbstractFactoryDAO.closeConnectionDatabase();
    			if(user!=null && PasswordUtils.verifyUserPassword(mes[3], user.getPassword(), user.getSalt())) {
    				originator.setInfo("user", user);
    				originator.sendToClient(CommunicationCommands.STARTING+" "+CommunicationCommands.LOGIN_CHECK+" "+CommunicationCommands.S_CORRECT);
    			}else {
    				originator.sendToClient(CommunicationCommands.STARTING+" "+CommunicationCommands.LOGIN_CHECK+" "+CommunicationCommands.S_INCORRECT);
    				originator.close();
    			}
    		}catch(SQLException e) {
    			originator.sendToClient("#dbUnaivailable");
    			originator.close();
    		}
    	}else {
    		originator.sendToClient(CommunicationCommands.STARTING+" "+CommunicationCommands.LOGIN_CHECK+" "+CommunicationCommands.S_INCORRECT);
    		originator.close();
    	}
    	break;
    case CommunicationCommands.SIGN_IN_CHECK:
    	if(mes.length>=6) {
    		try {
    			AbstractFactoryDAO.openConnectionDatabase();
    			DAO<User> userDAO = AbstractFactoryDAO.getInstance().createUserDAO();
    			User user=userDAO.findByMail(mes[2]);
    			if(user!=null) {
    				originator.sendToClient(CommunicationCommands.STARTING+" "+CommunicationCommands.SIGN_IN_CHECK+" "+CommunicationCommands.S_INCORRECT);
    				originator.close();
				}else{
					String email=mes[2];
					String pseudo=mes[3];
					String password=mes[4];
					String salt=mes[5];
					user=new User(pseudo,email,password,salt);
					userDAO.create(user);
					user=userDAO.findByMail(email);
					originator.setInfo("user", user);
					originator.sendToClient(CommunicationCommands.STARTING+" "+CommunicationCommands.SIGN_IN_CHECK+" "+CommunicationCommands.S_CORRECT);
				}
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    		finally {
    			try{
    				AbstractFactoryDAO.closeConnectionDatabase();
    			}catch(SQLException e) {}
    		}
    	}else {
    		originator.sendToClient(CommunicationCommands.STARTING+" "+CommunicationCommands.SIGN_IN_CHECK+" "+CommunicationCommands.S_INCORRECT);
    		originator.close();
    	}
    	break;
    case CommunicationCommands.C_GET_USER:
    	originator.sendToClient((User)(originator.getInfo("user")));
    	break;
    }
    }
    
    /**
     * Handle command related to "Play" use case
     * @param mesReceived
     * @param client
     */
    private void handleGameCommand(String mesReceived,ConnectionToClient client) throws IOException {
        String[] mes=mesReceived.split(" ");
        String command=mes[1];
        String code;
        GameServer game;
        switch(command) {
        case CommunicationCommands.C_JOIN_PRIVATE:
        	code=mes[2];
        	game=listGames.get(Integer.getInteger(code));
        	if(game==null) {
        		client.sendToClient(CommunicationCommands.GAME+" "+CommunicationCommands.S_GAME_NOT_FOUND);
        	}else {
        		if(game.isFull()) {
        			client.sendToClient(CommunicationCommands.GAME+" "+CommunicationCommands.S_GAME_FULL);
        		}else if(game.isStarted()) {
        			client.sendToClient(CommunicationCommands.GAME+" "+CommunicationCommands.S_GAME_ALREADY_STARTED);
        		}else {
        			game.addPlayer(client);
        			client.setInfo("game", game);
        		}
        	}
        	break;
        case CommunicationCommands.C_JOIN_PUBLIC:
        	addWaitingPlayer(client);
        	break;
        case CommunicationCommands.C_CREATE_GAME:
        	game=createGame();
        	game.addPlayer(client);
        	game.newHost();
        	client.setInfo("game", game);
        	break;
        case CommunicationCommands.C_QUIT_GAME:
        	
        	game=(GameServer)client.getInfo("game");
        	if(game!=null && game.removePlayer(client)) {
        		this.listGames.remove(game.getCode());
        	}
        	break;
        case CommunicationCommands.C_START_GAME:
        	game=(GameServer)client.getInfo("game");
        	game.startGame();
        	break;
        }
    }

    /**
     * Add a player to the list of player waiting for a public game, and create a new game and start it
     * if there is enough players. If so, remove the players from the list.
     * @param client
     */
    private void addWaitingPlayer(ConnectionToClient client) throws IOException {
    	this.waitingPlayers.add(client);
    	if(this.waitingPlayers.size()==GameServer.MAX_NUMBER_PLAYER) {
    		GameServer game=createGame();
    		ConnectionToClient player;
    		for(int i=0;i<8;i++) {
    			player=this.waitingPlayers.removeFirst();
    			game.addPlayer(player);
    			player.setInfo("game", game);
    		}
    		game.startGame();
    	}
    }
    
    /**
     * Create a new game whose code is randomly generated. Moreover, add the game to the list of games.
     * @return the game newly created.
     */
    private GameServer createGame() {
		Random random=new Random();
		int code=random.nextInt();
		while(this.listGames.containsKey(code)) {
			code=random.nextInt();
		}
		GameServer game=new GameServer(code);
		this.listGames.put(code,game);
		return new GameServer(code);
    }
    
  
    private void handleShopCommand(String mesReceived, ConnectionToClient client) throws IOException {
    	String[] mes=mesReceived.split(" ");
    	String command=mes[1];
    	switch(command) {
    	case CommunicationCommands.C_GETITEMS:
    		try {
    			AbstractFactoryDAO.openConnectionDatabase();
    			// Proper way to do: DAO<Item> itemDAO = AbstractFactoryDAO.getInstance().createItemDAO();
    			ItemMySQLDAO itemDAO = (ItemMySQLDAO) AbstractFactoryDAO.getInstance().createItemDAO();
    			ArrayList<Item> listItems = itemDAO.findAllItems(Integer.getInteger(mes[2]));
    			AbstractFactoryDAO.closeConnectionDatabase();
    			client.sendToClient(listItems);
    		}catch(SQLException e) {
    			client.sendToClient("#dbUnaivailable");
    			client.close();
    		}
    		break;
    	case CommunicationCommands.C_BUY:
    		break;
    	}
    }
    	
    /**
     * @return
     */
    public User[] findAllUsers() {
        // TODO implement here
        return null;
    }

    /**
     * @param message
     */
    public void sendToAllUsers(String message) {
        // TODO implement here
    }



	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof OriginatorMessage) {
			OriginatorMessage mesStruct=(OriginatorMessage) arg;
			ConnectionToClient originator=mesStruct.getOriginator();
			String message=(String)mesStruct.getMessage();
			System.out.println(message);
			
			if(message.equals(ObservableOriginatorServer.SERVER_STARTED)) {
				
			}else if(message.equals(ObservableOriginatorServer.SERVER_STOPPED)) {
				
			}else if(message.equals(ObservableOriginatorServer.SERVER_CLOSED)) {
				
			}else if(message.equals(ObservableOriginatorServer.CLIENT_CONNECTED)) {
				
			}else if(message.contains(ObservableOriginatorServer.CLIENT_DISCONNECTED)) {
				
			}else if(message.contains(ObservableOriginatorServer.CLIENT_EXCEPTION)) {
				 
			}else if(message.contains(ObservableOriginatorServer.LISTENING_EXCEPTION)) {
				
			}else{
				try {
					handleMessageFromClient(message,originator);
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		  }
		
	}
	
	/**
	 * Start the server
	 * @throws IOException
	 */
	public void startServer() throws IOException {
		this.serverCL.listen();
	}

}