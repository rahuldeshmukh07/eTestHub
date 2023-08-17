var app = angular.module('myApp', []);
app.controller('moderatorController', function ($scope, $http) {
    $http({
        method:'GET',
        url: 'http://localhost:8080/moderators/all'
    }).then(function (response) { $scope.names = response.data; });
});