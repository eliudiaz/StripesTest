(function ()
{
    'use strict';

    angular
        .module('app.roles.add')
        .controller('addRolController', addRolController);

    /** @ngInject */
  function addRolController($scope,$timeout,$mdDialog,$state,workSpace,localStorageService,ioSelect,ws,Notification){
        var vm = this;

        // Data
        vm.basicForm = {};
        var update = false;
        var valor; 
        
        try {
            valor = localStorageService.get('workSpace').rol.id;
        } catch (e) {

        }


        $scope.customTemplateScope = function() {
            Notification.primary({message: "Just message", templateUrl: "custom_template.html", scope: $scope});
        };

        $scope.showAlert = function(ev) {
            $mdDialog.show(
              $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .parent(angular.element(document.body))
                .title('Guardado')
                .textContent('Rol Guardado Con Exito')
                .ariaLabel('Alert Dialog Demo')
                .ok('Aceptar')
                .targetEvent(ev)
            );
          }; 

        if(workSpace.user.id){
            vm.basicForm.cui = workSpace.user.user;
        }

        //MULTI
         $scope.shopArr = ioSelect.query();
         $scope.bundle = [];
        //MULTI

         if(valor){
            vm.basicForm.nombre = localStorageService.get('workSpace').rol.nombre;
            $scope.bundle = localStorageService.get('workSpace').rol.accesos;
            update = true;
        }
        $scope.save = function(){
            var obj = {
              "nombre":vm.basicForm.nombre,
              "accesos":$scope.bundle
            };

            if(update){
                $scope.entryUp = ws.UpdateRol(valor);
                EntryUp = $scope.entryUp.update(obj, function() {
                    $scope.showAlert(); 
                    $state.go("app.roles");
                }, function(error) {
                    
                });
            }else{
                $scope.entry = ws.saveRol();
                var entry = $scope.entry.save(obj, function() {
                    $scope.showAlert(); 
                    $state.go("app.roles");
                }, function(error) { tr.toster(errorManager.getDescError(error), 'ik-error'); } 
                );   
            }

                       
        }    

  }
})();

