var app = angular.module('app', ['ngRoute'])
    .constant('GET_PRODUCT_BY_CRITERIAS', 'http://localhost:8080/products/criteria')
    .constant('GET_OR_CREATE_PRODUCTS', 'http://localhost:8080/products')
    .constant('USER_AUTHORIZATION', 'http://localhost:8080/users/authorize')
    .constant('USER_REGISTRATION', 'http://localhost:8080/users')


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
        .when('/product_management', {
            templateUrl: 'views/new_product.html',
            controller: 'adminController'
        })
        .otherwise({
            redirectTo: '/'
        });
});


