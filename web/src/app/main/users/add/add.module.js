(function ()
{
    'use strict';

    angular
        .module('app.users.add', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.users_add', {
                url    : '/users/add',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/users/add/add.html',
                        controller : 'addUserController as vm'
                    }
                }
            });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/users/add');

    
    }
})();