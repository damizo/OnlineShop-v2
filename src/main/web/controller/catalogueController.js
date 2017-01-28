app.controller('catalogueController', function ($scope) {


    $scope.products = [
        {
            imageSource: "resources/powerbank_adata.jpg",
            title: "Powerbank A-Data",
            price: 100,
            currency: "PLN"
        },
        {
            imageSource: "resources/tv_sony.png",
            title: "TV 3D Sony 4",
            price: 8050,
            currency: "PLN"
        },
        {
            imageSource: "resources/toshiba_hdd.jpg",
            title: "Toshiba HDD",
            price: 250,
            currency: "PLN"
        },
        {
            imageSource: "resources/iron_braun.jpg",
            title: "Iron Braun",
            price: 450,
            currency: "PLN"
        },
        {
            imageSource: "resources/laptop_dell.jpeg",
            title: "Laptop Dell",
            price: 4250,
            currency: "PLN"
        },
        {
            imageSource: "resources/ps4.jpg",
            title: "Playstation 4",
            price: 1450,
            currency: "PLN"
        },
        {
            imageSource: "resources/transcend_ssd.jpeg",
            title: "Transcend SSD 128GB",
            price: 255,
            currency: "PLN"
        },
    ];

});