(function ()
{
    'use strict';

    angular
        .module('app.accessos', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.accessos', {
                url    : '/accessos',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/accessos/accessos.html',
                        controller : 'accessosController as vm'
                    }
                }
            });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/accessos');

       
        msNavigationServiceProvider.saveItem('fuse.accessos', {
            title    : 'Accesos',
            icon     : 'icon-tile-four',
            state    : 'app.accessos',
            /*stateParams: {
                'param1': 'page'
             },*/
            translate: 'Accesos',
            weight   : 1
        });
    }
})();