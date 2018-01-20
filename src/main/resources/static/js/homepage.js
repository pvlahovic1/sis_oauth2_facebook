$(document).ready(function() {
    $("#newDataForm").submit(function(e){
        e.preventDefault(e);
    });
});

function showNewForm() {
    var element = $("#inputWrapper");

    if (element.is(":visible")) {
        element.hide()
    } else {
        element.show()
    }
}

function deleteData(id) {
    var result = confirm("Sigurno želite obrisati organizaciju?");
    if (result) {
        $.ajax({
            url : "/rest/data/delete/" + id,
            type : 'GET',
            success : function(data) {
                $('#organizacijeModal').modal('hide');
                refreshPage();
            },
            error : function(request,error)
            {
                console.log("Request: "+JSON.stringify(request));
            }
        });
    }
}

function refreshPage() {
    $.ajax({
        url : "/data/table",
        type : 'GET',
        success : function(data) {
            $("#tableWrapper").html(data);
        },
        error : function(request,error)
        {
            console.log("Request: "+JSON.stringify(request));
        }
    });
}

function addNew() {
    $.ajax({
        url : "/rest/data/new/save",
        type : 'POST',
        data : JSON.stringify(getFormData("newDataForm")),
        contentType: "application/json; charset=UTF-8",

        success : function(data) {
            hideInput();
            refreshPage();
        },
        error : function(request,error)
        {
            console.log("Request: "+JSON.stringify(request));
        }
    });
}

function hideInput() {
    $("#inputWrapper").hide();
    $("#title").val('');
    $("#value").val('');

}

function getFormData(formName) {
    var data = {};
    $("#" + formName).serializeArray().map(function(x){data[x.name] = x.value;});

    console.log(data);

    return data;
}