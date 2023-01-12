let from_selector = document.getElementById("fromAirport");
let to_selector = document.getElementById("toAirport");
let dataPicker = document.getElementById("departureDate");
function selectFlight(fligh_id,trip_id){
    localStorage.setItem("flightID",fligh_id);
    localStorage.setItem("trip_id",trip_id);
    window.location.replace("seat/getView")
    // const xhr = new XMLHttpRequest();
    // xhr.open("GET", "http://localhost:8080/seat/getView", true);
    // xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    // xhr.onload = function() {
    //     if (xhr.status === 200) {
    //         console.log("Success!");
    //     } else {
    //         console.error("Error: " + xhr.status);
    //     }
    // };
    // xhr.send();
}
function renderAirportLists(){
    try {
        $.ajax({
                method: "GET",
                url: "airport/airportsShort",
                async: true,
                success: function (data) {
                    if (data.code = "SUCCESS") {
                        console.log("SUCESS");
                        $("#toAirport").empty();
                        $("#fromAirport").empty();
                        let to = 'TO';
                        let from ="FROM";
                        let initToRow = `<option disabled selected>${to}</option>`
                        let initfromRow = `<option disabled selected>${from}</option>`
                        $("#toAirport").append(initToRow);
                        $("#fromAirport").append(initfromRow);
                        let i=0;
                        for (let air of data.context) {
                            i+=1;
                            let iata_code = air.iata_code;
                            let country = air.coutry;
                            let closest = air.closest;
                            let row = `<option value=\"${i}\" data-iatacode="${iata_code}">${country}(${closest})</option>`;
                            $("#toAirport").append(row);
                            $("#fromAirport").append(row);
                        }
                    } else {
                        console.log(data.code)
                    }
                },
                error: function (xhr, exeption) {
                    console.log(exeption)
                }
            }
        )
    }catch (exeption){
        alert(exeption.toString());
    }
}
function validator(){
    let fromSelector = from_selector.options[from_selector.selectedIndex].getAttribute("data-iatacode");
    let toSelector = to_selector.options[to_selector.selectedIndex].getAttribute("data-iatacode");
    let date=dataPicker.value;
    let date_object = new Date(date);
    let month = date_object.getMonth()+1;
    let date_va = date_object.getDate();
    if (month<10){
        month='0'+month;
    }
    if (date_va<10){
        date_va='0'+date_va;
    }
    let date_string = `${date_object.getFullYear()}-${month}-${date_va}`;
    console.log(date_string);
    let today = new Date();
    if (fromSelector==toSelector){
        alert("Select to different Airports");
    }else if (date_object.getTime()<today.getTime()){
        alert("Select valid date closest day you are allowed to book is tommorow")
    }else{
        try{
            $.ajax({
                    method: "POST",
                    contentType: "application/json",
                    url: "trip/tripList",
                    async: true,
                    data: JSON.stringify({
                        "from_iata":fromSelector,
                        "to_iata": toSelector,
                        "date": date_string
                    }),
                    success: function (data) {
                        if (data.code = "SUCCESS") {
                            console.log("SUCESS");
                            $("#flightTable").empty();
                            let i=0;
                            for (let trip of data.context) {
                                i+=1;
                                let trip_id = trip.trip_id;
                                let flight_id = trip.plane_id;
                                let date = trip.scheduled_date;
                                let departure = trip.departure;
                                let arrival = trip.arrival;
                                let row = `
                                    <tr>
                                        <th scope="row">${i}</th>
                                            <td>${trip_id}</td>
                                            <td>${date}</td>
                                            <td>${departure}</td>
                                            <td>${arrival}</td>
                                            <td><button type="button" class="btn btn-dark" onclick="selectFlight(${flight_id},${trip_id})">Book</button></td>
                                    </tr>
                                `;
                                $("#flightTable").append(row);
                            }
                        } else {
                            console.log(data.code)
                        }
                    },
                    error: function (xhr, exeption) {
                        console.log(exeption)
                    }
                }
            )
        }catch (exeption){
            alert(exeption.toString());
        }
    }
}



renderAirportLists();
