(function ()
{
    'use strict';

    angular
        .module('fuse')
        .factory('api', apiService);

    /** @ngInject */
    function apiService($resource)
    {
        
        var api = {};

        // Base Url
        api.baseUrl = 'app/data/';

        api.sample = $resource(api.baseUrl + 'sample/sample.json');

        api.quickPanel = {
            activities: $resource(api.baseUrl + 'quick-panel/activities.json'),
            contacts  : $resource(api.baseUrl + 'quick-panel/contacts.json'),
            events    : $resource(api.baseUrl + 'quick-panel/events.json'),
            notes     : $resource(api.baseUrl + 'quick-panel/notes.json')
        };

        return api;
    }

})();