//Confirmation for logout

//function logout() {
//    var r = confirm("Are you sure to logout?");
//    if (r) {
//        window.location.replace("/login");
//    }
//}
//
////Start exam
//
//function startexam() {
//    window.location.replace("/exam");
//}



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