package controller;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.stage.Stage;
import model.Labyrinthe;
import model.direction;
import view.View;

public class MyThread extends Thread {
	private Labyrinthe model;
	private View view;
	private Controller controller;
	private Timer timer;
	private Timer clock;
	private int tick;
	private int doNext;
	private Stage stage;

	public MyThread(String name, Labyrinthe lab, View v, Controller c, Stage s) {
		super(name);
		model = lab;
		view = v;
		controller = c;
		stage = s;
		int level = c.getLevel();

		// Definition de la vitesse du jeu : l'intervalle de temps entre deux
		// deplacements diminue en fonction du niveau actuel
		tick = (1000) - (level - 1) * 150;
		if (tick < 400)
			tick = 400;

		// D�marrage des horloges
		// timer : lance le deplacement des objets Movables a intervalles (tick)
		// reguliers
		// clock : indicateur du temps ecoule depuis le debut du niveau
		timer = new Timer();
		clock = new Timer();
		startClock();
		this.start();

		// Initialisation des affichages
		view.updateTime(0);
		view.updateScore(model.getScore());
		view.updateLevel(level);
	}

	private void startClock() {
		clock.scheduleAtFixedRate(new TimerTask() {
			int time = 0;
			int score = model.getScore();

			@Override
			public void run() {
				time = time + 1;
				model.setScore(model.getScore() - 1);
				view.updateTime(time);
				view.updateScore(model.getScore());
			}
		}, 1000, 1000);
	}

	public void run() {
		// Action effectuee toutes les "tick" millisecondes
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				direction myDirection = model.getCurrent_dir();

				// Deplacement du Packman dans la direction courante et du Ghost
				// (plus court chemin vers Packman)
				model.getPackman().move(model, myDirection);
				model.getGhost().move(model);

				// Modification de l'affichage apres les deplacements
				view.updatePlayer(model.getPackman().getPosition().getX(), model.getPackman().getPosition().getY());
				view.updateGhost(model.getGhost().getPosition().getX(), model.getGhost().getPosition().getY());

				int end = model.checkCollision(); // -1 <=> pas fini ; 0 <=>
													// perdu ; 1 <=> gagné;
													// 2,3,4,5 <=> bonbon de
													// type end-1

				doNext = -2; // -1 <=> rejouer ; 0 <=> quitter ; 1 <=>
								// continuer ; -2 <=> partie non-finie
				if (end == 0 || end == 1) { // La partie est finie : collision
											// packman<->ghost ou packman<->exit
					this.cancel();
					clock.cancel();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							doNext = view.drawEndGame(end); // Affichage du
															// pop-up de fin de
															// partie
							if (doNext != -2) { // Le joueur a choisi une action
								switch (doNext) {
								case -1: // On rejoue
									controller.playGame(); // On reinitialise le
															// modele pour
															// rejouer
									controller.start(stage);// On reinitialise
															// la vue
									break;

								case 0: // On quitte
									System.exit(0);
									break;
								case 1: // On continue vers le niveau suivant
									controller.continueGame(stage);
									break;

								default:
									break;
								}
							}
						}
					});
				} else if (end == 2 || end == 3 || end == 4 || end == 5) {
					// Collision avec un bonbon
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							view.pickUpBonbon(model.getScore(), end - 1);
						}
					});
				}
			}
		}, tick, tick); // ms
	}
}
