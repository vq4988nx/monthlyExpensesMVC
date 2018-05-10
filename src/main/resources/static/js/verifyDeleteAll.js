var deleteButtons = document.getElementById("delete_button");

for (var x=0; x < deleteButtons.length; x++) {
    deleteButtons[x].addEventListener("click", function(event) {
        var sure = confirm("Did you actually do that?");
        if (!sure) {
            event.preventDefault();
        }
    });
}

delete_button.addEventListener("click", function(event){
    var sure = confirm("Did you actually do this?");
    if (!sure) {
        event.preventDefault();   // don't actually click the button or send a request to the server.
    }
});