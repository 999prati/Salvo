var alreadyPlacedShips = [];
var aircraftCarrier1 = [];
var aircraftCarrier2 = [];
var aircraftCarrier3 = [];
var aircraftCarrier4 = [];
var aircraftCarrier5 = [];
var arrayOfShips = [];
var myArrayCells = [];
var alphabet = ["A", "B", "C", "D", "E", "F", "G", 'H', 'I', 'J'];
var columnsNumbers = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"];
var allGridCellsArray = [];
var locations = [];



function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
    app.selectedShip = ev.target.getAttribute("class")
}


function drop(ev) {

    var cellId = ev.target.id;
    if (ev.toElement.classList.contains("cell")) {
        var _target = $("#" + ev.target.id);
        if ($(_target).hasClass("noDrop")) {
            ev.preventDefault();
        } else {
            ev.preventDefault();
            var data = ev.dataTransfer.getData("text");
            ev.target.appendChild(document.getElementById(data));
            getAllShipsData(cellId)
        }
    }
}

function getAllShipsData(cellId) {

    var arrayOfShips = app.arrayOfShips;

    var dropCell = document.getElementById(cellId);
    var ship = dropCell.children[0];
    var arrayInfoOfTheShip = ship.getAttribute("class").split(" ");
    var shipType = arrayInfoOfTheShip[0];
    var shipDirection = arrayInfoOfTheShip[1];
    var letterNumberArray = dropCell.id.split(/(?=[1-9])/);
    var letter = letterNumberArray[0];
    var number = parseFloat(letterNumberArray[1]);


    var loops = 0;

    if (shipType == 'aircraftCarrier1') {
        loops = 2;

    } else if (shipType == 'aircraftCarrier2') {
        loops = 3

    } else if (shipType == 'aircraftCarrier3') {
        loops = 4

    } else if (shipType == 'aircraftCarrier4') {
        loops = 1

    } else if (shipType == 'aircraftCarrier5') {
        loops = 2

    }

    var locations = [];
    if (shipDirection == "horizontal") {
        locations.push(letter + number);
        for (var i = 0; i < loops; i++) {
            number++
            locations.push(letter + number);
        }
    } else {
        for (var i = 0; i < loops + 1; i++) {
            letter = alphabet[alphabet.indexOf(letter) + 1]
            locations.push(letter + number);
        }
    }

    var oneOfTheShips = {
        type: shipType,
        locations: locations
    }

    for (var i = 0; i < arrayOfShips.length; i++) {
        if (arrayOfShips[i].type == oneOfTheShips.type) {
            var index = arrayOfShips.indexOf(arrayOfShips[i]);
            if (index > -1) {
                arrayOfShips.splice(index, 1);
            }
        }
    }
    arrayOfShips.push(oneOfTheShips);
    var error = gridDropAllowances(arrayOfShips)
    if (error) {
        placeShipBack(ship)
        putDragabeShipBack(ship, shipType)

    }
    //    console.log(arrayOfShips);
}
//getAllSalvosData();
//function getAllSalvosData() {
//    var arrayOfSalvos = app.arrayOfSalvos;
//    var oneOfTheSalvo = {
//        turn_number: turn_number,
//        Locations: locations
//    }
//    for (var i = 0; i < arrayOfShips.length; i++) {
//        if (arrayOfSalvos[i].turn_number == oneOfTheSalvo.turn_number) {
//            var index = arrayOfSalvos.indexOf(arrayOfSalvos[i]);
//            if (index > -1) {
//                arrayOfSalvos.splice(index, 1);
//            }
//        }
//    }
//      arrayOfSalvos.push(oneOfTheSalvo);
//    console.log(gfvghffgjhbhjvhvyhvbvb);
//}

//getAllSalvosData();
calculatingTheGridArray();

function rotateToVert(id) {
    var element = document.getElementById(id);

    if (element.classList.contains("horizontal")) {
        element.classList.remove("horizontal")
        element.classList.add("vertical")
        rotationClass = "vertical";
    } else {
        element.classList.add("horizontal")
        element.classList.remove("vertical")
        rotationClass = "horizontal";
    }
    console.log(rotationClass)
    getAllShipsData(element.parentNode.id)

}

function calculatingTheGridArray() {
    var empty = [];
    for (var i = 0; i < alphabet.length; i++) {
        for (var j = 0; j < columnsNumbers.length; j++) {
            var gridCell = alphabet[i] + columnsNumbers[j];
            empty.push(gridCell);
            allGridCellsArray = empty;
        }

    }
}

function gridDropAllowances(ships) {
    var cellAlredyInUse = [];
    var error = false;
    for (var i = 0; i < ships.length; i++) {
        var ship = ships[i];
        for (var j = 0; j < ship.locations.length; j++) {
            var eachLocation = ship.locations[j];

            //            console.log(allGridCellsArray.includes(eachLocation));

            if (allGridCellsArray.includes(eachLocation)) {
                if (!cellAlredyInUse.includes(eachLocation)) {
                    cellAlredyInUse.push(eachLocation);
                } else {
                    var index = ships.indexOf(ship);
                    if (index > -1) {
                        ships.splice(index, 1)
                    }
                    error = true;
                }
            } else {
                var index = ships.indexOf(ship);
                if (index > -1) {
                    ships.splice(index, 1)
                }
                error = true;
            }
        }
    }
    return error;
}

function putDragabeShipBack(ship, shipType) {
    if (shipType == "aircraftCarrier1") {
        document.getElementById("dragg1").appendChild(ship);
    }
    if (shipType == "aircraftCarrier2") {
        document.getElementById("dragg2").appendChild(ship);
    }
    if (shipType == "aircraftCarrier3") {
        document.getElementById("dragg3").appendChild(ship);
    }
    if (shipType == "aircraftCarrier4") {
        document.getElementById("dragg4").appendChild(ship);
    }
    if (shipType == "aircraftCarrier5") {
        document.getElementById("dragg5").appendChild(ship);
    }
}

function getGamePlayerId() {
    var gamePlayerId = window.location.search.split("&")[0].split("=")[1];
    app.gpId = gamePlayerId;
}

function placeShipBack(ship) {
    document.getElementById("ships-container").appendChild(ship)
}

var app = new Vue({
    el: '#app',
    data: {
        message: 'Game PlAYER',
        id: '',
        user: '',
        player1: "",
        player2: "",
        main: {},
        shipLocations: [],
        hitLocations: [],
        selectedShip: "",
        arrayOfShips: [],
        myArrayCells: [],
        placeShipSalvo: false,
        shipsContainer: false,
        secondGrid: false,
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
                    app.main = json;
                    console.log(json);

                    if (app.main.gamesObject.ships.length == 5) {
                        app.shipsContainer = false;
                        app.secondGrid = true;
                        app.placeShipSalvo = true;
                    } else {
                        app.shipsContainer = true;
                        app.secondGrid = false;
                        app.placeShipSalvo = true;
                    }


                    setTimeout(function () {
                        for (var i = 0; i < app.main.gamesObject.ships.length; i++) {

                            for (var j = 0; j < app.main.gamesObject.ships[i].locations.length; j++) {
                                document.getElementById(app.main.gamesObject.ships[i].locations[j]).setAttribute("class", "location");
                                app.shipLocations.push(app.main.gamesObject.ships[i].locations[j]);
                            }
                        }
                        app.findTheUser();
                        app.findTheSalvo();
//                        app.findTheOtherSalvo();
                        app.postShips();
                        //                        app.shotSalvos(id);
                    }, 100)
                })
                .catch(function (error) {
                    console.log(error);
                })
        },

        findTheId: function () {
            this.id = location.search.split('=')[1];

        },

        findTheUser: function () {
            for (var i = 0; i < this.main.gamesObject.games.gamePlayers.length; i++) {
                if (this.id == this.main.gamesObject.games.gamePlayers[i].id) {
                    var player1 = this.main.gamesObject.games.gamePlayers[i].player.player_email;
                } else {
                    var player2 = this.main.gamesObject.games.gamePlayers[i].player.player_email;

                }
                this.player1 = player1 + "(you)";
                this.player2 = player2;

            }
        },

        findTheSalvo: function () {
            var salvos = this.main.gamesObject.salvo
            for (var i = 0; i < salvos.length; i++) {
                for (var j = 0; j < salvos[i].locations.length; j++) {
                    document.getElementById('s' + salvos[i].locations[j]).setAttribute("class",
                        "location1");

                }
            }
        },

        findTheOtherSalvo: function () {
            //            console.log(this.shipLocations);
            for (var i = 0; i < this.main.gamesObject.OtherSalvo.length; i++) {
                for (var j = 0; j < this.main.gamesObject.OtherSalvo[i].locations.length; j++) {
                    let loc = this.main.gamesObject.OtherSalvo[i].locations[j];
                    let cell = document.getElementById(loc);
                    if (this.main.shipLocations.includes(loc)) {
                        cell.setAttribute("class", "colorHit");
                    } else {
                        cell.setAttribute("class", "location2");
                        cell.innerHTML = this.gamesObject.OtherSalvo[i].turn;

                    }
                }
            }
            app.postShips();
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
                .then(function (response) {
                    if (response.status == 200) {
                        location.replace("/web/games.html")
                    }
                })

            location.reload();
        },
        backButton: function () {
            location.replace("/web/games.html")
        },

        postShips: function () {
            var gamePlayerId = this.id;
            if (app.arrayOfShips.length == 5) {
                fetch("/api/games/players/" + gamePlayerId + "/ships", {
                        credentials: 'include',
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(app.arrayOfShips)
                    })
                    .then(r => r.json())
                    .then(r => {
                        console.log(r);
                        this.shipsContainer = true;
                        this.placeShipSalvo = true;

                    })
            } else {
                alert("please place all ships")
            }
        },

        shotShips: function (id) {
            var arrayOfSalvos = app.arrayOfSalvos;
            var element = document.getElementById(id);
            if (document.getElementsByClassName("mystyle").length == 5) {
                document.getElementsByClassName("mystyle")[0].classList.toggle("mystyle");
                element.classList.toggle("mystyle");
            } else {
                element.classList.toggle("mystyle");
            }
            this.fillArray();
            console.log(this.postSalvos)
        },

        fillArray: function () {
            var allSalvoCell = document.getElementsByClassName("mystyle");
            var myArrayCells = [];
            for (var i = 0; i < allSalvoCell.length; i++) {
                myArrayCells.push(allSalvoCell[i].id);

                if (myArrayCells[i].charAt(0) == 's') {
                    myArrayCells[i] = myArrayCells[i].substr(1)
                }
            }
            this.postSalvos = myArrayCells;
        },
        
        postSalvos: function () {
            console.log(this.postSalvos);
            fetch("/api/games/players/" + this.id + "/salvos", {
                    credentials: 'include',
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({locations:this.postSalvos})

                })
                .then(r => r.json())
                .then(r => {
                    console.log(r);
                    console.log(app.myArrayCells)
                })
        },
    }
})
