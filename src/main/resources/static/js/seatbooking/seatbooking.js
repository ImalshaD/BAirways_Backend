

const container = document.querySelector('.container');
const seats = document.querySelectorAll('.row .seat:not(.occupied)');
const count = document.getElementById('count');
const total = document.getElementById('total');


var ticketPrice = +50; // get from database
var tripid = localStorage.getItem("trip_id");
var plane_id = localStorage.getItem("flightID");


var businessRows = 10;
var businessCols = 3;
var firstRows = 3;
var firstCols = 4;
var economyRows = 4;
var economyCols = 6;

const first = [];
const business = [];
const economy = [];
const seatsDIV=document.getElementById("seats");
loadByplaneID(plane_id);
loadplanedata();
seatsDIV.innerHTML = renderSeats();
console.log(seatsDIV.innerHTML);
populateUI();


function loadByplaneID(planeID){
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/airplane/planebyID/${planeID}`,
    async: false,
    success: function (data) {
      console.log(data.context.plane_id)
      businessRows = data.context.seat_rows_businessclass;
      businessCols = data.context.seat_cols_businessclass;
      firstRows = data.context.seat_rows_firstclass;
      firstCols = data.context.seat_cols_firstclass;
      economyRows = data.context.seat_rows_economyclass;
      economyCols = data.context.seat_cols_economyclass;
    }
  })

}

function loadplanedata() {
  $.ajax({
    method: "GET",
    url: `/seat/tripID/${tripid}`,
    async: false,
    success: function (data) {
      for (let seat of data.context) {
        if (seat.class_id == 1) {
          first.push({ seatID: seat.seat_id, availability: seat.availability });
        } else if (seat.class_id == 2) {
          business.push({ seatID: seat.seat_id, availability: seat.availability });
        } else if (seat.class_id == 3) {
          economy.push({ seatID: seat.seat_id, availability: seat.availability });
        }
      }
    }
  })
}



function renderSeats() {

  var html = '';
  let seatID = '';
  html += '<div class="seat-container First">';
  html += '  <div class="class-label">First Class</div>';
  for (var i = 0; i < firstRows; i++) {

    html += '  <div class="row">';

    for (var j = 0; j < firstCols; j++) {

      seatID = first[firstCols*i+j].seatID;
      html += `<div class="seat" id="${seatID}"></div>`;
    }

    html += '  </div>';
  }
  html += '</div>';

  html += '<div class="seat-container Business">';
  html += '  <div class="class-label">Business Class</div>';
  for (var i = 0; i < businessRows; i++) {
    console.log("Second_class");
    html += '  <div class="row">';

    for (var j = 0; j < businessCols; j++) {

      seatID = business[businessCols*i+j].seatID;
      html += `<div class="seat" id="${seatID}"></div>`;
    }

    html += '  </div>';

  }
  html += '</div>';





  html += '<div class="seat-container Economy">';
  html += '  <div class="class-label">Economy Class</div>';
  for (var i = 0; i < economyRows; i++) {

    html += '  <div class="row">';
    for (var j = 0; j < economyCols; j++) {

      seatID = economy[economyCols*i+j].seatID;
      html += `<div class="seat" id="${seatID}"></div>`;
    }
    html += '  </div>';
  }
  html += '</div>';

  html += ' <span><button type="button" onclick="proceed()" >Proceed</button></span>';



  return html;
}



//get data from storage & populate UI
function populateUI() {
  // add code to show occupied seats for booked seats using DB
  let availability;
  for (var i = 0; i < firstRows; i++) {
    for (var j = 0; j < firstCols; j++) {
      availability = first[firstCols * i + j].availability;
      if (availability == false) {
        document.getElementById(first[firstCols * i + j].seatID).classList.add("occupied");
      }
    }

  }

  for (var i = 0; i < businessRows; i++) {
    for (var j = 0; j < businessCols; j++) {
      availability = business[businessCols * i + j].availability;

      if (availability == 'false') {
        document.getElementById(business[businessCols * i + j].seatID).classList.add("occupied");
      }
    }

  }



  for (var i = 0; i < economyRows; i++) {
    for (var j = 0; j < economyCols; j++) {
      availability = economy[economyCols * i + j].availability;
      if (availability == false) {
        document.getElementById(economy[economyCols * i + j].seatID).classList.add("occupied");
      }
    }

  }

}



//update total and count
function updateSelectedCount() {
  const selectedSeats = document.querySelectorAll('.row .seat.selected');

  const seatsIndex = [...selectedSeats].map(seat => [...seats].indexOf(seat));

  localStorage.setItem('selectedSeats', JSON.stringify(seatsIndex));

  const selectedSeatsCount = selectedSeats.length;

  // count.innerText = selectedSeatsCount;
  // total.innerText = selectedSeatsCount * ticketPrice;
  return [...selectedSeats].map(seat => seat.id);
}

function proceed(){
  localStorage.setItem("seatIDs",updateSelectedCount());
  window.location.replace("../booking/bookingpage");
}


//seat click event
container.addEventListener('click', e => {
  if (e.target.classList.contains('seat') && !e.target.classList.contains('occupied')) {
    e.target.classList.toggle('selected');

    updateSelectedCount();
  }
});

//initial count and total set
updateSelectedCount();