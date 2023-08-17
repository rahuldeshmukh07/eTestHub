/*function upload() {
	alert("I am an alert box!");
}

*/



var globalResults;




var faculty_app = angular.module('faculty_app', []);

//var exam=document.getElemntById("examyear").value;
//var sub=document.getElemntById("subject_id").value;

faculty_app.directive('fileInput', ['$parse', function($parse) {
    return {
        restrict: 'A',
        link: function(scope, elm, attrs) {
            elm.bind('change', function() {
                $parse(attrs.fileInput)
                    .assign(scope, elm[0].files)
                scope.$apply()
            });
        }
    }
}]);
 faculty_app.controller('myCtrl', function($scope, $http) {
	 
	 
	 var url = "http://localhost:8080/api/v1/faculty/examview/";
	    $http.get(url).then(function (response) {
	      $scope.examlist = response.data;
	      console.log(response.data);
	    }, function error(response) {
	      $scope.postResultMessage = "Error with status: " +  response.statusText;
	    });
	  
	    var url = "http://localhost:8080/api/v1/faculty/subjectview/";
	    $http.get(url).then(function (response) {
	      $scope.subjectlist = response.data;
	      console.log(response.data);
	    }, function error(response) {
	      $scope.postResultMessage = "Error with status: " +  response.statusText;
	    });
	
	 
	$scope.uploadFile = function() {
		 
		console.log("CALLED");
		
		//Take the first selected file
		 console.log($scope.selectedsubject);
		 if(""==$scope.selectedsubject)
			 {
			 alert("please select subject");
			
			 }
			//Take the first selected file
		 console.log($scope.selectedexam+" vvv");
		 if(""==$scope.selectedexam)
		 {
		 alert("please select year");
		 }
		 console.log($scope.qbfile);
		 if($scope.qbfile.length==0)
			 {
			 alert("please select file");
			 }
		 var fd = new FormData();
		fd.append("file", $scope.qbfile[0]);
		fd.append("exam_id" , $scope.selectedexam);
		fd.append("subject_id", $scope.selectedsubject);
//		fd.append("faculty_id", 1003);
		var url = "http://localhost:8080/api/v1/upload";
		var config = { headers: {'Content-Type': undefined},
					   transformRequest: angular.identity
					 }
		$http.post(url, fd, config).then(response=>{console.log(response);
//		$scope.message=" Uploading Successfull!";
		alert("Succesfully`!");
		},error=>{console.log(error);
		alert(" SelectionInvalid");
		location.reload();
		})
		
	
	};
	
 });
 

 
 /*faculty_app.controller('taskCtrl', function($scope,$http){
	 $http.get("http://localhost:8080/api/v1/taskbar/10002").then(function (response) {$scope.tbresponse = response.data.records;});
		});
 });
*/
 
 // controller
 // ng-click  =  fetech({{response.answer_id}})
 //c $scope.fetch = function(id) {
// call eval route
// data -> globally
 // window,.replace
 // gl
// }
 faculty_app.controller('FetchCtrl', function($scope, $http, $location) {
 console.log("Calllled");
	  $scope.fetchLink =function(id){
		  var url = "http://localhost:8080/api/v1/faculty/evaluationpage/"+ id.target.id ;
		    $http.get(url).then(function (response) {
		    	globalResults = response.data;
		      sessionStorage.setItem("response",JSON.stringify(response.data));
		      window.location.replace('/faculty/eval');
		    }, function error(response) {
		      $scope.postResultMessage = "Error with status: " +  response.statusText;
		    });
		  
		    
		  
	  }
	});
 
 faculty_app.controller('taskCtrl', function($scope, $http, $location) {
//	    
	 var url = "http://localhost:8080/api/v1/taskbar/";
	    $http.get(url).then(function (response) {
	      $scope.response = response.data;
	      console.log(response.data);
	    }, function error(response) {
	      $scope.postResultMessage = "Error with status: " +  response.statusText;
	    });
	  
	});
 
 faculty_app.controller('drpyearCtrl', function($scope, $http) {
//	    var url = $location.absUrl() + "/api/v1/taskbar/1002";
	 var url = "http://localhost:8080/api/v1/faculty/examview/";
	    $http.get(url).then(function (response) {
	      $scope.response = response.data;
	      console.log(response.data);
	    }, function error(response) {
	      $scope.postResultMessage = "Error with status: " +  response.statusText;
	    });
	  
	});

 faculty_app.controller('drpsubCtrl', function($scope, $http) {
//	    
	 var url = "http://localhost:8080/api/v1/faculty/subjectview/";
	    $http.get(url).then(function (response) {
	      $scope.response = response.data;
	      console.log(response.data);
	    }, function error(response) {
	      $scope.postResultMessage = "Error with status: " +  response.statusText;
	    });
	  
	}); 
 
 
 
 
 
 
 faculty_app.controller('evalCtrl', function($scope, $http) {
	 	console.log(globalResults);
	    $scope.response = JSON.parse(sessionStorage.getItem("response"))[0];
	    console.log(JSON.parse(sessionStorage.getItem("response"))[0]);
	    $scope.marksCtrl=function(mark,ano){
	    	console.log($scope.mark)
	    }
	});
 
 
	

 
  
 
 faculty_app.controller('marksCtrl',function ($scope,$http){
	
	  	        var config ={
	  	              transformRequest: angular.identity,
	  	            headers: {'Content-Type': undefined}
	  	        };

	$scope.setMarks = function(){
	  	        	console.log("function called");
	  	        
	  	        	var data={
	  	  				"evaluator_marks":document.getElementById("mrks").value,
	  	  			};
	  	        	 var fd = new FormData();
	  	   		fd.append("marks",document.getElementById("mrks").value);
	  	        	console.log(fd.get("marks"));
	  	        	console.log(data);
	  	        	console.log(document.getElementsByName("answer_text")[0].id);
	  	        	console.log("qwertyuiopasdfghjk");
	  	        	//VERY NICE!
	  	        	var url = "http://localhost:8080/api/v1/submitmarks/" + document.getElementsByName("answer_text")[0].id;
//	  	        	var url = "http://localhost:8080/api/v1/submitmarks/100047";
	  	        	console.log(url)
	  	  		$http.patch(url,fd,config).then(function(response){
	  	  			console.log(response+"HWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
	  	  			if(response.data == 1)
	  	  				alert('Marks Updated Succesfully');
	  	  			else
	  	  				alert("Marks Already Registered");
	  	  			window.location.replace("/faculty/worklist");
	  	  			   
	  	          }, function error(error) {
	  	            console.log(error);
	  	          });
	  	        }
 });
	     
//		$scope.ansid=document.getElementById("anq").value;
//		$scope.mrks=document.getElementById("mrks").value;
//		console.log($scope.ansid);
//		console.log($scope.mrks);
			
		
//		
//			
//	});

// 
//
 
 
 faculty_app.controller('facultyProfile', function ($scope, $http) {
	    $http({
	        method:'GET',
	        url: 'http://localhost:8080/api/v1/faculty/'
	    }).then(function (response) { 
	    	console.log("Inside");
	    	$scope.response = response.data;
	    	},function erro(response){
	    		console.log("Error");});
	});

