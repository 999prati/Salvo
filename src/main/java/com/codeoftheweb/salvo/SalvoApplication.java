package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
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
									  GamePlayerRepository gamePlayerRepository) {
		return (args) -> {

			Player player1 = new Player("j.bauer@ctu.gov ");
			Player player2 = new Player("c.obrian@ctu.gov");
			Player player3 = new Player("t.almeida@ctu.gov");
			Player player4 = new Player("g.bauer@ctu.gov");
//			Player player5 = new Player("j.bauer@ctu.gov");
//			Player player6 = new Player("j.bauer@ctu.gov");
//			Player player7 = new Player("j.bauer@ctu.gov");
//			Player player8 = new Player("j.bauer@ctu.gov");



			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);
//			playerRepository.save(player5);
//			playerRepository.save(player6);
//			playerRepository.save(player7);
//			playerRepository.save(player8);


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




			GamePlayer player1game1 = new GamePlayer(game1,player1);
			GamePlayer player2game2 = new GamePlayer(game2,player2);
			GamePlayer player3game3 = new GamePlayer(game3,player3);
			GamePlayer player4game4 = new GamePlayer(game4,player4);
			GamePlayer player5game5 = new GamePlayer(game5,player2);
			GamePlayer player6game6 = new GamePlayer(game6,player1);





			gamePlayerRepository.save(player1game1);
			gamePlayerRepository.save(player2game2);
			gamePlayerRepository.save(player3game3);
			gamePlayerRepository.save(player4game4);
			gamePlayerRepository.save(player5game5);
			gamePlayerRepository.save(player6game6);






		};

	}
}




