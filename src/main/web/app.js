var app = angular.module('app', ['ngRoute'])

app.config(function ($routeProvider) {
    $routeProvider
        .when('/home', {
            templateUrl: 'views/home.html',
            controller: 'homeController'
        })
        .when('/register', {
            templateUrl: 'views/registration.html',
            controller: 'registrationController'
        })
        .when('/contact', {
            templateUrl: 'views/contact.html',
            controller: 'registrationController'
        })
        .when('/catalogue', {
            templateUrl: 'views/catalogue.html',
            controller: 'catalogueController'
        })
        .otherwise({
            redirectTo: '/'
        });
});


