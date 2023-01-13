
var table = document.getElementById("bookings");

//retrieve following data for the user currently logged in
var bkId = [];
var route=[];
var from = [];
var to = [];
var dattTimeDep = [];
var dattTimeArrival = [];
var status_list =[];

getBookingDetails();
var numberOfBookings = bkId.length;


loadBookings(numberOfBookings);

function getBookingDetails(){
    bkId = [];
    route=[];
    from = [];
    to = [];
    dattTimeDep = [];
    dattTimeArrival = [];
    status_list =[];
    $.ajax({
        method: "GET",
        url: "/booking/getBookings",
        async: false,
        success: function (data) {
            if (data.code = "SUCCESS") {
                for (let bookingDTO of data.context) {
                    bkId.push(bookingDTO.booking_id);
                    from.push(bookingDTO.from);
                    to.push(bookingDTO.to);
                    let date_1 = bookingDTO.schedule;
                    let departure = bookingDTO.departure;
                    let arrival = bookingDTO.arrival;
                    dattTimeDep.push(`${date_1} ${departure}`);
                    dattTimeArrival.push(`${date_1} ${arrival}`);
                    status_list.push(bookingDTO.status);
                }
            } else {
                console.log(data.code)
            }
        },
        error: function (xhr, exeption) {
            console.log(exeption)
        }
    })
}

function loadBookings(numberOfBookings){
    var html = `
    <thead>
            <tr>
                <th>#</th>
                <th>From</th>
                <th>To</th>
                <th>Departure date & time</th>
                <th>Arrival date & time</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
    `;

    for (var i = 0; i < numberOfBookings; i++) {
        if (status_list[i]==1){
            html += `
        <tr>
                <td>${bkId[i]}</td>
                <td>${from[i]}</td>
                <td>${to[i]}</td>
                <td>${dattTimeDep[i]}</td>
                <td>${dattTimeArrival[i]}</td>
                <td><button disabled="true" type="button" class="btn btn-outline-danger" id="C${bkId[i]}" onclick="cancelBooking(${bkId[i]})">
                       Pending Payments</button>
                </td>
                <td><button type="button" class="btn btn-outline-danger" id="${bkId[i]}" onclick="cancelBooking(this.id)">Cancel
                        Booking</button>
                </td>
        </tr>
        `;
        }else if(status_list[i]==2) {
            html += `
        <tr>
                <td>${bkId[i]}</td>
                <td>${from[i]}</td>
                <td>${to[i]}</td>
                <td>${dattTimeDep[i]}</td>
                <td>${dattTimeArrival[i]}</td>
                <td><button type="button" class="btn btn-outline-danger" id="T${bkId[i]}" onclick="viewticket(${bkId[i]})">
                       View Ticket</button>
                </td>
                <td><button type="button" class="btn btn-outline-danger" id="${bkId[i]}" onclick="cancelBooking(this.id)">Cancel
                        Booking</button>
                </td>
                
        </tr>
        `;
        }else if(status_list[i]==4){
            html += `
        <tr>
                <td>${bkId[i]}</td>
                <td>${from[i]}</td>
                <td>${to[i]}</td>
                <td>${dattTimeDep[i]}</td>
                <td>${dattTimeArrival[i]}</td>
                <td><button type="button"  class="btn btn-outline-danger" id="T${bkId[i]}" onclick="viewticket(${bkId[i]})">
                       View Ticket</button>
                </td>
                <td><button type="button" disabled="true" class="btn btn-outline-danger" id="${bkId[i]}" onclick="cancelBooking(this.id)">Completed
                        Trip</button>
                </td>
                
        </tr>
        `;
        }else{
            html += `
        <tr>
                <td>${bkId[i]}</td>
                <td>${from[i]}</td>
                <td>${to[i]}</td>
                <td>${dattTimeDep[i]}</td>
                <td>${dattTimeArrival[i]}</td>
                <td><button disabled="true" type="button" class="btn btn-outline-danger" id="T${bkId[i]}" onclick="viewticket(${bkId[i]})">
                       View Ticket</button>
                </td>
                <td><button disabled='true' type="button" class="btn btn-outline-danger" id="${bkId[i]}" onclick="cancelBooking(this.id)">Canceled
                        </button>
                </td>
        </tr>
        `;
        }

    }

    table.innerHTML = html;

}

function cancelBooking(clicked_id){

    isTrue = confirm(`Are you sure you want to cancel booking ${clicked_id}!..`);

    if(isTrue){
        $.ajax({
            method: "POST",
            url: `/booking/cancelbooking/${clicked_id}`,
            async: false,
            success: function (data) {
                if (data.code = "SUCCESS") {
                    getBookingDetails();
                    loadBookings(numberOfBookings);
                } else {
                    console.log(data.code);
                    alert("Cancelling Failed");
                }
            },
            error: function (xhr, exeption) {
                console.log(exeption)
            }
        })
    }

}
function viewticket(booking_id){
    localStorage.setItem("booking_id",booking_id);
    window.location.replace("/booking/ticket");
}