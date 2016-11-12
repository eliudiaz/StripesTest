(function() {
    'use strict';

    angular
        .module('fuse')
        .factory('ioIdioma', ioIdioma);

    /** @ngInject */
    function ioIdioma($timeout, $window,$resource) {

      return $resource('http://69.164.209.242:8080/MS_RRHH_Servicios/catalogos/get/all?tipo=IDIOMA', {}, {
        query: {method: 'GET', cache: true, isArray: true}
      }); 

}
})();
