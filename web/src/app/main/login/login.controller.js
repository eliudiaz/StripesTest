(function ()
{
    'use strict';

    angular
        .module('app.login')
        .controller('LoginV2Controller', LoginV2Controller);

    /** @ngInject */
    function LoginV2Controller($scope,$state,$mdDialog,localStorageService,workSpace){
		$scope.form = {};
		localStorageService.remove('workSpace');
		$scope.showAlert = function(ev) {
		    $mdDialog.show(
		      $mdDialog.alert()
		        .parent(angular.element(document.querySelector('#popupContainer')))
		        .clickOutsideToClose(true)
		        .parent(angular.element(document.body))
		        .title('Error')
		        .textContent('Datos Incorrectos')
		        .ariaLabel('Alert Dialog Demo')
		        .ok('Aceptar')
		        .targetEvent(ev)
		    );
		  }; 

		$scope.send = function(){
			if($scope.form.email=="admin" && $scope.form.password=="admin"){
            	$state.go("app.home");
            	var data = {
            		user:$scope.form.email,
            		name:'admin'
            	}
            	workSpace.name = 'admin';
            	workSpace.cui = $scope.form.email;
            	localStorageService.set('workSpace', workSpace);
			}else{
                 $scope.showAlert();  
			}     
		}

}
})();