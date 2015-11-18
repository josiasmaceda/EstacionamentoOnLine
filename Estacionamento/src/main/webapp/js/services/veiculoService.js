'use strict';

angular.module('app').factory('Veiculo',
        function (railsResourceFactory, API_URL) {
            var Veiculo = railsResourceFactory({
                url: API_URL + 'veiculos'
            });
            return Veiculo;
        }
    );


