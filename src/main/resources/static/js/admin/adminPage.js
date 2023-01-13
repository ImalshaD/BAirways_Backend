renderAirportLists();
function renderAirportLists(){
    try {
        $.ajax({
                method: "GET",
                url: "../airport/airportsShort",
                async: true,
                success: function (data) {
                    if (data.code = "SUCCESS") {
                        console.log("SUCCESS");
                        $("#origin").empty();
                        $("#destination").empty();
                        $("#destination1").empty();
                        let to = 'TO';
                        let from ="FROM";
                        let initToRow = `<option disabled selected>${to}</option>`
                        let initfromRow = `<option disabled selected>${from}</option>`
                        $("#destination").append(initToRow);
                        $("#origin").append(initfromRow);
                        let i=0;
                        for (let air of data.context) {
                            i+=1;
                            let iata_code = air.iata_code;
                            let country = air.coutry;
                            let closest = air.closest;
                            let row = `<option value=\"${iata_code}\" data-iatacode="${iata_code}">${country}(${closest})</option>`;
                            $("#destination").append(row);
                            $("#origin").append(row);
                            $("#destination1").append(row);
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
function button1_Clicked(){
    let flight = document.getElementById("flightID").value;
    let isBelow18 = document.getElementById("below18").checked;
    let over18 = !isBelow18;
    $("#passengerAge").empty();
    $.ajax({
        method: "POST",
        contentType : "application/json",
        url : "../admin/passengersbyage",
        async : true,
        data : JSON.stringify({
            "flight_id" : flight,
            "over18" : over18
        }),
        success : function (data){
            for (let i=0;i<data.context.length;i++){
                let html =`<tr>
                            <td>${i+1}</td>
                            <td>${data.context[i].passport_number}</td>
                            <td>${data.context[i].nationality}</td>
                            <td>${data.context[i].first_name}</td>
                            <td>${data.context[i].last_name}</td>
                            <td>${data.context[i].email}</td>
                            <td>${data.context[i].age}</td>
                        </tr>
                `
                $("#passengerAge").append(html);
            }
        },
        error : function (xhr,error){
            alert(error);
        }
    })

}

function button2_Clicked(){
    
    let starDate1 = new Date(document.getElementById("startDate1").value);
    let endDate1 = new Date(document.getElementById("endDate1").value);
    let destination1 = document.getElementById("destination1").value;
    let year1 = starDate1.getFullYear();
    let year2 = endDate1.getFullYear();
    let month1 = starDate1.getMonth()+1;
    let month2 = endDate1.getMonth()+1;
    let date1 = starDate1.getDate();
    let date2 = endDate1.getDate();
    if (month1<10){
        month1='0'+month1;
    }
    if (date1<10){
        date1='0'+date1;
    }
    if (month2<10){
        month2='0'+month2;
    }
    if (date2<10){
        date2='0'+date2;
    }
    let data_string1 = `${year1}-${month1}-${date1}`;
    let data_string2 = `${year2}-${month2}-${date2}`;
    $("#desti").empty();
    $.ajax({
        method : "POST",
        contentType: "application/json",
        url :"../admin/checkbydesti",
        async : true,
        data : JSON.stringify({
            "from_date" : data_string1,
            "to_date" : data_string2,
            "destination" : destination1
        }),
        success : function (data){
            let html =`
                <tr>
                    <td>${data.context.desti}</td>
                    <td>${data.context.number}</td>
                </tr>
            
            `
            $("#desti").append(html);
        },
        error :function (xhr,error){
            alert(error);
        }
    })
}

function button3_Clicked(){

    let starDate2 = new Date(document.getElementById("startDate2").value);
    let endDate2 = new Date(document.getElementById("endDate2").value);
    let year1 = starDate2.getFullYear();
    let year2 = endDate2.getFullYear();
    let month1 = starDate2.getMonth()+1;
    let month2 = endDate2.getMonth()+1;
    let date1 = starDate2.getDate();
    let date2 = endDate2.getDate();
    if (month1<10){
        month1='0'+month1;
    }
    if (date1<10){
        date1='0'+date1;
    }
    if (month2<10){
        month2='0'+month2;
    }
    if (date2<10){
        date2='0'+date2;
    }
    let data_string1 = `${year1}-${month1}-${date1}`;
    let data_string2 = `${year2}-${month2}-${date2}`;
    $("#numberByClass").empty();
    $.ajax({
        method : "POST",
        contentType: "application/json",
        url :"../admin/bookingsbyType",
        async : true,
        data : JSON.stringify({
            "from_date" : data_string1,
            "to_date" : data_string2,
        }),
        success : function (data){
            for (var i=0;i<data.context.length;i+=1){
                let html=`<tr>
                    <td>${data.context[i].class_name}</td>
                    <td>${data.context[i].result}</td>
                </tr>
                `
                $("#numberByClass").append(html);
            }
        },
        error :function (xhr,error){

        }
    })
    
}

function button4_Clicked(){
    let origin = document.getElementById("origin").value;
    let destination = document.getElementById("destination").value;
    let tblPastFlghtAndPassengerData = document.getElementById("table4");
    $.ajax({
        method :"POST",
        contentType :"application/json",
        url :"../admin/pastFlights",
        data : JSON.stringify({
            "from_iata" : origin,
            "to_iata" : destination
        }),
        success : function (data){
            $("#flightTable").empty();

            for (let i=0;i<data.context.length;i+=1){
                let html = `<tr>
                            <td>${data.context[i].trip_id}</td>
                            <td>${data.context[i].scheduled_date}</td>
                            <td>${data.context[i].status_name}</td>
                            <td>${data.context[i].c}</td>
                        </tr>`
                $("#flightTable").append(html);
            }
        },
        error : function (xrh,error){
            alert(error);
        }
    })
    
}

function loadrevenueTable(){
    $('#revenueTable').empty();
    $.ajax({
        method: "GET",

        url : "../admin/revenue",
        success : function (data){
            if (data.code == "SUCCESS"){

                for (var i=0;i<data.context.length;i+=1){
                    html =`<tr>
                            <td>${data.context[i].modal_name}</td>
                            <td>${data.context[i].revenue}</td>
                        </tr>
                    `;
                    $('#revenueTable').append(html);
                }
            }
        }
    })
}
