(function ()
{
    'use strict';

    angular
        .module('app.home.update')
        .controller('updateHomeController', updateHomeController);

    /** @ngInject */
  function updateHomeController($scope,$timeout,$mdDialog,$state,workSpace,localStorageService){
        var vm = this;

        // Data
        vm.basicForm = {};
        $scope.usuario = [
            {"id":"01", "name":"admin","creadoPor":"SuperUsuario","UpdatePor":"", "fchCreacion":"20/10.2016"},
            {"id":"02", "name":"test" ,"creadoPor":"SuperUsuario","UpdatePor":"", "fchCreacion":"20/10.2016"},
            {"id":"03", "name":"2134523452345","creadoPor":"SuperUsuario","UpdatePor":"", "fchCreacion":"20/10.2016"},
            {"id":"04", "name":"5645634563546","creadoPor":"SuperUsuario","UpdatePor":"", "fchCreacion":"20/10.2016"},
            {"id":"05", "name":"3456345634563","creadoPor":"SuperUsuario","UpdatePor":"", "fchCreacion":"20/10.2016"},
            {"id":"06", "name":"7657367456745","creadoPor":"SuperUsuario","UpdatePor":"", "fchCreacion":"20/10.2016"},
            {"id":"07", "name":"2362565654645","creadoPor":"SuperUsuario","UpdatePor":"", "fchCreacion":"20/10.2016"},
            {"id":"08", "name":"2362356265445","creadoPor":"SuperUsuario","UpdatePor":"", "fchCreacion":"20/10.2016"},
            {"id":"09", "name":"8458564874848","creadoPor":"SuperUsuario","UpdatePor":"", "fchCreacion":"20/10.2016"},
            {"id":"10", "name":"4568456845845","creadoPor":"SuperUsuario","UpdatePor":"", "fchCreacion":"20/10.2016"},
            {"id":"11", "name":"7776574567467","creadoPor":"SuperUsuario","UpdatePor":"", "fchCreacion":"20/10.2016"},
            {"id":"12", "name":"4567676575667","creadoPor":"SuperUsuario","UpdatePor":"", "fchCreacion":"20/10.2016"}
        ];

        $scope.roles = [
            {id:'01',name:'Role1'},
            {id:'01',name:'Role2'},
            {id:'01',name:'Role3'}
        ]

        $scope.showAlert = function(ev) {
            $mdDialog.show(
              $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .parent(angular.element(document.body))
                .title('Guardado')
                .textContent('Usuario Guardado Con Exito')
                .ariaLabel('Alert Dialog Demo')
                .ok('Aceptar')
                .targetEvent(ev)
            );
          }; 

        if(workSpace.user.id){
            vm.basicForm.cui = workSpace.user.user;
        }

        $scope.save = function(){
            $state.go("app.roles");
            console.info(vm.basicForm.cui);
            $scope.showAlert(); 
        }    

  }
})();

