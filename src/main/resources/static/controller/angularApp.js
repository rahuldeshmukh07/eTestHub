var app = angular.module('myApp', []);
app.controller('moderatorController', function ($scope, $http) {
    $http({
        method:'GET',
        url: 'http://localhost:8080/api/v1/admin/moderator'
    }).then(function (response) { $scope.names = response.data; });
});

app.controller('facultyController', function ($scope, $http) {
    $http({
        method:'GET',
        url: 'http://localhost:8080/api/v1/admin/faculty'
    }).then(function (response) { $scope.names = response.data; });
});

app.controller('studentController', function ($scope, $http) {
    $http({
        method:'GET',
        url: 'http://localhost:8080/api/v1/admin/student'
    }).then(function (response) { $scope.names = response.data; });
});
//
// app.controller('resultController', function ($scope, $http) {
// var id= $scope.
// $http({
// method:'GET',
// url: 'http://localhost:8080/api/v1/result/{id}'
// }).then(function (response) { $scope.names = response.data; });
// });

app.controller('myCon', function ($scope, $http) {
	
	
   $scope.getData = function(){
	   var id= $scope.rollNo;
	   console.log(id);
	   $http({
	        method:'GET',
	        url: `http://localhost:8080/api/v1/result/`+id
	    }).then(function (response) { $scope.names = response.data; });
	
   }
	
});
// app.service('fileUpload', ['$http:', function($https:)])

app.controller('myCoan', ['$scope', 'fileUpload',function($scope, fileUpload){
	$scope.import = function(){
	 var file= $scope.filecsv;
	 console.log('file is');
	 console.log(file);
// var uploadUrl="/fileUpload";
	}
}]);






app.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          
          element.bind('change', function() {
             scope.$apply(function() {
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
 }]);
app.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl) {
       var fd = new FormData();
       fd.append('file', file);
    
       $http.post(uploadUrl, fd, {
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined}
       })
       .success(function() {
       })
       .error(function() {
       });
    }
 }]);
app.controller('myCtrl', ['$scope', 'fileUpload', function($scope, fileUpload) {
    $scope.doUploadFile = function() {
       
       var file = $scope.myFile;
       console.log('file is ' );
       console.dir(file);
       var uploadUrl = "http://localhost:8080/api/v1/studentUpload";
       fileUpload.uploadFileToUrl(file, uploadUrl);
    };
 }]);






app.controller('faculty_upload', function($scope, $http) {
	$scope.uploadFile = function(files) {
		console.log("CAALLLED");
		var fd = new FormData();
		fd.append("faculty", files[0]);
		var url = "http://localhost:8080/api/v1/uploadFaculty";
		var config = { headers: {'Content-Type': undefined},
					   transformRequest: angular.identity
					 }
		$http.post(url, fd, config).then(response=>{console.log(response);
		alert("File Selected");
		},error=>{console.log(error);
		alert("Please Select A valid File");
		location.reload();
		})	
	};
 });

app.controller('student_upload', function($scope, $http) {
	$scope.uploadFile = function(files) {
		console.log("CAALLLED");
		var fd = new FormData();
		fd.append("student", files[0]);
		var url = "http://localhost:8080/api/v1/uploadStudent";
		var config = { headers: {'Content-Type': undefined},
					   transformRequest: angular.identity
					 }
		$http.post(url, fd, config).then(response=>{console.log(response);
		alert("File Selected");
		},error=>{console.log(error);
		alert("Please Select A valid File");
		location.reload();
		})	
	};
 });

app.controller('moderator_upload', function($scope, $http) {
	$scope.uploadFile = function(files) {
		console.log("CAALLLED");
		var fd = new FormData();
		fd.append("moderator", files[0]);
		var url = "http://localhost:8080/api/v1/uploadModerator";
		var config = { headers: {'Content-Type': undefined},
					   transformRequest: angular.identity
					 }
		$http.post(url, fd, config).then(response=>{console.log(response);
		alert("File Selected");
		},error=>{console.log(error);
		alert("Please Select A valid File");
		location.reload();
		})	
	};
 });


app.controller('ProfileController', function ($scope, $http) {
    $http({
        method:'GET',
        url: 'http://localhost:8080/api/v1/admin/'
    }).then(function (response) { 
    	console.log("Inside");
    	$scope.response = response.data;
    	},function erro(response){
    		console.log("Error");});
});

