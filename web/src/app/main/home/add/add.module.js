(function ()
{
    'use strict';

    angular
        .module('app.home.add', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.home_add', {
                url    : '/home/add',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/home/add/add.html',
                        controller : 'addHomeRolController as vm'
                    }
                }
            });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/home/add');

    
    }
})();