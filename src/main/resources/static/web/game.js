var app = new Vue({
    el: '#app',
    data: {
        message: 'Game PlAYER',
        id: '',
        user: '',
        player1: "",
        player2: "",
        main: {},
        shipLocations:[],
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
                            app.shipLocations.push(main.ships[i].locations[j]);

                        }
                    }
                    app.findTheUser();
                    app.findTheSalvo();
                    app.findTheOtherSalvo();
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
                    document.getElementById('S' + this.main.salvo[i].locations[j]).setAttribute("class", "location1");
                }
            }
        },

        findTheOtherSalvo: function () {
            
            console.log(this.shipLocations);
            
            for (var i = 0; i < this.main.OtherSalvo.length; i++) {
                for (var j = 0; j < this.main.OtherSalvo[i].locations.length; j++) {

                    let loc = this.main.OtherSalvo[i].locations[j];
                    let cell = document.getElementById(loc);
                    
                    console.log(loc);
                    
                    
                    if(this.shipLocations.includes(loc)){
                        
                        cell.setAttribute("class", "colorHit");
                    }
                    else{

                    cell.setAttribute("class", "location2");
                    cell.innerHTML = this.main.OtherSalvo[i].turn;
                        
                    }
                }
            }
        },

    }
})
