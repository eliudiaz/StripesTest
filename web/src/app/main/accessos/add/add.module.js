(function ()
{
    'use strict';

    angular
        .module('app.accessos.add', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.accessos_add', {
                url    : '/accessos/add',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/accessos/add/add.html',
                        controller : 'addController as vm'
                    }
                }
            });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/accessos/add');

    
    }
})();