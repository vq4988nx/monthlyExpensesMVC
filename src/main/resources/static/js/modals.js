// Get the modal
var modal = document.getElementById('myModal');

// get all the buttons

var allModifyButtons = document.querySelectorAll(".modify-button");
allModifyButtons.forEach(function(button){
    button.addEventListener('click', function (el) {
        modal.style.display = "block";

        // Which modify button was clicked? Read the id
        var id = button.id.replace("modify-button-", "");

        // If the button was modify-button-2, Find the elements with expense-id-2, expense-description-2 ....
        var selectedExpenseId = document.getElementById('expense-id-' + id);
        var selectedExpenseDate = document.getElementById('expense-date-' + id);
        var selectedExpenseDescription = document.getElementById('expense-description-' + id);
        var selectedExpenseAmount = document.getElementById('expense-amount-' + id);
        var selectedExpenseCategory = document.getElementById('expense-category-' + id);
        // todo get the amount, category too, if needed

        // set the values in the form
        document.getElementById("modal-edit-id").value = id;
        document.getElementById("modal-edit-date").value = selectedExpenseDate.innerText.split(' ')[0];  // read the date part, not the time. Ick, hacky. There's probably a nicer way to do this.
        document.getElementById("modal-edit-description").value = selectedExpenseDescription.innerText;
        document.getElementById("modal-edit-amount").value = selectedExpenseAmount.innerText;
        document.getElementById("modal-edit-category").value = selectedExpenseCategory.innerText;

    });
});

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];


// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};
