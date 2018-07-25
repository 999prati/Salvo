var app = new Vue({
    el: '#app',
    data: {
        games: [],
        allGames:[],
    },

    created: function () {
        this.dataServer();
    },


    methods: {

        dataServer: function () {
            var url = fetch("/api/games", {
                    method: "GET",
                })
                .then(function (response) {
                    if (response.ok)
                        return response.json()
                })
                .then(function (json) {
                    var main = json;
                    console.log(main);
                    app.games = main;
                    console.log(app.games);
                    app.gameInformation();


                })
                .catch(function (error) {
                    console.log(error);
                })

        },
        gameInformation: function () {
           var empty = [];
           for (var i = 0; i < app.games.length; i++) {
               var date = new Date(app.games[i].creation).toLocaleString();
               var playerOne = app.games[i].gamePlayers[0].player.player_email;
                
//               ["0"].gamePlayers["0"].player.player_email
               
                if (app.games[i].gamePlayers[1] != null ) {
                   var playerTwo = app.games[i].gamePlayers[1].player.player_email;
               } else {
                  var playerTwo = "JOIN"
                  
               }
               var object = {
                   emailOne : playerOne,
                   emailTwo : playerTwo,
                   date : date,
               }
               console.log(object)
               empty.push(object);            
           }
           app.allGames = empty;
           console.log(app.games);
       },
  
    }
})



