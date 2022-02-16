package assign4;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Ellipse;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class gobang extends Application {

    // put your code here
    // Indicate which player has a turn, initially it is the \u25cf player
    private char whoseTurn = '\u25cf';

    // Create and initialize cell
    private Cell[][] cell = new Cell[20][20];

    // Create and initialize a status label
    private Label lblStatus = new Label("\u25cf's turn to play");

    // Create variables for the cell been focused
    private int focusX =  0, focusY = 0;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Pane to hold cell
        GridPane pane = new GridPane();
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                pane.add(cell[i][j] = new Cell(i,j), j, i);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(lblStatus);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 600, 600);
        scene.setOnKeyPressed( new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                // turn off highlight on original Place
                cell[focusX][focusY].setFocus( false );

                switch (event.getCode()) {
                    case UP: 
                        focusX-=1;
                        break;
                    case DOWN:
                        focusX+=1;
                        break;
                    case LEFT:
                        focusY-=1;
                        break;
                    case RIGHT:
                        focusY+=1;
                        break;
                    case SPACE:
                        cell[focusX][focusY].placeChessman();
                        break;
                }
                // prevent from moving focus out of the board
                if( focusX < 0 ) focusX = 0;
                if( focusY < 0 ) focusY = 0;
                if( focusX > 19 ) focusX = 19;
                if( focusY > 19 ) focusY = 19;

                // turn on highlight on original Place
                cell[focusX][focusY].setFocus( true );
            }
        });
        primaryStage.setTitle("Gobang"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }


    /** Determine if the cell are all occupied */
    public boolean isFull() {
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                if (cell[i][j].getToken() == ' ')
                    return false;

        return true;
    }

    /** Determine if the player with the specified token wins */
    public boolean isWon(char token, int x, int y) {
        // vertically
        int countDirUp = 1;
        int countDirDown = 1;
        while( x+countDirUp < 20 && cell[x+countDirUp][y].getToken() == token  ) countDirUp++;
        while( x-countDirDown >= 0 && cell[x-countDirDown][y].getToken() == token  ) countDirDown++;
        if( countDirUp + countDirDown > 5) return true;

        // horizontally 
        int countDirLeft = 1;
        int countDirRight = 1;
        while( y+countDirLeft < 20 && cell[x][y+countDirLeft].getToken() == token  ) countDirLeft++;
        while( y-countDirRight >= 0 && cell[x][y-countDirRight].getToken() == token  ) countDirRight++;
        if( countDirLeft + countDirRight > 5) return true;

        // diagonally 
        int countDirUpLeft = 1;
        int countDirDownRight = 1;
        while( x+countDirUpLeft < 20 && y+countDirUpLeft < 20 && cell[x+countDirUpLeft][y+countDirUpLeft].getToken() == token  ) countDirUpLeft++;
        while( x-countDirDownRight >= 0 && y-countDirDownRight >= 0 && cell[x-countDirDownRight][y-countDirDownRight].getToken() == token  ) countDirDownRight++;
        if( countDirUpLeft + countDirDownRight > 5) return true;

        // diagonally
        int countDirDownLeft = 1;
        int countDirUpRight = 1;
        while( x+countDirDownLeft < 20 && y-countDirDownLeft >= 0 && cell[x+countDirDownLeft][y-countDirDownLeft].getToken() == token  ) countDirDownLeft++;
        while( x-countDirUpRight >= 0 && y+countDirUpRight < 20 && cell[x-countDirUpRight][y+countDirUpRight].getToken() == token  ) countDirUpRight++;
        if( countDirDownLeft + countDirUpRight > 5) return true;

        return false;
    }

    /** An inner class for a cell */
    public class Cell extends Pane {

        // put your code here
        // Token used for this cell
        private char token = ' ';
        private int _x = -1;
        private int _y = -1;

        public Cell( int x, int y ) {
            setStyle("-fx-border-color: black");
            this.setPrefSize(2000, 2000);
            this.setOnMouseClicked(e -> placeChessman());
            this._x = x;
            this._y = y;
        }

        public void setFocus(boolean on){
            if( on ){
                setStyle("-fx-border-color: red");
            }
            else{
                setStyle("-fx-border-color: black");
            }
        }

        /** Return token */
        public char getToken() {
            return token;
        }

        /** Set a new token */
        public void setToken(char c) {
            token = c;

            if (token == '\u25cf') {
                Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2 - 4,
                        this.getHeight() / 2 - 4);
                getChildren().add(ellipse); // Add the ellipse to the pane

                
            } else if (token == '\u25cb') {
                Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2 - 4,
                        this.getHeight() / 2 - 4);
                ellipse.setStroke(Color.BLACK);
                ellipse.setFill(Color.WHITE);
                getChildren().add(ellipse); // Add the ellipse to the pane
            }
        }

        /* Handle a mouse click event */
        private void placeChessman() {
            // If cell is empty and game is not over
            if (token == ' ' && whoseTurn != ' ') {
                setToken(whoseTurn); // Set token in the cell


                // Check game status
                if (isWon(whoseTurn, _x, _y)) {
                    lblStatus.setText(whoseTurn + " won! The game is over");
                    whoseTurn = ' '; // Game is over
                } else if (isFull()) {
                    lblStatus.setText("Draw! The game is over");
                    whoseTurn = ' '; // Game is over
                } else {
                    // Change the turn
                    whoseTurn = (whoseTurn == '\u25cf') ? '\u25cb' : '\u25cf';
                    // Display whose turn
                    lblStatus.setText(whoseTurn + "'s turn");
                }
            }
        }

    }

    /**
     * The main method is only needed for the IDE with limited JavaFX support. Not
     * needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
