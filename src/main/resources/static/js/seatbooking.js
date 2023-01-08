const container = document.querySelector('.container');
const seats = document.querySelectorAll('.row .seat:not(.occupied)');
const count = document.getElementById('count');
const total = document.getElementById('total');
const movieSelect = document.getElementById('movie');
document.getElementById('booking').innerHTML = renderSeats(10, 3, 3, 4, 4, 6);

function renderSeats(businessRows, businessCols, firstRows, firstCols, economyRows, economyCols) {
    var html = '';
  
    html += '<div class="seat-container">';
    html += '  <div class="class-label">Business Class</div>';
    for (var i = 0; i < businessRows; i++) {
      html += '  <div class="row">';
      for (var j = 0; j < businessCols; j++) {
        html += '    <div class="seat"></div>';
      }
      html += '  </div>';
    }
    html += '</div>';
  
    html += '<div class="seat-container">';
    html += '  <div class="class-label">First Class</div>';
    for (var i = 0; i < firstRows; i++) {
      html += '  <div class="row">';
      for (var j = 0; j < firstCols; j++) {
        html += '    <div class="seat"></div>';
      }
      html += '  </div>';
    }
    html += '</div>';
  
    html += '<div class="seat-container">';
    html += '  <div class="class-label">Economy Class</div>';
    for (var i = 0; i < economyRows; i++) {
      html += '  <div class="row">';
      for (var j = 0; j < economyCols; j++) {
        html += '    <div class="seat"></div>';
      }
      html += '  </div>';
    }
    html += '</div>';
  
    return html;
  }
  

populateUI();

let ticketPrice = +movieSelect.value;

//save selected movie index and price
function setMovieData(movieIndex, moviePrice){
    localStorage.setItem('selectedMovieIndex', movieIndex);
    localStorage.setItem('selectedMoviePrice', moviePrice);
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

//get data from local storage & populate UI
function populateUI() {
    const selectedSeats = JSON.parse(localStorage.getItem('selectedSeats'));

    if (selectedSeats !== null && selectedSeats.length > 0) {
        seats.forEach((seat, index) => {
          if (selectedSeats.indexOf(index) > -1) {
            seat.classList.add('selected');
          }
        });
      }

    const selectedMovieIndex = localStorage.getItem('selectedMovieIndex');

    if (selectedMovieIndex !== null) {
        movieSelect.selectedIndex = selectedMovieIndex;
    }
}

//movie select event
movieSelect.addEventListener('change', e => {
    ticketPrice = +e.target.value;
    setMovieData(e.target.selectedIndex, e.target.value)
    updateSelectedCount();
});

//seat click event
container.addEventListener('click', e => {
 if (e.target.classList.contains('seat') && !e.target.classList.contains('occupied'))
 {
    e.target.classList.toggle('selected');
    
    updateSelectedCount();
 }
});

//initial count and total set
updateSelectedCount();