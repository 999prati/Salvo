package com.codeoftheweb.salvo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws Throwable{
		SpringApplication.run(SalvoApplication.class, args);
		System.out.println("hellopratibha");
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository,
									  GameRepository gameRepository,
									  GamePlayerRepository gamePlayerRepository,
									  ShipRepository shipRepository, SalvoRepository salvoRepository,
									  ScoreRepository scoreRepository) {
		return (args) -> {

			Player player1 = new Player("j.bauer@ctu.gov","24");
			Player player2 = new Player("c.obrian@ctu.gov","42");
			Player player3 = new Player("kim_bauer@gmail.com","kb");
			Player player4 = new Player("t.almeida@ctu.gov","mole");

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
			GamePlayer gamePlayer7 = new GamePlayer(game4, player2);
			GamePlayer gamePlayer8 = new GamePlayer(game4, player1);
			GamePlayer gamePlayer9 = new GamePlayer(game5, player4);
			GamePlayer gamePlayer10 = new GamePlayer(game5, player1);
			GamePlayer gamePlayer11 = new GamePlayer(game6, player3);
			GamePlayer gamePlayer13 = new GamePlayer(game7, player4);
			GamePlayer gamePlayer15 = new GamePlayer(game8, player3);
			GamePlayer gamePlayer16 = new GamePlayer(game8, player4);

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
			gamePlayerRepository.save(gamePlayer13);
			gamePlayerRepository.save(gamePlayer15);
			gamePlayerRepository.save(gamePlayer16);


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
			List<String> locations11 = Arrays.asList("C6", "C7");
			List<String> locations12 = Arrays.asList("A2", "A3", "A4");
			List<String> locations13 = Arrays.asList("G6", "H6");
			List<String> locations14 = Arrays.asList("B5", "C5", "D5");
			List<String> locations15 = Arrays.asList("C6", "C7");
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
			List<String> locations27 = Arrays.asList("G6", "H6");


			Ship ship1 = new Ship("Destroyer", gamePlayer1, locations1);
			Ship ship2 = new Ship("Submarine", gamePlayer1, locations2);
			Ship ship3 = new Ship("Patrol Boat", gamePlayer1, locations3);
			Ship ship4 = new Ship("Destroyer", gamePlayer2, locations4);
			Ship ship5 = new Ship("Patrol Boat", gamePlayer2, locations5);
			Ship ship6 = new Ship("Destroyer", gamePlayer3, locations6);
			Ship ship7 = new Ship("Patrol Boat", gamePlayer3, locations7);
			Ship ship8 = new Ship("Submarine", gamePlayer4, locations8);
			Ship ship9 = new Ship("Patrol Boat", gamePlayer4, locations9);
			Ship ship10 = new Ship("Destroyer", gamePlayer5, locations10);
			Ship ship11 = new Ship("Patrol Boat", gamePlayer5, locations11);
			Ship ship12 = new Ship("Submarine", gamePlayer6, locations12);
			Ship ship13 = new Ship("Patrol Boat", gamePlayer6, locations13);
			Ship ship14 = new Ship("Destroyer", gamePlayer7, locations14);
			Ship ship15 = new Ship("Patrol Boat", gamePlayer7, locations15);
			Ship ship16 = new Ship("Submarine", gamePlayer8, locations16);
			Ship ship17 = new Ship("Patrol Boat", gamePlayer8, locations17);
			Ship ship18 = new Ship("Destroyer", gamePlayer9, locations18);
			Ship ship19 = new Ship("Patrol Boat", gamePlayer9, locations19);
			Ship ship20 = new Ship("Submarine", gamePlayer10, locations20);
			Ship ship21 = new Ship("Patrol Boat", gamePlayer10, locations21);
			Ship ship22 = new Ship("Destroyer", gamePlayer11, locations22);
			Ship ship23 = new Ship("Patrol Boat", gamePlayer11, locations23);
			Ship ship24 = new Ship("Destroyer", gamePlayer15, locations24);
			Ship ship25 = new Ship("Patrol Boat", gamePlayer15, locations25);
			Ship ship26 = new Ship("Submarine", gamePlayer16, locations26);
			Ship ship27 = new Ship("Patrol Boat", gamePlayer16, locations27);


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


			Salvo salvo1 = new Salvo("1", gamePlayer1, locations1);
			Salvo salvo2 = new Salvo("1", gamePlayer2, locations2);
			Salvo salvo3 = new Salvo("2", gamePlayer1, locations3);
			Salvo salvo4 = new Salvo("2", gamePlayer2, locations4);
			Salvo salvo5 = new Salvo("1", gamePlayer3, locations5);
			Salvo salvo6 = new Salvo("1", gamePlayer4, locations6);
			Salvo salvo7 = new Salvo("2", gamePlayer3, locations7);
			Salvo salvo8 = new Salvo("2", gamePlayer4, locations8);
			Salvo salvo9 = new Salvo("1", gamePlayer5, locations9);
			Salvo salvo10 = new Salvo("1", gamePlayer6, locations10);
			Salvo salvo11 = new Salvo("2", gamePlayer5, locations11);
			Salvo salvo12 = new Salvo("2", gamePlayer6, locations12);
			Salvo salvo13 = new Salvo("1", gamePlayer7, locations13);
			Salvo salvo14 = new Salvo("1", gamePlayer8, locations14);
			Salvo salvo15 = new Salvo("2", gamePlayer7, locations15);
			Salvo salvo16 = new Salvo("2", gamePlayer8, locations16);
			Salvo salvo17 = new Salvo("1", gamePlayer9, locations17);
			Salvo salvo18 = new Salvo("1", gamePlayer10, locations18);
			Salvo salvo19 = new Salvo("2", gamePlayer9, locations19);
			Salvo salvo20 = new Salvo("2", gamePlayer10, locations20);
			Salvo salvo22 = new Salvo("3", gamePlayer10, locations22);


			salvoRepository.save(salvo1);
			salvoRepository.save(salvo2);
			salvoRepository.save(salvo3);
			salvoRepository.save(salvo4);
			salvoRepository.save(salvo5);
			salvoRepository.save(salvo6);
			salvoRepository.save(salvo7);
			salvoRepository.save(salvo8);
			salvoRepository.save(salvo9);
			salvoRepository.save(salvo10);
			salvoRepository.save(salvo11);
			salvoRepository.save(salvo12);
			salvoRepository.save(salvo13);
			salvoRepository.save(salvo14);
			salvoRepository.save(salvo15);
			salvoRepository.save(salvo16);
			salvoRepository.save(salvo17);
			salvoRepository.save(salvo18);
			salvoRepository.save(salvo19);
			salvoRepository.save(salvo20);
			salvoRepository.save(salvo22);


			Score score1 = new Score(game1, player1, 1.0);
			Score score2 = new Score(game1, player2, 0.0);
			Score score3 = new Score(game2, player1, 0.5);
			Score score4 = new Score(game2, player2, 0.5);
			Score score5 = new Score(game3, player2, 1.0);
			Score score6 = new Score(game3, player4, 0.0);
			Score score7 = new Score(game4, player2, 0.5);
			Score score8 = new Score(game4, player1, 0.5);

			scoreRepository.save(score1);
			scoreRepository.save(score2);
			scoreRepository.save(score3);
			scoreRepository.save(score4);
			scoreRepository.save(score5);
			scoreRepository.save(score6);
			scoreRepository.save(score7);
			scoreRepository.save(score8);

		};
	}
}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	PlayerRepository playerRepository;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inputName-> {
			Player player = playerRepository.findByUserName(inputName);
			if (player != null) {
				return new User(player.getUserName(), player.getPassword(),
						AuthorityUtils.createAuthorityList("USER"));
			} else {
				throw new UsernameNotFoundException("Unknown user: " + inputName);
			}
		});
	}
}

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/**").permitAll()
//				.antMatchers("/api/game_view").fullyAuthenticated()
				.antMatchers("/api/login").permitAll()
				.antMatchers("/api/players").permitAll()
//				.antMatchers("/rest/**").denyAll()
				.anyRequest().fullyAuthenticated()
				.and()
				.formLogin();

		http.formLogin()
				.usernameParameter("userName")
				.passwordParameter("password")
				.loginPage("/api/login");

		http.logout().logoutUrl("/api/logout");

		// turn off checking for CSRF tokens
		http.csrf().disable();

		// if user is not authenticated, just send an authentication failure response
		http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if login is successful, just clear the flags asking for authentication
		http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

		// if login fails, just send an authentication failure response
		http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if logout is successful, just send a success response
		http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}
}
