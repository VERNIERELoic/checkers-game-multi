package launcher;


import controller.Mediator;
import controller.localController.Controller;
import gui.GuiConfig;
import gui.View;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.BoardGame;
import model.Coord;
import model.Model;


public class Launcher extends Application {

	private BoardGame<Coord> model;
	private EventHandler<MouseEvent> controller;
	private View view;
	
	public static void main (String[] args) {

		Launcher.launch();
	}

	@Override
	public void init () throws Exception {
		super.init();
		
		///////////////////////////////////////////////////////////////////////////////////////
		// Objet qui gere les aspects metier du jeu de dame :
		///////////////////////////////////////////////////////////////////////////////////////
		
		this.model = new Model();

		
		///////////////////////////////////////////////////////////////////////////////////////
		// Objet qui contrele les actions de la vue et les transmet au model
		// il renvoie les reponses du model e la vue
		///////////////////////////////////////////////////////////////////////////////////////
		
		this.controller = new Controller();
		
		
		///////////////////////////////////////////////////////////////////////////////////////
		// Fenetre dans laquelle se dessine le damier est ecoutee par controller
		///////////////////////////////////////////////////////////////////////////////////////
				
		this.view = new View(controller);
		
		// Controller doit pouvoir invoquer les methodes du model
		// il enverra ensuite des instructions e view qui relaiera e son objet Board
		// En mode Client/Server 
		// Les actions devront etre propagees sur les vues de chaque client et non pas seulement 
		// sur celle qui a initie l'action 
		 ((Mediator) controller).setView(view);
		 ((Mediator) controller).setModel(model);
	}


	@Override
	public void start (Stage primaryStage) {

		primaryStage.setScene(new Scene(this.view, GuiConfig.HEIGHT, GuiConfig.HEIGHT));
		primaryStage.setTitle("Jeu de dames - Version de depart");
		primaryStage.show();
	
	}

	
}

