let from_selector = document.getElementById("fromAirport");
let to_selector = document.getElementById("toAirport");
let dataPicker = document.getElementById("departureDate");
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
    let today = new Date();
    if (fromSelector==toSelector){
        alert("Select to different Airports");
    }else if (date_object.getTime()<today.getTime()){
        alert("Select valid date closest day you are allowed to book is tomorow")
    }else{
        try{
            $.ajax({
                    method: "GET",
                    url: "trip/tripList",
                    async: true,
                    success: function (data) {
                        if (data.code = "SUCCESS") {
                            console.log("SUCESS");
                            $("#flightTable").empty();
                            let i=0;
                            for (let trip of data.context) {
                                i+=1;
                                let trip_id = trip.trip_id;
                                let date = trip.scheduled_date
                                let departure = trip.departure;
                                let arrival = trip.arrival;
                                let row = `
                                    <tr>
                                        <th scope="row">{i}</th>
                                            <td>{trip_id}</td>
                                            <td>{date}</td>
                                            <td>{departure}</td>
                                            <td>{arrival}</td>
                                            <td><button type="button" class="btn btn-dark">Book</button></td>
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
