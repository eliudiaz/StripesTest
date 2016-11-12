(function ()
{
    'use strict';

    /**
     * Main module of the Fuse
     */
    angular
        .module('fuse', [

            // Core
            'app.core',

            // Navigation
            'app.navigation',

            // Toolbar
            'app.toolbar',

            // Quick panel
            'app.quick-panel',

            // Sample
            'app.login',
            'app.home',
            'app.home.add',
            'app.home.update',
            'app.roles',
            'app.roles.add',
            'app.users',
            'app.users.add',
            'app.accessos',
            'app.accessos.add'
        ]);
})();