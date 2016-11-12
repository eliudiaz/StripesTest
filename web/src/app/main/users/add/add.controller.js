(function ()
{
    'use strict';

    angular
        .module('app.users.add')
        .controller('addUserController', addUserController);

    /** @ngInject */
  function addUserController($scope,$timeout,$mdDialog,$state,workSpace,localStorageService,ws){
        var vm = this;
        // Data
        vm.basicForm = {};
        var update = false;
        var valor; 
        
        try {
            valor = localStorageService.get('workSpace').user.usuario;
        } catch (e) {

        }

        if(valor){
            vm.basicForm.nombre = localStorageService.get('workSpace').user.nombres;
            vm.basicForm.apellido = localStorageService.get('workSpace').user.apellidos;
            vm.basicForm.cui = localStorageService.get('workSpace').user.tipo;
            vm.basicForm.usuario = localStorageService.get('workSpace').user.usuario;
            vm.basicForm.correo = localStorageService.get('workSpace').user.correo;
            //vm.basicForm.rol = localStorageService.get('workSpace').user.tipo;
            update = true;
        }


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

        var entryViews = ws.allRol().query({}, function() {
             $scope.departamento = entryViews;
        });
        



        $scope.save = function(){
            var obj = {
              "usuario":vm.basicForm.usuario,
              "correo":vm.basicForm.correo,
              "estado":"ACTIVO",
              "creadoPor":"admin",
              "nombres":vm.basicForm.nombre,
              "apellidos":vm.basicForm.apellido,
              "clave":vm.basicForm.password,
              "confirmacionClave":vm.basicForm.password2,
              "cui":vm.basicForm.cui,
              "roleId":vm.formWizard.rol
            };
            if(update){
                $scope.entryUp = ws.UpdateUser(valor);
                var EntryUp = $scope.entryUp.update(obj, function() {
                    $scope.showAlert(); 
                    $state.go("app.users");
                }, function(error) {
                    
                });
            }else{
                $scope.entry = ws.saveUser();
                var entry = $scope.entry.save(obj, function() {
                    $scope.showAlert(); 
                    $state.go("app.users");
                }, function(error) {  } 
                );
            }
            
        }    

  }
})();

