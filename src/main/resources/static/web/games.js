var app = new Vue({
    el: '#app',
    data: {
        message: 'PLAYERS SCORE BOARD',
        players: "",
        main: {},
        alertDiv: "",
        logged: "false",
        heading: "",
        player: "",
        headingUserName: "",
    },

    created: function () {

        this.dataServer();
        this.getGames();
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


                    if (app.games.player != null) {
                        app.logged = true;
                        app.headingUserName = app.games.player.player_email;


                    } else {
                        app.logged = false;
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
//                        location.reload();
                        this.alertDiv = false;
                        this.logged = true;
                        this.heading = true;
                        app.getGames();
                        app.dataServer();

                    } else {
                        this.alertDiv = true;
                        this.logged = false;
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
                        app.getGames()
                    } else {
                        this.alertDiv = false;
                        this.logged = true;
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
                        app.logIn();
                    } else {
                        this.alertDiv = true;
                    }
                    console.log(r)
                })
                .catch(r => console.log(r))
        },
    }
})
