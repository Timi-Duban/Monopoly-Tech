package client.UI;

import generalClasses.SquareName;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameView {
	public static final int SIZE=11;
	public static final int SQUARE_SIZE=70;
	public static final int NB_SQUARE=40;
	private GridPane grid = new GridPane();
	private Label[] owners=new Label[NB_SQUARE-1];
	private Button rollDice;
	
	private Stage tradeOffer;
	private VBox boxTrade;
	private Label tradeMessage;
	private HBox buttons=new HBox(10);
	private Button accept;
	private Button refuse;
	private Scene offer;
	
	private Text actionText;
	
	Scene scene;
	GameController controller;
	
	public GameView(Dispatcher dispatcher) {
		SquareName val[]=SquareName.values();
		int j=0;
		int row=SIZE-1;
	    int col;
	    for (col = SIZE-1; col >=0; col--) {
	    	grid.getColumnConstraints().add(new ColumnConstraints(SQUARE_SIZE));
	    	
	    	VBox p=new VBox();
	    	Label name=new Label(val[j].getValue());
	    	owners[j]=new Label();
	    	p.getChildren().add(name);
	    	p.getChildren().add(owners[j]);
	    	grid.add(p,col ,row);
	    	j++;  
	            
	    }
	    col=0;
	    for (row = SIZE-2; row >=0; row--) {
        	grid.getRowConstraints().add(new RowConstraints(SQUARE_SIZE));
        	
        	VBox p=new VBox();
	    	Label name=new Label(val[j].getValue());
	    	owners[j]=new Label();
	    	p.getChildren().add(name);
	    	p.getChildren().add(owners[j]);
	    	grid.add(p,col ,row);
	    	j++;  
	    }
	    row=0;
	    for(col=1;col<SIZE;col++) {
	    	VBox p=new VBox();
	    	Label name=new Label(val[j].getValue());
	    	owners[j]=new Label();
	    	p.getChildren().add(name);
	    	p.getChildren().add(owners[j]);
	    	grid.add(p,col ,row);
	    	j++;  
	    }
	    col=SIZE-1;
	    for (row = 1; row <SIZE; row++) {
	    	VBox p=new VBox();
	    	Label name=new Label(val[j].getValue());
	    	owners[j]=new Label();
	    	p.getChildren().add(name);
	    	p.getChildren().add(owners[j]);
	    	grid.add(p,col ,row);
	    	j++; 
	    }
	    
	    rollDice=new Button("Roll Dice");
	    rollDice.setVisible(false);
	    
	    tradeOffer=new Stage();
	    tradeMessage=new Label();
	    accept=new Button("Accept");
	    refuse=new Button("Refuse");
	    buttons.getChildren().addAll(accept,refuse);
	    boxTrade=new VBox();
	    boxTrade.getChildren().addAll(tradeMessage,buttons);
	    offer=new Scene(boxTrade,500,200);
	    tradeOffer.setScene(offer);
	    
	    actionText=new Text();
	    
		scene=new Scene(grid, (SIZE+1)*SQUARE_SIZE+200, (SIZE+1)*SQUARE_SIZE);
		controller=new GameController(actionText,owners,rollDice,tradeOffer,tradeMessage,scene,dispatcher);
		
	}
}
