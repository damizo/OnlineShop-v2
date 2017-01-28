app.controller('authenticationController', function ($scope) {

    $scope.userLoggedIn = function () {
        return true;
    }

    $scope.showLoginPopup = function () {
        $('.absolute').slideToggle(1000);
    }

});
