var mainApp = angular.module("mainApp", []);




mainApp.controller('ProfileController', function ($scope, $http, $location) {
    $scope.breadcrumb='profilemoderator';

	    var url = "http://localhost:8080/api/v1/moderator/";
	    
	    
	    var config = {
	  	      headers : {
	  	        'Content-Type' : 'application/json;charset=utf-8;'
	  	      }
	  	    }
	    
		    $http.get(url,config).then(function(response) {
		    	console.log(response);
				  $scope.response = response.data;
		    }, function error(response) {
	      //$scope.postResultMessage = "Error with status: " +  response.statusText;
		    	 window.location.replace('/');
	    });
});
mainApp.controller('examinerController', function ($scope, $http, $location) {
    $scope.breadcrumb='dashboardmoderator';

	    var url = "http://localhost:8080/api/v1/examinerbyId/";
	    
	    var config = {
	  	      headers : {
	  	        'Content-Type' : 'application/json;charset=utf-8;'
	  	      }
	  	    }
	    
		    $http.get(url,config).then(function(response) {
			      $scope.response = response.data;
			      console.log($scope.response);
			      
		    }, function error(response) {
	      //$scope.postResultMessage = "Error with status: " +  response.statusText;
		    	window.location.replace('/');
	    });
});

mainApp.controller('generateController', function ($scope, $http, $location) {
    
	    var url = "http://localhost:8080/api/v1/qbank/subject/";
	    
	    var config = {
	  	      headers : {
	  	        'Content-Type' : 'application/json;charset=utf-8;'
	  	      }
	  	    }
	    
		    $http.get(url,config).then(function(response) {
			      $scope.response = response.data;
			      console.log($scope.response);
			      
		    }, function error(response) {
	      //$scope.postResultMessage = "Error with status: " +  response.statusText;
		//   window.location.replace('/');
		console.log(error);
	    });
});


mainApp.controller("PostController", function ($scope, $http, $location) {
	
	var url = "http://localhost:8080/api/v1/qbank/createQuestion";
    $scope.start_time=null;
    $scope.end_time=null;
    $scope.start_date=null;
    //$scope.duration=null;

    $scope.SendData = function (start_time,end_time,start_date) {
       // use $.param jQuery function to serialize data from JSON 
        var data = {
        	//created_by:created_by,
            start_time: start_time,
            end_time:end_time,
            //end_time: start_time.add(moment.duration(duration,'hours')),
            start_date: start_date
           
        };
    console.log(JSON.stringify(data));
        var config = {
            headers : {
                'Accept': 'text/plain;'
            }
        }
        var data1=JSON.stringify(data);


        $http.post(url,data1).then(function(response) {
        	//if(response.data){
        		console.log("working");
        		$scope.PostDataResponse = response.data;
        		console.log(response.data);
        		alert("Paper uploaded successfully!");
        		window.location="/moderator/dashboard";
        		//$scope.msg = "Post Data Submitted Successfully!";
        	/*}*/}, function (response)  {
        	//	$scope.msg = "Service not Exists";
        		alert("Check all the fields and Try Again!");
        		//window.location.replace('/moderator/dashboard');
        });
    };

});

mainApp.controller("AssignController", function ($scope, $http, $location) {
	
	var url = "http://localhost:8080/api/v1/assignRandomFaculty";
    
    $scope.DistributeData = function() {
       // use $.param jQuery function to serialize data from JSON 
        
        var config = {
            headers : {
                'Accept': 'text/plain;'
            }
        }


        $http.post(url,null).then(function(response) {
        	//if(response.data){
        		console.log("working");
        		
        		alert("Distribution Done!");
        		window.location="/moderator/dashboard";
        		//$scope.msg = "Post Data Submitted Successfully!";
        	/*}*/}, function (response)  {
        	//	$scope.msg = "Service not Exists";
        		alert("Error!");
        		window.location.replace('/moderator/distribution');
        });
    };

});

