var app = angular.module('app', ['ngRoute'])
    .constant('GET_PRODUCT_BY_CRITERIAS', 'http://localhost:8080/product')

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


