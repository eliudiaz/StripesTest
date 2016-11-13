(function ()
{
    'use strict';

    angular
        .module('app.roles.add', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.roles_add', {
                url    : '/roles/add',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/roles/add/add.html',
                        controller : 'addRolController as vm'
                    }
                }
            });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/roles/add');

    
    }
})();