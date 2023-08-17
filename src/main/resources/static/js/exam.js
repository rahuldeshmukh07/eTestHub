// function submit() {
//     var option = confirm("Finish Exam?")
//     if (option) {
//         window.location.replace("/dashboard");
//     }
// }



//Start exam

// function startexam() {
//     window.location.replace("/exam");
// }

//Confirmation for logout

function logout() {
    var r = confirm("Are you sure to logout?");
    if (r) {
        $.ajax({

            url: 'http://localhost:8080/api/v1/logout',
            type: 'GET',
            success: function(data) {
                window.location.replace('/');
            },
            error: function(request, error) {
                alert("Request: " + JSON.stringify(request));
            }
        });
    }
}


function terms_changed(termsCheckBox) {
    //If the checkbox has been checked
    if (termsCheckBox.checked) {
        //Set the disabled property to FALSE and enable the button.
        document.getElementById("submit_button").disabled = false;
    } else {
        //Otherwise, disable the submit button.
        document.getElementById("submit_button").disabled = true;
    }
}