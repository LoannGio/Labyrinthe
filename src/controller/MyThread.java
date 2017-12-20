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
		tick = (1000) - (level - 1) * 150;
		if (tick < 400)
			tick = 400;
		timer = new Timer();
		clock = new Timer();
		System.out.println(tick);
		startClock();
		this.start();
	}

	private void startClock() {
		clock.scheduleAtFixedRate(new TimerTask() {
			int time = 0;

			@Override
			public void run() {
				time = time + 1;
				view.updateTime(time);
			}
		}, 1000, 1000);
	}

	public void run() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				direction myDirection = model.getCurrent_dir();

				model.getPackman().move(model, myDirection);
				model.getGhost().move(model);

				view.updatePlayer(model.getPackman().getPosition().getX(), model.getPackman().getPosition().getY());
				view.updateGhost(model.getGhost().getPosition().getX(), model.getGhost().getPosition().getY());
				int end = model.checkCollision(); // -1 <=> pas fini ; 0 <=>
													// perdu ; 1 <=> gagné
				doNext = -2; // -1 <=> rejouer ; 0 <=> quitter ; 1 <=>
								// continuer ; -2 <=> partie non-finie
				if (end == 0 || end == 1) {
					this.cancel();
					clock.cancel();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							doNext = view.drawEndGame(end);
							if (doNext != -2) {
								switch (doNext) {
								case -1:
									controller.playGame();
									break;

								case 0:
									System.exit(0);
									break;
								case 1:
									controller.continueGame(stage);
									break;

								default:
									break;
								}
							}
						}
					});
				} else if (end == 2) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							view.pickUpBonbon(model.getScore());
						}
					});
				}
			}
		}, tick, tick); // ms
	}
}
