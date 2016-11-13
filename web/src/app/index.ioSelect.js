(function() {
    'use strict';

    angular
        .module('fuse')
        .factory('ioSelect', ioSelect);

    /** @ngInject */
    function ioSelect($timeout, $window,$resource) {

      return $resource('http://69.164.209.242:8080/MS_RRHH_Servicios/accesos/get/all', {}, {
        query: {method: 'GET', cache: true, isArray: true}
      }); 

}
})();
