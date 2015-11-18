'use strict';

angular.module('app').controller('MensalistaController',
        function ($scope,Mensalista, Veiculo) {
            $scope.veiculos = [];
            $scope.tipoVeiculo = [{codigo:1,descricao:"Carro"},{codigo:2,descricao:"Moto"},{codigo:3,descricao:"Onibus"},{codigo:4,descricao:"Caminhão"}]
            
            $scope.$on('limpar-started-mensalista', function(event, args) {
                $scope.limpar();
            });
            
            $scope.limpar = function(){
                $scope.veiculo = {};
            };

            /*$scope.adicionarMensalista = function (mensalista) {
                $scope.mensalistas.push(angular.copy(mensalista));
                console.log(mensalista);
                $scope.mensalista = {};
            };*/
            
            $scope.gravar = function(veiculo){
                if (veiculo.id){
                    veiculo.update(veiculo)
                        .then(function(){
                           $scope.limpar();
                            $scope.listar(); 
                        }, function(error){
                            console.log("error update -> "+error.data);
                        });               
                }else{
                    new Veiculo(veiculo).create()
                        .then(function(){
                           $scope.limpar();
                           $scope.listar(); 
                        }, function(error){
                            console.log("error "+error.data);
                            alert(error.data); 
                        }); 
                }
                
                /*if (veiculo.mensalista.id){
                    veiculo.mensalista.update(veiculo.mensalista)
                        .then(function(){
                            $scope.limpar();
                            $scope.listar();
                        }, function(error){
                            console.log("error update -> "+error.data);
                        });               
                }else{
                    new Mensalista(veiculo.mensalista).create()
                        .then(function(){
                            $scope.limpar();
                            $scope.listar();
                        }, function(error){
                            console.log("error "+error.data);
                            alert(error.data); 
                        }); 
                }*/
            };
            
            $scope.deletar = function(veiculo){
                veiculo.remove()
                        .then(function(){
                             $scope.limpar();
                             $scope.listar();
                        }, function(error){
                            console.log("error listar -> "+error.data);
                        });
            }
            
            $scope.editar = function(veiculo){
                $scope.veiculo = angular.copy(veiculo);
            };
            
            $scope.listar = function(){
                Veiculo.query()
                        .then(function(data){
                            $scope.veiculos = data;
                        }, function(error){
                            console.log("error listar -> "+error.data);
                       });
            }
            
            $scope.limpar();
            $scope.listar();


        }
);


