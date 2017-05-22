app.controller('authenticationController', function ($scope, validationService, $location, USER_AUTHORIZATION, $http) {

    $scope.vm = {
        email: '',
        password: ''
    };
    $scope.error_message = '';

    $scope.userLoggedIn = function () {
        return false;
    }

    $scope.isSuperAdmin = function () {
        return false;
    }

    $scope.showLoginPopup = function () {
        $('.absolute').slideToggle(1000);
    }

    function saveCredentialsInCookies(vm) {

    }

    $scope.executeLogIn = function () {
        var result = validationService.validateUser($scope.vm);
        if(result.statusType === 'ERROR'){
            $scope.error_message = result.message;
        } else {
            $http.post(USER_AUTHORIZATION, $scope.vm).success(function (response) {
                $location.path('/catalogue');
                hidePopupAndlClearFields();
                saveCredentialsInCookies($scope.vm);
            }).error(function (data) {
                $scope.error_message = data.message;
            })
        }
    }

    function hidePopupAndlClearFields() {
        $('.absolute').slideToggle(1000);
        $scope.vm = {};
        $scope.error_message = '';
    }

});
