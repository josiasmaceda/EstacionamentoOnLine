'use strict';

angular.module('app').controller('ConfiguracaoController',
        function ($scope, Configuracao) {
            $scope.configuracao = {};

            $scope.salvarConfiguracao = function (configuracao) {
                if (configuracao.id) {
                    configuracao.update(configuracao)
                            .then(function () {
                                $scope.retornarConfiguracao();
                            }, function (error) {
                                console.log("error update -> " + error.data);
                            });
                } else {
                    new Configuracao(configuracao).create()
                            .then(function () {
                                $scope.retornarConfiguracao();
                            }, function (error) {
                                console.log("error " + error.data);
                                alert(error.data);
                            });
                }
            };

            $scope.retornarConfiguracao = function () {
                Configuracao.query()
                        .then(function (data) {
                            $scope.configuracao = data[0];
                        }, function (error) {
                            console.log("error listar -> " + error.data);
                        });
            };

            $scope.retornarConfiguracao();
        }
);


