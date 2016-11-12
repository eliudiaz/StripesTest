(function ()
{
    'use strict';

    angular
        .module('app.roles', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.roles', {
                url    : '/roles',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/roles/roles.html',
                        controller : 'rolesController as vm'
                    }
                }
            });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/roles');

       
        msNavigationServiceProvider.saveItem('fuse.sample', {
            title    : 'Roles',
            icon     : 'icon-tile-four',
            state    : 'app.roles',
            /*stateParams: {
                'param1': 'page'
             },*/
            translate: 'Roles',
            weight   : 1
        });
    }
})();