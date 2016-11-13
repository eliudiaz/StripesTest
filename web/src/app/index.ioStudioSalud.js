(function() {
    'use strict';

    angular
        .module('fuse')
        .factory('ioStuSalud', ioStuSalud);

    /** @ngInject */
    function ioStuSalud($timeout, $window,$resource) {

      return $resource('http://69.164.209.242:8080/MS_RRHH_Servicios/catalogos/get/all?tipo=ESTUDIO_SALUD', {}, {
        query: {method: 'GET', cache: true, isArray: true}
      }); 

}
})();
