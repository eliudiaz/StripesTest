(function ()
{
    'use strict';

    angular
        .module('app.home.update', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.home_update', {
                url    : '/home/update',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/home/update/update.html',
                        controller : 'updateHomeController as vm'
                    }
                }
            });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/home/update');

    
    }
})();