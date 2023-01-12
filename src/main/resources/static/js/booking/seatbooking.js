const container = document.querySelector('.container');
const seats = document.querySelectorAll('.row .seat:not(.occupied)');
const count = document.getElementById('count');
const total = document.getElementById('total');


var ticketPrice = +50; // get from database
var flightID = localStorage.getItem("flightID");


const businessRows = 10;
const businessCols = 3;
const firstRows = 3;
const firstCols = 4;
const economyRows = 4;
const economyCols = 6;

const first = [];
const business = [];
const economy = [];
const k = [{seatID:1}];

loadplanedata(flightID);
console.log("TESTING")
console.log(k[1].seatID)
//populateUI();
document.getElementById('booking').innerHTML = renderSeats();



function loadplanedata(planeid) {
  k.push({seatID: 2})
  $.ajax({
    method: "GET",
    url: `/seat/tripID/${flightID}`,
    async: true,
    
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

  html += '<div class="seat-container Business">';
  html += '  <div class="class-label">Business Class</div>';
  for (var i = 0; i < businessRows; i++) {

    html += '  <div class="row">';

    for (var j = 0; j < businessCols; j++) {

      //seatID = business[businessCols*i+j].seatID;
      html += `<div class="seat" id="${seatID}">${seatID}</div>`;
    }

    html += '  </div>';

  }
  html += '</div>';


  html += '<div class="seat-container First">';
  html += '  <div class="class-label">First Class</div>';
  for (var i = 0; i < firstRows; i++) {

    html += '  <div class="row">';

    for (var j = 0; j < firstCols; j++) {

      //seatID = first[firstCols*i+j].seatID;
      html += `<div class="seat" id="${seatID}">${seatID}</div>`;
    }

    html += '  </div>';
  }
  html += '</div>';


  html += '<div class="seat-container Economy">';
  html += '  <div class="class-label">Economy Class</div>';
  for (var i = 0; i < economyRows; i++) {

    html += '  <div class="row">';
    for (var j = 0; j < economyCols; j++) {

      //seatID = economy[economyCols*i+j].seatID;
      html += `<div class="seat" id="${seatID}">${seatID}</div>`;
    }
    html += '  </div>';
  }
  html += '</div>';

  html += ' <span><button type="button" onclick="">Proceed</button></span>';



  return html;
}



//get data from storage & populate UI
function populateUI() {
  // add code to show occupied seats for booked seats using DB
  let availability;

  for (var i = 0; i < businessRows; i++) {
    for (var j = 0; j < businessCols; j++) {
      availability = business[businessCols * i + j].availability;
      if (availability == false) {
        document.getElementById(business[businessCols * i + j].seatID).classList.add("occupied");
      }
    }

  }

  for (var i = 0; i < firstRows; i++) {
    for (var j = 0; j < firstCols; j++) {
      availability = first[firstCols * i + j].availability;
      if (availability == false) {
        document.getElementById(first[firstCols * i + j].seatID).classList.add("occupied");
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

  count.innerText = selectedSeatsCount;
  total.innerText = selectedSeatsCount * ticketPrice;
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