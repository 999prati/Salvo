var app = new Vue({
    el: '#app',
    data: {
        message: 'Game PlAYER',


    },
    created: function () {
        this.dataServer();
    },


    methods: {

        dataServer: function () {
            var url = fetch("/api/game_view/1", {
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

                })
                .catch(function (error) {
                    console.log(error);
                })

        }
    }
})
