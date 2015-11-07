'use strict';

angular.module('app')
        .factory('Movimento',
            function(railsResourceFactory,API_URL){
                var Movimento = railsResourceFactory({
                   url:API_URL + 'movimentos' 
                });
                
                Movimento.prototype.finalizar = function(movimento) {
                    return Movimento.get("finalizar/"+movimento.id);
                }
                return Movimento;
        }).factory('Configuracao',
            function(railsResourceFactory,API_URL){
                var Configuracao = railsResourceFactory({
                   url:API_URL + 'configuracoes' 
                });
                return Configuracao;
        });


