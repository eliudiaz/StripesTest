(function ()
{
    'use strict';

    angular
        .module('app.home', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.home', {
                url    : '/home',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/home/home.html',
                        controller : 'homeController as vm'
                    }
                }
            });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/home');

        // Navigation
        msNavigationServiceProvider.saveItem('fuse', {
            title : '',
            group : true,
            weight: 1
        });

        msNavigationServiceProvider.saveItem('fuse.home', {
            title    : 'Home',
            icon     : 'icon-tile-four',
            state    : 'app.home',
            translate: 'Home',
            weight   : 1
        });
    }
})();