var app = angular.module('login_app', []);

app.controller('logincontroller', function($scope, $http, $location) {
    $scope.submitForm = function() {
        //		var url = $location.absUrl() + "api/v1/login";
        var url = "http://localhost:8080/api/v1/login";
        var config = {
            headers: {
                'Accept': 'text/plain'
            }
        }
        var data = {
            username: $scope.username,
            password: $scope.password
        };
        $http.post(url, data, config).then(function(response) {
            console.log(response.data);
            if (response.data == "Student") {
                location.replace("http://localhost:8080/dashboard");
            } else if (response.data == "Faculty") {
                location.replace("http://localhost:8080/faculty/dashboard");
            } else if (response.data == "Moderator") {
                location.replace("http://localhost:8080/moderator/dashboard");
            } else if (response.data == "Admin") {
                location.replace("http://localhost:8080/admin/dashboard");
            } else {
                $scope.message = "Invalid Credentials!! Try again!";
            }
        }, function(error) {
            $scope.message = "Invalid Credentials!! Try again!";
            $scope.username = "";
            $scope.password = "";
        });

    }
});






/*
mainApp.controller('resultController', function($scope, $http, $location) {

    var url ="http://localhost:8080/api/v1/result/";
    $http.get(url).then(function(response) {
        $scope.response = response.data;
        console.log("Result fetched!");
        console.log(response.data);
        
        
    }, function error(response) {
    	alert("No result found!!");
       $scope.postResultMessage="Error with status"+ response.statusText;
    });
});
*/
/*
mainApp.controller('resultController', function ($scope, $http) {
    $http({
        method:'GET',
        url: 'http://localhost:8080/api/v1/result/'
    }).then(function (response) { 
    	console.log("Result Fetched");
    	$scope.response = response.data;
    	},function erro(response){
    		console.log("Error");});
});
});
*/