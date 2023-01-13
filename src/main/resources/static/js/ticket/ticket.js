let formiata = document.getElementById("fromiata");
let toiata = document.getElementById("toiata");
let flightId = document.getElementById("flighNo");
let boards = document.getElementById("boards");
let departs = document.getElementById("departs");
let arrives = document.getElementById("arrives");
let passe_name = document.getElementById("passenger");
let class1 = document.getElementById("class");
let seat1 = document.getElementById("seat");
let booking_id1 = localStorage.getItem("booking_id");
render(booking_id1);
function render(ticket_id){
    alert(booking_id1);
    $.ajax({
        method: "GET",
        url: `../booking/getTicket/${ticket_id}`,
        async: false,
        success: function (data) {
            if (data.code=="SUCCESS"){
                console.log(data.context.from_iata);
                formiata.innerHTML=data.context.from_iata;
                toiata.innerHTML = data.context.to_iata;
                flightId.innerHTML = `<span>Flight</span>${data.context.flight_id}`;
                boards.innerHTML= `<span>Date</span>${data.context.schedule}`;
                departs.innerHTML=`<span>Departs</span>${data.context.departure}`;
                arrives.innerHTML=`<span>Departs</span>${data.context.arrival}`;
                passe_name.innerHTML=`<span>Passenger</span>${data.context.fullName}`;
                class1.innerHTML=`<span>Class</span>${data.context.class_id}`;
                seat1.innerHTML =`<span>Seat</span>${data.context.seat_id}`;
            }else{
                alert(data.message);
            }
        },
        error: function (xhr, exeption) {
            console.log(exeption)

        }
    })
}