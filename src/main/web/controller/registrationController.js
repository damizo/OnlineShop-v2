/**
 * Created by dami on 2016-12-12.
 */
app.controller('registrationController', function ($scope) {

    init();


    $scope.createUser = function () {
        console.log($scope.user);
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