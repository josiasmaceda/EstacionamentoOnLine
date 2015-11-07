'use strict';

function HomeController($scope){
    $scope.mensagem = "teste";
}

function HomeRoute($stateProvider){
    $stateProvider.state('home',{
        url: '/',
        templateUrl : 'views/home.html',
        controller: 'HomeController'
    });
}

angular.module('app')
        .config(HomeRoute)
        .controller('HomeController',HomeController);


