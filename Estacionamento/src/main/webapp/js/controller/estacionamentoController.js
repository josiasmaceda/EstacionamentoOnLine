'use strict';

var EstacionamentoController = function ($scope, Movimento){
    $scope.movimentos = [];
    $scope.mensalistas = [];
    $scope.mensalista = {};
    
    $scope.limpar = function(){
        $scope.movimento = {};
    };
    
    $scope.$on('limpar-started', function(event, args) {
        $scope.limpar();
    });
 
    $scope.editar = function(movimento){
        $scope.movimento = angular.copy(movimento);
    };
    
    $scope.selecionar = function(movimento){
        $scope.movimento = angular.copy(movimento);
    };
    
    $scope.finalizar = function(movimento){
        movimento.dataHoraSaida = new Date();
        movimento.update(movimento)
            .then(function(){
                $scope.listar();
            }, function(error){
                console.log("error update -> "+error.data);
            });    
    };
    
    $scope.gravar = function(movimento){
        if (movimento.id){
            movimento.update(movimento)
                .then(function(){
                    $scope.listar();
                }, function(error){
                    console.log("error update -> "+error.data);
                });               
        }else{
            new Movimento(movimento).create()
                .then(function(){
                    $scope.limpar();
                    $scope.listar();
                }, function(error){
                    console.log("error "+error.data);
                    alert(error.data); 
                }); 
        }
    };
    
    $scope.listar = function(){
        Movimento.query()
                .then(function(data){
                    $scope.movimentos = data;
                }, function(error){
                    console.log("error listar -> "+error.data);
                });
    };
    
    $scope.obterDadosParaFinalizar = function(movimento){
        movimento.finalizar(movimento)
                .then(function(data){
                    $scope.movimento = data;
                }, function(error){
                    console.log("error listar -> "+error.data);
                });
    };    
    
    
    $scope.deletar = function(movimento){
        movimento.remove()
                .then(function(){
                    $scope.listar();
                }, function(error){
                    console.log("error listar -> "+error.data);
                });
    };
    
    $scope.adicionarMensalista = function(mensalista){
        $scope.mensalistas.push(angular.copy(mensalista));
    };
    
    $scope.limpar();
    $scope.listar();
    
}

var EstacionamentoRoute = function($stateProvider){
    $stateProvider.state('estacionamento',{
        url: '/',
        templateUrl : 'views/estacionamento.html',
        controller: 'EstacionamentoController'
    });
}

angular.module('app')
        .config(EstacionamentoRoute)
        .controller('EstacionamentoController',EstacionamentoController);


