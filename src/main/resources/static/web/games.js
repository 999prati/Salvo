var app = new Vue({
    el: '#app',
    data: {
        message: 'BATTLE SHIP',
        players: "",
        main: {},
        alertDiv: "",
        logged: "false",
        heading: "",
        player: "",
        headingUserName: "",
        games: [],
        object: [],
        showTable2: "false",

    },

    created: function () {

        this.dataServer();
        this.getGames();
        this.returnGames();
        
        //        this.gameInformation();
    },

    methods: {
        dataServer: function () {
            fetch("/api/leaderboard", {
                    method: "GET",
                    creatential: "include",
                })
                .then(function (response) {
                    if (response.ok)
                        return response.json()

                })
                .then(function (json) {

                    main = json;
                    app.main = json;
                    console.log(main);

                })
                .catch(function (error) {
                    console.log(error);

                })
        },


        getGames: function () {
            let url = '/api/games';
            fetch(url, {
                    method: "GET",
                    credentials: "include",
                })
                .then((response) => response.json())
                .then(function (data) {
                    console.log(data)

                    app.games = data;

                    //                    app.games = app.games.game;
                    app.gameInformation();

                    if (app.games.player != null) {
                        app.logged = true;
                        app.showTable2 = true;
                        app.player = app.games.player;
                        app.headingUserName = 'WELCOME:' + '  ' + app.games.player.player_email;

                    } else {
                        app.logged = false;
                        app.showTable2 = false;
                    }

                })
        },

        logIn: function () {
            let user = document.getElementById("inputEmail").value;
            let password = document.getElementById("inputPassword").value;


            fetch("/api/login", {
                    credentials: 'include',
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: 'userName=' + user + '&password=' + password,

                })
                .then(r => {
                    if (r.status == 200) {
                        this.alertDiv = false;
                        this.logged = true;
                        this.heading = true;
                        this.showTable2 = true;
                        app.getGames();
                        app.dataServer();


                    } else {
                        this.alertDiv = true;
                        this.logged = false;
                        this.showTable2 = false;
                    }
                    console.log(r)
                })
                .catch(r => console.log(r))
        },

        logOut: function () {
            fetch("/api/logout", {
                    credentials: 'include',
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },

                })
                .then(r => {
                    if (r.status == 200) {
                        this.alertDiv = false;
                        this.logged = false;
                        this.showTable2 = false;
                        app.getGames()
                    } else {
                        this.alertDiv = false;
                        this.logged = true;
                        this.showTable2 = true;
                    }
                    console.log(r)
                })
                .catch(r => console.log(r))
        },

        signUp: function () {
            let user = document.getElementById("inputEmail").value;
            let password = document.getElementById("inputPassword").value;

            fetch("/api/players", {
                    credentials: 'include',
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: 'userName=' + user + '&password=' + password,
                })
                .then(r => {
                    if (r.status == 201) {
                        this.logged = true;
                        this.showTable2 = true;
                        app.logIn();
                    } else {
                        this.alertDiv = true;
                        this.showTable2 = false;
                    }
                    console.log(r)
                })
                .catch(r => console.log(r))
        },

        gameInformation: function () {
            var container = [];
            for (var i = 0; i < this.games.game.length; i++) {
                var gameNumber = this.games.game[i].id;
                var gamePlayer1 = app.games.game[i].gamePlayers[0].player.player_email;
                var gamePlayer1Id = app.games.game[i].gamePlayers[0].id;
                
                var ShowInGame = app.games.player.player_email;

                if (this.games.game[i].gamePlayers[1] != null) {
                    var gamePlayer2 = app.games.game[i].gamePlayers[1].player.player_email;
                    var gamePlayer2Id = app.games.game[i].gamePlayers[1].id;
                } else {
                    var gamePlayer2 = "JOIN TO PLAY"

                }
                if (gamePlayer2 == ShowInGame || gamePlayer1 == ShowInGame) {
                    var status = "return game"
                } else if (this.games.game[i].gamePlayers[1] == null) {
                    var status = "join Game"
                } else {
                    var status = "no access"
                }

                var object = {
                    numberOfGames: gameNumber,
                    Player1: gamePlayer1,
                    gpId1: gamePlayer1Id,
                    player2: gamePlayer2,
                    gpId2: gamePlayer2Id,
                    showGame: status,
                }
                container.push(object);
            }
            app.object = container;
        },
    }
})
