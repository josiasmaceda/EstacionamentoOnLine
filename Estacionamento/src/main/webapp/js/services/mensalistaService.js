'use strict';

angular.module('app').factory('Mensalista',
        function (railsResourceFactory, API_URL) {
            var Mensalista = railsResourceFactory({
                url: API_URL + 'mensalistas'
            });
            return Mensalista;
        }
    );


