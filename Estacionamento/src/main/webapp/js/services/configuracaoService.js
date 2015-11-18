'use strict';

angular.module('app').factory('Configuracao',
        function (railsResourceFactory, API_URL) {
            var Configuracao = railsResourceFactory({
                url: API_URL + 'configuracoes'
            });
            return Configuracao;
        }
    );


