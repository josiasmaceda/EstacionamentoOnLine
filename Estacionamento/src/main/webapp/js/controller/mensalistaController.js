'use strict';

angular.module('app').controller('MensalistaController',
        function ($scope/*Mensalista*/) {
            $scope.mensalistas = [];
            $scope.mensalista = {};

            $scope.adicionarMensalista = function (mensalista) {
                $scope.mensalistas.push(angular.copy(mensalista));
            };

        }
);


