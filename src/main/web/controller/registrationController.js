/**
 * Created by dami on 2016-12-12.
 */
app.controller('registrationController', function ($scope, $http, USER_REGISTRATION) {

    init();


    $scope.createUser = function () {

        $http.post(USER_REGISTRATION, $scope.user).success(function (data) {
            console.log(data);
        }).error(function (data) {
            console.log(data);
        })
    }

    function init() {

        $scope.user = {};

        if (!$('#header').is(':visible')) {
            $('#header').slideToggle(1500);
        }

        if (!$('.form-registration').is(':visible')) {
            $('.form-registration').slideToggle(1500);
        }
    }

});