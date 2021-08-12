angular.module('app').controller('ordersController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/summer';

    $scope.loadOrders = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;
        });
    }

    $scope.showProducts = function (orderId) {
        $http({
            url: contextPath + '/api/v1/orders/' + orderId,
            method: 'GET'
        }).then(function (response) {
            $scope.products = response.data;
            console.log(response);
        });
     }

     $scope.createOrder = function(){
         $http({
             url: basePath + '/api/v1/orders',
             method: 'POST'
         }).then(function(response){
             alert('Заказ создан');
             $scope.loadCart();
             //$scope.loadOrders();
         });
     };


    $scope.loadOrders();
});