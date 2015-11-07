'use strict';

angular.module('app')
        .factory('Movimento',
            function(railsResourceFactory,API_URL){
                var Contato = railsResourceFactory({
                   url:API_URL + 'movimentos' 
                });
                return Contato;
        }).factory('Configuracao',
            function(railsResourceFactory,API_URL){
                var Contato = railsResourceFactory({
                   url:API_URL + 'configuracoes' 
                });
                return Contato;
        });


