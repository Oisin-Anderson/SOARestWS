var $$ = function (id) { return document.getElementById(id); };
var rootURL = "http://localhost:8080/SOACondGet/webresources/players";

window.onload = function() {
    getData();
   
};

const getData = () => {
    fetch('/SOACondGet/webresources/players', {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
    .then( (response) => {
        $$("get").innerHTML = "Get Status: " + response.status;
        //alert("Get Status: " + response.status);

        if(response.ok){
            return response.json();
        }
        else{
            console.log("Error Getting The Data");
        }
    })
    .then( (json) => {
        
        if(json){
            console.log(json);
            loadTable(json);
        }
        else{
            console.log("No Data");
        }
    })
    .catch( error => {
        console.log("Catch: " + error);
    });
};




function loadTable(json) {
     
    console.log(json);
    var length = json.length;
    for(var x = 0; x < length; x++){
        
        $$.ajax({
            type: 'PUT',
            contentType: 'application/json',
            url: rootURL + '/' + json[x].id,
            dataType: "json",
            data: formToJSON(json[x]),
            success: function(data, textStatus, jqXHR){
                    findAll();
            },
            error: function(jqXHR, textStatus, errorThrown){
                    alert('Get error: ' + textStatus);
            }
        });
    }
}

function formToJSON(table) {
    return JSON.stringify({
        "id": table.id == "" ? null : table.id,
        "pname": table.pname, 
        "goals": table.goals, 
        "timestamp": table.timestamp
        });
}

function findAll() {
    console.log('findAll');
    $$.ajax({
        type: 'GET',
        url: rootURL,
        dataType: "json", 
        success: renderList,
        error: function() {
            alert('Error occurs!');
        }
    });
}

function renderList(data) {
	console.log('render');
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
}

const addData = (formData) => {
    
    console.log(formData.get("pname"), formData.get("goals"));
    fetch('/SOACondGet/webresources/players', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(formData).toString()
    })
    .then( (response) => {
        $$("add").innerHTML = "Add Status: " + response.status;
        //alert(" Post Status: " + response.status);
        
        if(response.ok){
            return response.json();
        }
        else{
            console.log("Error Getting The Data");
        }
    })
    .then((data) => {
        console.log('Success:', data);
    })
    .catch( error => {
        console.log("Catch: " + error);
    });
};


const editData = (formData) => {
    
    console.log(formData.get("eid"),formData.get("epname"), formData.get("egoals"));
    fetch('/SOACondGet/webresources/players/put', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(formData).toString()
    })
    .then( (response) => {
        $$("edit").innerHTML = "Edit Status: " + response.status;
        //alert(" Put Status: " + response.status);
        
        if(response.ok){
            return response.json();
        }
        else{
            console.log("Error Getting The Data");
        }
    })
    .then((data) => {
        console.log('Success:', data);
    })
    .catch( error => {
        console.log("Catch: " + error);
    });
};

function refresh(){
    var reload = $('#table').DataTable();
    reload.ajax.reload();
    const d = new Date();
    $$("date").innerHTML = d;
}