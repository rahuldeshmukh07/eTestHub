var onlineExam = angular.module('onlineExamApp', ['ngRoute']);
onlineExam.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {

    // HTML5 mode enabled
    $locationProvider.html5Mode(true);

    // Route Provider
    $routeProvider



    /*------------Home Page--------------*/
    /*-----------------------------------*/
        .when('/admin/', {
        templateUrl: '/adminindex.html'
    })



    /*------------Students---------------*/
    /*-----------------------------------*/
    .when('/admin/students', {
            templateUrl: '/pages/student-view.html'
        })
        .when('/admin/students/import', {
            templateUrl: '/pages/student-import.html'
        })




    .when('/admin/faculty', {
            templateUrl: '/pages/faculty.html'
        })
        .when('/admin/moderator', {
            templateUrl: '/pages/moderator.html'
        })





    /*------------403 Error--------------*/
    /*-----------------------------------*/
    .when('/admin/forbidden', {
        templateUrl: '/pages/403.html'
    })



    /*------------404 Error--------------*/
    /*-----------------------------------*/
    .when('/admin/notFound', {
            templateUrl: '/pages/404.html'
        })
        .otherwise({
            redirectTo: '/admin/notFound'
        });
}]);


// Date and Time Controller
onlineExam.controller('dateTimeController', function($scope, $interval) {
    var tick = function() {
        $scope.Clock = Date.now();
    }
    tick();
    $interval(tick, 1000);
});

onlineExam.controller('loader', function($scope, $http) {
    // Show loading spinner.
    $scope.loading = true;
    $http.get('/:url')
        .success(function(data) {
            // Do stuff with data.
        })
        .catch(function(err) {
            // Log error somehow.
        })
        .finally(function() {
            // Hide loading spinner whether our call succeeded or failed.
            $scope.loading = false;
        });
});