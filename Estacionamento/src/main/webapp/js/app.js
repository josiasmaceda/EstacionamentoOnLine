'use strict';

angular.module('app', [
    'ui.router',
    'ui.bootstrap',
    'rails' //para usar em vez do $ngresource representacao do rest dentro do angular
]).config(function( $urlRouterProvider, RailsResourceProvider) {
    $urlRouterProvider.when('','/');
    $urlRouterProvider.otherwise('/error?code=404');
    RailsResourceProvider.rootWrapping(false);
}).constant('API_URL','http://localhost:8080/Estacionamento/api/');