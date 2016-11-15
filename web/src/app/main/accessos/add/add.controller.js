(function ()
{
    'use strict';

    angular
        .module('app.accessos.add')
        .controller('addController', addController);

    /** @ngInject */
  function addController($scope,$timeout,$mdDialog,$state,workSpace,localStorageService,ws){
        var vm = this;

        // Data
        vm.basicForm = {};
        var update = false;
        var valor; 
        
        try {
            valor = localStorageService.get('workSpace').access.id;
        } catch (e) {

        }



        if(valor){
            vm.basicForm.valor = localStorageService.get('workSpace').access.valor;
            vm.basicForm.tipo = localStorageService.get('workSpace').access.tipo;
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

        $scope.departamento ={
          "base":[{id:1,nombre:'rol 1'}]
        };



        $scope.save = function(){
            var obj = {
              "valor":vm.basicForm.valor,
              "tipo":vm.basicForm.tipo,
              "creadoPor":"admin",
              "fechaCreacion":"01-01-2012"
            };

            if(update){
                $scope.entryUp = ws.UpdateAccess(valor);
                EntryUp = $scope.entryUp.update(obj, function() {
                    $scope.showAlert(); 
                    $state.go("app.accessos");
                }, function(error) {
                    
                });
            }else{
                $scope.entry = ws.saveAccess();
                var entry = $scope.entry.save(obj, function() {
                    $scope.showAlert(); 
                    $state.go("app.accessos");
                }, function(error) { console.error(error); } 
                );    
            }
            

            
        }    

  }
})();

