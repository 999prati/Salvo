var app = new Vue({
    el: '#app',
    data: {
        message: 'Game PlAYER',
        id: '',
        user: '',
        player1: "",
        player2: "",
        main: {},

    },
    created: function () {

        this.findTheId();
        this.dataServer(this.id);


    },

    methods: {

        dataServer: function (id) {
            fetch("/api/game_view/" + id, {
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


                    for (var i = 0; i < main.ships.length; i++) {
                        for (var j = 0; j < main.ships[i].locations.length; j++) {
                            document.getElementById(main.ships[i].locations[j]).setAttribute("class", "location");

                        }
                    }
                    app.findTheUser();
                    app.findTheSalvo();
                })
                .catch(function (error) {
                    console.log(error);

                })

        },

        findTheId: function () {
            this.id = location.search.split('=')[1];

        },
        findTheUser: function () {
            for (var i = 0; i < this.main.games.gamePlayers.length; i++) {
                if (this.id == this.main.games.gamePlayers[i].id) {
                    var player1 = this.main.games.gamePlayers[i].player.player_email;
                } else {
                    var player2 = this.main.games.gamePlayers[i].player.player_email;

                }
                this.player1 = player1 + "(you)";
                this.player2 = player2;
            }
        },
        findTheSalvo: function () {
            for (var i = 0; i < this.main.salvo.length; i++) {
                for (var j = 0; j < this.main.salvo[i].locations.length; j++) {
                    document.getElementById('S'+this.main.salvo[i].locations[j]).setAttribute("class", "location1");
                }
            }
        }

    }
})
