package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
		System.out.println("hellopratibha");
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository,
									  GameRepository gameRepository,
									  GamePlayerRepository gamePlayerRepository,
									  ShipRepository shipRepository) {
		return (args) -> {

			Player player1 = new Player("j.bauer@ctu.gov ");
			Player player2 = new Player("c.obrian@ctu.gov");
			Player player3 = new Player("kim_bauer@gmail.com ");
			Player player4 = new Player("t.almeida@ctu.gov");

			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);


			Game game1 = new Game();
			Game game2 = new Game();
			Game game3 = new Game();
			Game game4 = new Game();
			Game game5 = new Game();
			Game game6 = new Game();
			Game game7 = new Game();
			Game game8 = new Game();


			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);
			gameRepository.save(game4);
			gameRepository.save(game5);
			gameRepository.save(game6);
			gameRepository.save(game7);
			gameRepository.save(game8);


			GamePlayer gamePlayer1 = new GamePlayer(game1, player1);
			GamePlayer gamePlayer2 = new GamePlayer(game1, player2);
			GamePlayer gamePlayer3 = new GamePlayer(game2, player1);
			GamePlayer gamePlayer4 = new GamePlayer(game2, player2);
			GamePlayer gamePlayer5 = new GamePlayer(game3, player2);
			GamePlayer gamePlayer6 = new GamePlayer(game3, player4);
			GamePlayer gamePlayer7 = new GamePlayer(game4, player1);
			GamePlayer gamePlayer8 = new GamePlayer(game4, player2);
			GamePlayer gamePlayer9 = new GamePlayer(game5, player2);
			GamePlayer gamePlayer10 = new GamePlayer(game5, player1);
			GamePlayer gamePlayer11 = new GamePlayer(game6, player3);
			GamePlayer gamePlayer12 = new GamePlayer(game6, player3);
			GamePlayer gamePlayer13 = new GamePlayer(game7, player4);
			GamePlayer gamePlayer14 = new GamePlayer(game8, player3);

			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			gamePlayerRepository.save(gamePlayer3);
			gamePlayerRepository.save(gamePlayer4);
			gamePlayerRepository.save(gamePlayer5);
			gamePlayerRepository.save(gamePlayer6);
			gamePlayerRepository.save(gamePlayer7);
			gamePlayerRepository.save(gamePlayer8);
			gamePlayerRepository.save(gamePlayer9);
			gamePlayerRepository.save(gamePlayer10);
			gamePlayerRepository.save(gamePlayer11);
			gamePlayerRepository.save(gamePlayer12);
			gamePlayerRepository.save(gamePlayer13);
			gamePlayerRepository.save(gamePlayer14);


			List<String> locations1 = Arrays.asList("H2", "H3", "H4");
			List<String> locations2 = Arrays.asList("E1", "F1", "G1");
			List<String> locations3 = Arrays.asList("B4", "B5");
			List<String> locations4 = Arrays.asList("B5", "C5", "D5");
			List<String> locations5 = Arrays.asList("F1", "F2");
			List<String> locations6 = Arrays.asList("B5", "C5", "D5");
			List<String> locations7 = Arrays.asList("C6", "C7");
			List<String> locations8 = Arrays.asList("A2", "A3", "A4");
			List<String> locations9 = Arrays.asList("G6", "H6");
			List<String> locations10 = Arrays.asList("B5", "C5", "D5");
			List<String> locations11= Arrays.asList("C6", "C7");
			List<String> locations12= Arrays.asList("A2", "A3", "A4");
			List<String> locations13= Arrays.asList("G6", "H6");
			List<String> locations14 = Arrays.asList("B5", "C5", "D5");
			List<String> locations15= Arrays.asList("C6", "C7");
			List<String> locations16 = Arrays.asList("A2", "A3", "A4");
			List<String> locations17 = Arrays.asList("G6", "H6");
			List<String> locations18 = Arrays.asList("B5", "C5", "D5");
			List<String> locations19 = Arrays.asList("C6", "C7");
			List<String> locations20 = Arrays.asList("A2", "A3", "A4");
			List<String> locations21 = Arrays.asList("G6", "H6");
			List<String> locations22 = Arrays.asList("B5", "C5", "D5");
			List<String> locations23 = Arrays.asList("C6", "C7");
			List<String> locations24 = Arrays.asList("B5", "C5", "D5");
			List<String> locations25 = Arrays.asList("C6", "C7");
			List<String> locations26 = Arrays.asList("A2", "A3", "A4");
			List<String> locations27= Arrays.asList("G6", "H6");




			Ship ship1 = new Ship("Destroyer",gamePlayer1,locations1);
			Ship ship2 = new Ship("Submarine",gamePlayer2,locations2);
			Ship ship3 = new Ship("Patrol Boat",gamePlayer3,locations3);
			Ship ship4 = new Ship("Destroyer",gamePlayer4,locations4);
			Ship ship5 = new Ship("Patrol Boat",gamePlayer5,locations5);
			Ship ship6 = new Ship("Destroyer",gamePlayer6,locations6);
			Ship ship7 = new Ship("Patrol Boat",gamePlayer7,locations7);
			Ship ship8 = new Ship("Submarine",gamePlayer8,locations8);
			Ship ship9 = new Ship("Patrol Boat",gamePlayer9,locations9);
			Ship ship10 = new Ship("Destroyer",gamePlayer10,locations10);
			Ship ship11= new Ship("Patrol Boat",gamePlayer11,locations11);
			Ship ship12= new Ship("Submarine",gamePlayer12,locations12);
			Ship ship13= new Ship("Patrol Boat",gamePlayer13,locations13);
			Ship ship14= new Ship("Destroyer",gamePlayer14,locations14);
			Ship ship15= new Ship("Patrol Boat",gamePlayer1,locations15);
			Ship ship16= new Ship("Submarine",gamePlayer2,locations16);
			Ship ship17= new Ship("Patrol Boat",gamePlayer3,locations17);
			Ship ship18= new Ship("Destroyer",gamePlayer4,locations18);
			Ship ship19= new Ship("Patrol Boat",gamePlayer5,locations19);
			Ship ship20= new Ship("Submarine",gamePlayer6,locations20);
			Ship ship21= new Ship("Patrol Boat",gamePlayer7,locations21);
			Ship ship22= new Ship("Destroyer",gamePlayer8,locations22);
			Ship ship23= new Ship("Patrol Boat",gamePlayer9,locations23);
			Ship ship24= new Ship("Destroyer",gamePlayer10,locations24);
			Ship ship25 = new Ship("Patrol Boat",gamePlayer11,locations25);
			Ship ship26 = new Ship("Submarine",gamePlayer12,locations26);
			Ship ship27 = new Ship("Patrol Boat",gamePlayer13,locations27);


			shipRepository.save(ship1);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship4);
			shipRepository.save(ship5);
			shipRepository.save(ship6);
			shipRepository.save(ship7);
			shipRepository.save(ship8);
			shipRepository.save(ship9);
			shipRepository.save(ship10);
			shipRepository.save(ship11);
			shipRepository.save(ship12);
			shipRepository.save(ship13);
			shipRepository.save(ship14);
			shipRepository.save(ship15);
			shipRepository.save(ship16);
			shipRepository.save(ship17);
			shipRepository.save(ship18);
			shipRepository.save(ship19);
			shipRepository.save(ship20);
			shipRepository.save(ship21);
			shipRepository.save(ship22);
			shipRepository.save(ship23);
			shipRepository.save(ship24);
			shipRepository.save(ship25);
			shipRepository.save(ship26);
			shipRepository.save(ship27);

		};
	}
}




