'use strict';

angular.module('app', [
    'ui.router',
    'ui.bootstrap',
    'rails' //para usar em vez do $ngresource representacao do rest dentro do angular
]).config(function ($urlRouterProvider, RailsResourceProvider,railsSerializerProvider) {
    $urlRouterProvider.when('', '/');
    $urlRouterProvider.otherwise('/error?code=404');
    RailsResourceProvider.rootWrapping(false);
    railsSerializerProvider.underscore(angular.identity).camelize(angular.identity); //esta linha desativa a conversao de por ex: DataHora para data_hora
}).constant('API_URL', 'http://localhost:8080/Estacionamento/api/')
        .run(function ($rootScope) {
            $rootScope.startLimpar = function () {
                $rootScope.$broadcast('limpar-started');
            };
        });

