(function ()
{
    'use strict';

    angular
        .module('app.users', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.users', {
                url    : '/users',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/users/users.html',
                        controller : 'usersController as vm'
                    }
                }
            });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/users');

       
        msNavigationServiceProvider.saveItem('fuse.usuarios', {
            title    : 'Usuarios',
            icon     : 'icon-tile-four',
            state    : 'app.users',
            /*stateParams: {
                'param1': 'page'
             },*/
            translate: 'Usuarios',
            weight   : 1
        });
    }
})();