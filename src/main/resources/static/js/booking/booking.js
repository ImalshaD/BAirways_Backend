const forms = document.getElementById("accordionExample");

const seats = localStorage.getItem("seatIDs").split(',');
const firstNames = [];
const lastNames = [];
const genders = [];
const birthDays = [];
const passPortsNumbers = [];
const nationalities = [];
const emails = [];
const countryCodes = [];
const phoneNumbers = [];
const addressLines1 = [];
const addressLines2 = [];
const addressLines3 = [];

var numOfBookings = seats.length;

loadForms(numOfBookings);
populateSeats(numOfBookings);
loadCoutryCodes();
function loadForms(numberOfbookings) {
  let html = '';
  let passengerForm = `
    <form id="form1">

      <div class="row">
        <div class="col-auto">
          <label for="seat1" class="col-form-label">Seat ID</label>
        </div>
        <div class="col-auto">
          <input class="form-control me-2" type="input" placeholder="SeatID" id="seat1" disabled>
        </div>
      </div>

      <br>

      <div class="row">
        <div class="col-sm">
          <input class="form-control me-2" type="input" placeholder="First Name" id="firstName1" aria-label="Search">
        </div>
        <div class="col-sm">
          <input class="form-control me-2" type="search" placeholder="Last Name" id="lastName1" aria-label="Search">
        </div>
      </div>

      </br>

      <div class="row">
        <div class="col-sm-3">
          <select class="form-select" id="gender1" aria-label="Default select">
            <option disabled selected>Gender</option>
            <option value="1">Male</option>
            <option value="2">Female</option>
          </select>
        </div>
        <div class="col-sm-3">
          <div class="input-group date datepickerclass" id="datepicker1">
            <input type="text" class="form-control" id="birthDay1" placeholder="Birth Day">
            <span class="input-group-append">
                <span class="input-group-text bg-white c-block">
                  <img src="../../images/cliparts/download.jfif" alt="Calender" height="25">
                </span>
            </span>
          </div>
        </div>
      </div>

      <br>

      <div class="row">
        <div class="col-sm-3">
          <input class="form-control me-2" type="input" placeholder="Passport Number" id="passportNo1" aria-label="Search">
        </div>
        <div class="col-sm-3">
          <select class="form-select nat" aria-label="Default select" id="nationality1">
            <option disabled selected>Nationality</option>
            <option value="1">Sri Lanka</option>
            <option value="2">India</option>
          </select>
        </div>
      </div>

      <br>

      <div class="row">
        <div class="col-sm-3">
          <input class="form-control me-2" type="input" placeholder="Email Address" id="email1" aria-label="Search">
        </div>
        <div class="col-sm-2">
          <select class="form-select tel" id="countryCode1" aria-label="Default select">
            <option disabled selected>Country Code</option>
            <option value="+94">+94</option> 
            <option value="+91">+91</option>
          </select>
        </div>
        <div class="col-sm-3">
          <input class="form-control me-2" type="input" placeholder="Phone Number" id="phoneNo1" aria-label="Search">
        </div>
      </div>

      <br>

      <div class="row">

        <div class="col-sm-4">
          <input class="form-control me-2" type="input" placeholder="Address line 1" id="address11" aria-label="Search">
        </div>

        <div class="col-sm-4">
          <input class="form-control me-2" type="input" placeholder="Address line 2" id="address21" aria-label="Search">
        </div>
        <div class="col-sm-4">
          <input class="form-control me-2" type="input" placeholder="Address line 3" id="address31" aria-label="Search">
        </div>

      </div>

    </form>
    `;


  let accordionItem = `
  <div class="accordion-item">
      <h2 class="accordion-header" id="heading1">
        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse1" aria-expanded="true" aria-controls="collapse1">
          Passenger #1
        </button>
      </h2>
      <div id="collapse1" class="accordion-collapse collapse show" aria-labelledby="heading1" data-bs-parent="#accordionExample">
        <div class="accordion-body">
        ${passengerForm}
        </div>
      </div>
    </div>
  `;

  html += accordionItem;
  
  for (var i = 2; i <= numberOfbookings; i++) {


    passengerForm = `
    <form id="form${i}">

      <div class="row">
        <div class="col-auto">
          <label for="seat${i}" class="col-form-label">Seat ID</label>
        </div>
        <div class="col-auto">
          <input class="form-control me-2" type="input" placeholder="SeatID" id="seat${i}" disabled>
        </div>
      </div>

      <br>

      <div class="row">
        <div class="col-sm">
          <input class="form-control me-2" type="input" placeholder="First Name" id="firstName${i}" aria-label="Search">
        </div>
        <div class="col-sm">
          <input class="form-control me-2" type="search" placeholder="Last Name" id="lastName${i}" aria-label="Search">
        </div>
      </div>

      </br>

      <div class="row">
        <div class="col-sm-3">
          <select class="form-select" id="gender${i}" aria-label="Default select">
            <option disabled selected>Gender</option>
            <option value="1">Male</option>
            <option value="2">Female</option>
          </select>
        </div>
        <div class="col-sm-3">
        <div class="input-group date datepickerclass" id="datepicker${i}">
        <input type="text" class="form-control" id="birthDay${i}" placeholder="Birth Day">
        <span class="input-group-append">
            <span class="input-group-text bg-white c-block">
              <img src="../../images/cliparts/download.jfif" alt="Calender" height="25">
            </span>
        </span>
      </div>
        </div>
      </div>

      <br>

      <div class="row">
        <div class="col-sm-3">
          <input class="form-control me-2" type="input" placeholder="Passport Number" id="passportNo${i}" aria-label="Search">
        </div>
        <div class="col-sm-3">
          <select class="form-select nat" id="nationality${i}" aria-label="Default select">
            <option disabled selected>Nationality</option>
            <option value="1">Sri Lanka</option>
            <option value="2">India</option>
          </select>
        </div>
      </div>

      <br>

      <div class="row">
        <div class="col-sm-3">
          <input class="form-control me-2" type="input" placeholder="Email Address" id="email${i}" aria-label="Search">
        </div>
        <div class="col-sm-2">
          <select class="form-select tel" id="countryCode${i}" aria-label="Default select">
            <option disabled selected>Country Code</option>
            <option value="+94">+94</option>
            <option value="+91">+91</option>
          </select>
        </div>
        <div class="col-sm-3">
          <input class="form-control me-2" type="input" placeholder="Phone Number" id="phoneNo${i}" aria-label="Search">
        </div>
      </div>

      <br>

      <div class="row">

        <div class="col-sm-4">
          <input class="form-control me-2" type="input" placeholder="Address line 1" id="address1${i}" aria-label="Search">
        </div>

        <div class="col-sm-4">
          <input class="form-control me-2" type="input" placeholder="Address line 2" id="address2${i}" aria-label="Search">
        </div>
        <div class="col-sm-4">
          <input class="form-control me-2" type="input" placeholder="Address line 3" id="address3${i}" aria-label="Search">
        </div>

      </div>
      
    </form>
    `;


    accordionItem = `
    <div class="accordion-item">
      <h2 class="accordion-header" id="heading${i}">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${i}" aria-expanded="false" aria-controls="collapse${i}">
          Passenger #${i}
        </button>
      </h2>
      <div id="collapse${i}" class="accordion-collapse collapse" aria-labelledby="heading${i}" data-bs-parent="#accordionExample">
        <div class="accordion-body">
        ${passengerForm}
        </div>
      </div>
    </div>
    `;

  

    html += accordionItem;
  }

  forms.innerHTML= html;
  
}



function populateSeats(numberOfbookings) {

  for (var i = 0; i < numberOfbookings; i++) {
    document.getElementById(`seat${i + 1}`).value = seats[i];
  }


}

function proceedClicked() {
  let tripid=localStorage.getItem("trip_id");
  alert(tripid);
  let allBookins={
      'trip_id' : tripid,
      'list' :[]
  }
  for (var i = 1; i <= numOfBookings; i++) {
    let date_object = new Date(document.getElementById(`birthDay${i}`).value)
    let month = date_object.getMonth()+1;
    let date = date_object.getDate();
    if (month<10){
      month='0'+month;
    }
    if (date<10){
      date='0'+date;
    }
    let date_string = `${date_object.getFullYear()}-${month}-${date}`;
    console.log(date_string);
    let singleBooking={
      'passport_number' : document.getElementById(`passportNo${i}`).value,
      'nationality' : document.getElementById(`nationality${i}`).value,
      'first_name' : document.getElementById(`firstName${i}`).value,
      'last_name' : document.getElementById(`firstName${i}`).value,
      'email' : document.getElementById(`email${i}`).value,
      'contact_number' : document.getElementById(`countryCode${i}`).value+document.getElementById(`phoneNo${i}`).value,
      'address_line1' : document.getElementById(`address1${i}`).value,
      'address_line2' : document.getElementById(`address2${i}`).value,
      'address_line3' : document.getElementById(`address3${i}`).value,
      'b_day' : date_string,
      'seat_id' : seats[i-1]
    }
    allBookins.list.push(singleBooking);
  }
  console.log(allBookins);
  $.ajax({
    method: "POST",
    contentType : "application/json",
    url:"../booking/newbooking",
    async : true,
    data :JSON.stringify(allBookins),
    success: function(data){
      console.log(data)
    },
    error :function(xhr,exception){
      console.error(exception)
    }
  })

}
function loadCoutryCodes(){
  $.ajax({
        method: "GET",
        url: "../country/countrylist",
        async: true,
        success: function (data) {
          if (data.code = "SUCCESS") {
            console.log("SUCESS");
            $(".tel").empty();
            $(".nat").empty();
            let Nationality = 'Nationality';
            let Code ="Country Code";
            let nationalityRow = `<option disabled selected>${Nationality}</option>`
            let codeRow = `<option disabled selected>${Code}</option>`
            $(".tel").append(codeRow);
            $(".nat").append(nationalityRow);

            for (let country of data.context) {
              let countryid = country.country_id;
              let nationality1 = country.nationality;
              let countrycode= country.country_phone_code;
              let name1 =country.name;
              let rownat = `<option value=\"${countryid}\">${nationality1}</option>`;
              let rowtel = `<option value=\"+${countrycode}-\">${name1}( +${countrycode} )</option>`;
              $(".tel").append(rowtel);
              $(".nat").append(rownat);
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
}