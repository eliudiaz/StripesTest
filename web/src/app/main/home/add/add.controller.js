(function ()
{
    'use strict';

    angular
        .module('app.home.add')
        .controller('addHomeRolController', addHomeRolController);

    /** @ngInject */
  function addHomeRolController($scope,$timeout,$mdDialog,$state,workSpace,localStorageService,f,ws,ioStuSalud,ioIdioma,$http){
        var vm = this;
        $scope.manualEnable = true;
        $scope.focus = false;
        $scope.paisCheckV = true;
        $scope.paisCheckV2 = true;
        
        $scope.img='';
        $scope.txtMRZ2_1='';
        $scope.txtMRZ2_2='';
        $scope.chkLeftThumb=false;
        $scope.chkLeftIndex=false;
        $scope.chkLeftMiddle=false;
        $scope.chkLeftRing=false;
        $scope.chkLeftLittle=false;
        $scope.chkRightThumb=false;
        $scope.chkRightIndex=false;
        $scope.chkRightMiddle=false;
        $scope.chkRightRing=false;
        $scope.chkRightLittle=false;
        $scope.paisSet = 'Pais';
        $scope.paisSetShow = true;
        $scope.deptoSet = 'Departamento';
        $scope.deptoSetShow = true;
        $scope.muniSet = 'Munucipio';
        $scope.muniSetShow = true;
        $scope.fchNacSet = 'Pais';
        $scope.fchNacShow = true;
        $scope.deptoVecSet = 'Departamento';
        $scope.deptoVecSetShow = true;
        $scope.muniVecSet = 'Munucipio';
        $scope.muniVecSetShow = true;
        $scope.deptoCedSet = 'Departamento';
        $scope.deptoCedSetShow = true;
        $scope.muniCedSet = 'Munucipio';
        $scope.muniCedSetShow = true;

        // Data
        vm.formWizard = {};

        var update = false;
        var valor; 
        
        try {
            valor = localStorageService.get('workSpace').person;
            vm.formWizard.cui = valor.cui;
            vm.formWizard.primerNombre = valor.primerNombre;
            vm.formWizard.segundoNombre = valor.segundoNombre;
            vm.formWizard.tercerNombre = valor.otrosNombres;
            vm.formWizard.primerApellido = valor.primerApellido;
            vm.formWizard.segundoApellido = valor.segundoApellido;
            vm.formWizard.tercerApellido = valor.otrosApellidos;
            vm.formWizard.nserie = '';
            vm.formWizard.profesion = valor.fkProfesion;
            vm.formWizard.limitacionesFisicas = valor.limitacionesFisicas;
            vm.formWizard.oficialActivo = false;
            vm.formWizard.sabeLeer = valor.sabeLeer;
            vm.formWizard.sabeEscribir = valor.sabeEscribir;
            vm.formWizard.libro = valor.nacNoLibro;
            vm.formWizard.folio = valor.nacNoFolio;
            vm.formWizard.partida = valor.nacNoPartida;
            vm.formWizard.ncedula = valor.noCedula;
            vm.formWizard.edad = valor.edad;
            vm.formWizard.fechaNacimiento = valor.fechaNacimiento;

            $scope.generoSet = valor.sexo;
            $scope.stadoCivilSet = valor.estadoCivil;
            $scope.nacionalidadSet = valor.fkNacionalidad;
            $scope.puebloSet = valor.fkPueblo;
            $scope.linSet = valor.fkComunidadLinguistica;
            $scope.municipioSet = valor.fkMunicipioNacimiento;
            $scope.municipioCedulaSet = valor.fkMunicipioCedula;
            $scope.muni4Set = valor.fkMunicipioVecindad;
            $scope.deptoNac = valor.fkDepartamentoNac;
            $scope.paisSet2 = valor.fkPaisNac;
            $scope.pais2Set = valor.fkPaisCedula;
            $scope.depto4Set2 = valor.fkDepartamentoVecindad;
            $scope.depto3Set = valor.fkDepartamentoCedula;


            $scope.manualEnable = false;

        } catch (e) {

        }

        $scope.runWs2=function(){
         if (!deployJava.isWebStartInstalled("undefined")) {if (deployJava.installLatestJRE()) {if (deployJava.launch("launch.jnlp")) {}}} else {if (deployJava.launch("launch.jnlp")) {}}
        }
       
        //*************************
      //PAGE with 100%
      var myWidth = 0,
          myHeight = 0,
          newH;
      if (typeof(window.innerWidth) == 'number') {
          //No-IE 
          myWidth = window.innerWidth;
          myHeight = window.innerHeight;
      } else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
          //IE 6+ 
          myWidth = document.documentElement.clientWidth;
          myHeight = document.documentElement.clientHeight;
      } else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {
          //IE 4 compatible 
          myWidth = document.body.clientWidth;
          myHeight = document.body.clientHeight;
      }

        $scope.hh = myHeight-310;
 
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

        $scope.manual = function(){
            $scope.manualEnable = false;
            f('cui');
        }

        $scope.counter=0;
        $scope.lector = function(){
           /*     if(localStorageService.get("context")==null){
              localStorageService.set("context","http://69.164.209.242:8080/MS_RRHH_Servicios/");
             }
             if(localStorageService.get("sessionid")==null){
              localStorageService.set("sessionid","123");
             }
             $scope.dpiPath= localStorageService.get("context")+"dpi/pull?session="+ localStorageService.get("sessionid");
             $http({
              url: $scope.dpiPath,
              dataType: "json",
              method: "GET",
              headers: {
               "Content-Type": "application/json"
              }
             })
             .success(function(value){
               $scope.lecturaJson=value;
               $scope.img = $scope.lecturaJson.lblPicture;
               $scope.manualEnable = true;
               vm.formWizard.cui = $scope.lecturaJson.txtCUI;
               vm.formWizard.primerNombre = $scope.lecturaJson.txtNombre1;
               vm.formWizard.segundoNombre = $scope.lecturaJson.txtNombre2;
               vm.formWizard.tercerNombre = $scope.lecturaJson.txtOtrosNombres;
               vm.formWizard.primerApellido = $scope.lecturaJson.txtApellido1;
               vm.formWizard.segundoApellido = $scope.lecturaJson.txtApellido2;
               vm.formWizard.tercerApellido = $scope.lecturaJson.txtApellido3;
               $scope.txtMRZ2_1=$scope.lecturaJson.txtMRZ2_1;
               $scope.txtMRZ2_2=$scope.lecturaJson.txtMRZ2_2;
               $scope.chkLeftThumb=$scope.lecturaJson.chkLeftThumb;
               $scope.chkLeftIndex=$scope.lecturaJson.chkLeftIndex;
               $scope.chkLeftMiddle=$scope.lecturaJson.chkLeftMiddle;
               $scope.chkLeftRing=$scope.lecturaJson.chkLeftRing;
               $scope.chkLeftLittle=$scope.lecturaJson.chkLeftLittle;
               $scope.chkRightThumb=$scope.lecturaJson.chkRightThumb;
               $scope.chkRightIndex=$scope.lecturaJson.chkRightIndex;
               $scope.chkRightMiddle=$scope.lecturaJson.chkRightMiddle;
               $scope.chkRightRing=$scope.lecturaJson.chkRightRing;
               $scope.chkRightLittle=$scope.lecturaJson.chkRightLittle;

               if($scope.lecturaJson.txtGenero=="MASCULINO"){
                $scope.generoSet = {id:'HOMBRE',name:'Masculino'};
               }else{
                $scope.generoSet = {id:'MUJER',name:'Femenino'};
               }
             }).error(function() {
              if ($scope.counter < 10) {
                $timeout($scope.lector(),1000);
                $scope.counter++ 
              }else{
               $scope.counter=0;
              }
               });
             */

            $scope.lecturaJson={
  "lblPicture": "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",
  "txtApellido1": "DIAZ",
  "txtApellido2": "VASQUEZ",
  "txtApellido3": "",
  "txtCUI": "2703759290608",
  "txtFecEmision": "29ABR2013",
  "txtFecVencimiento": "28ABR2023",
  "txtNombre1": "ELIU",
  "txtNombre2": "ABISAI",
  "txtOtrosNombres": "",
  "txtMRZ2_1": "IDGTMDIAZ<<ELIU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<",
  "txtMRZ2_2": "2703759290GTM8305099M23042850608S03090101<<<",
  "txtGenero": "MASCULINO",
  "txtEstadoCivil": "SOLTERO",
  "txtNacionalidad": "GUATEMALA",
  "txtNumSerie": "10371374",
  "txtProfesion": "INGENIERO EN SISTEMAS ",
  "txtLimitaciones": "",
  "chkOficialActivo": false,
  "chkSabeLeer": true,
  "chkSabeEscribir": true,
  "txtVecMunicipio": "SANTA LUCIA MILPAS ALTAS",
  "txtVecDepto": "SACATEPEQUEZ",
  "txtFechaNacimiento": "09MAY1983",
  "txtNacMunicipio": "CHIQUIMULILLA",
  "txtNacDepartamento": "SANTA ROSA",
  "txtNacPais": "GUATEMALA",
  "txtLibro": "36",
  "txtFolio": "400",
  "txtPartida": "786",
  "chkLeftThumb": false,
  "chkLeftIndex": true,
  "chkLeftMiddle": false,
  "chkLeftRing": false,
  "chkLeftLittle": false,
  "chkRightThumb": false,
  "chkRightIndex": true,
  "chkRightMiddle": false,
  "chkRightRing": false,
  "chkRightLittle": false,
  "txtCedulaNumero": "F-6 54914",
  "txtCedulaMunicipio": "CHIQUIMULILLA",
  "txtCedulaDepto": "SANTA ROSA"
};
            $scope.img = $scope.lecturaJson.lblPicture;
            $scope.manualEnable = true;
            vm.formWizard.cui = $scope.lecturaJson.txtCUI;
            vm.formWizard.primerNombre = $scope.lecturaJson.txtNombre1;
            vm.formWizard.segundoNombre = $scope.lecturaJson.txtNombre2;
            vm.formWizard.tercerNombre = $scope.lecturaJson.txtOtrosNombres;
            vm.formWizard.primerApellido = $scope.lecturaJson.txtApellido1;
            vm.formWizard.segundoApellido = $scope.lecturaJson.txtApellido2;
            vm.formWizard.tercerApellido = $scope.lecturaJson.txtApellido3;
            vm.formWizard.nserie = $scope.lecturaJson.txtNumSerie;
            vm.formWizard.profesion = $scope.lecturaJson.txtProfesion;
            vm.formWizard.limitacionesFisicas = $scope.lecturaJson.txtLimitaciones;
            vm.formWizard.oficialActivo = $scope.lecturaJson.chkOficialActivo;
            vm.formWizard.sabeLeer = $scope.lecturaJson.chkSabeLeer;
            vm.formWizard.sabeEscribir = $scope.lecturaJson.chkSabeEscribir;
            vm.formWizard.libro = $scope.lecturaJson.txtLibro;
            vm.formWizard.folio = $scope.lecturaJson.txtFolio;
            vm.formWizard.partida = $scope.lecturaJson.txtPartida;
            vm.formWizard.ncedula = $scope.lecturaJson.txtCedulaNumero;
            vm.formWizard.edad = '33';

            $scope.txtMRZ2_1=$scope.lecturaJson.txtMRZ2_1;
            $scope.txtMRZ2_2=$scope.lecturaJson.txtMRZ2_2;
            $scope.chkLeftThumb=$scope.lecturaJson.chkLeftThumb;
            $scope.chkLeftIndex=$scope.lecturaJson.chkLeftIndex;
            $scope.chkLeftMiddle=$scope.lecturaJson.chkLeftMiddle;
            $scope.chkLeftRing=$scope.lecturaJson.chkLeftRing;
            $scope.chkLeftLittle=$scope.lecturaJson.chkLeftLittle;
            $scope.chkRightThumb=$scope.lecturaJson.chkRightThumb;
            $scope.chkRightIndex=$scope.lecturaJson.chkRightIndex;
            $scope.chkRightMiddle=$scope.lecturaJson.chkRightMiddle;
            $scope.chkRightRing=$scope.lecturaJson.chkRightRing;
            $scope.chkRightLittle=$scope.lecturaJson.chkRightLittle;



            if($scope.lecturaJson.txtGenero=="MASCULINO"){
                $scope.generoSet = {id:'HOMBRE',name:'Masculino'};
            }else{
                $scope.generoSet = {id:'MUJER',name:'Femenino'};
            }

            $scope.stadoCivilSet = $scope.lecturaJson.txtEstadoCivil;
            $scope.depto4Set = $scope.lecturaJson.txtVecDepto;
            $scope.muni4Set = $scope.lecturaJson.txtVecMunicipio;
            
            $scope.paisSet = $scope.lecturaJson.txtNacPais;
            $scope.paisSetShow = false;
            vm.formWizard.pais = $scope.lecturaJson.txtNacPais;

            $scope.deptoSet = $scope.lecturaJson.txtNacDepartamento;
            $scope.deptoSetShow = false;
            vm.formWizard.departamento = $scope.lecturaJson.txtNacDepartamento;

            $scope.muniSet = $scope.lecturaJson.txtNacMunicipio;
            $scope.muniSetShow = false;
            vm.formWizard.municipio = $scope.lecturaJson.txtNacMunicipio;

            $scope.fchNacSet = $scope.lecturaJson.txtFechaNacimiento;
            $scope.fchNacShow = false;
            vm.formWizard.fechaNacimiento = $scope.lecturaJson.txtFechaNacimiento;

            $scope.deptoVecSet = $scope.lecturaJson.txtNacDepartamento;
            $scope.deptoVecSetShow = false;
            vm.formWizard.departamentoVecindad = $scope.lecturaJson.txtNacDepartamento;

            $scope.muniVecSet = $scope.lecturaJson.txtNacMunicipio;
            $scope.muniVecSetShow = false;
            vm.formWizard.municipioVecindad = $scope.lecturaJson.txtNacMunicipio;

            $scope.deptoCedSet = $scope.lecturaJson.txtCedulaDepto;
            $scope.deptoCedSetShow = false;
            vm.formWizard.departamentoCedula = $scope.lecturaJson.txtCedulaDepto;

            $scope.muniCedSet = $scope.lecturaJson.txtCedulaMunicipio;
            $scope.muniCedSetShow = false;
            vm.formWizard.municipioCedula = $scope.lecturaJson.txtCedulaMunicipio;


            console.log(vm.formWizard.pais);


            if($scope.lecturaJson.txtNacionalidad=="GUATEMALA"){
               $scope.nacionalidadSet = "guatemalteco(a)";
            }

        

        }


        


        $scope.fechaNacChange = function(){
           var fc = vm.formWizard.fechaNacimiento;
           console.info(fc);  
        }

        $scope.save = function(){
            $state.go("app.roles");
            console.info(vm.basicForm.cui);
            $scope.showAlert(); 
        }    

        $scope.departamento ={
          "base":[
               "Alta Verapaz",
               "Baja Verapaz",
               "Chimaltenango",
               "Chiquimula",
               "El Progreso",
               "Escuintla",
               "Guatemala",
               "Huehuetenango",
               "Izabal",
               "Jutiapa",
               "Petén",
               "Quetzaltenango",
               "Quiché",
               "Retalhuleu",
               "Sacatepéquez",
               "San Marcos",
               "Santa Rosa",
               "Sololá",
               "Suchitepéquez",
               "Totonicapán",
               "Zacapa"
            ]
        };


    $scope.genero = [{id:'HOMBRE',name:'Masculino'},
        {id:'MUJER',name:'Femenino'}
    ];

    $scope.stadoCivil = [{id:'CASADO',name:'Casado (a)'},
        {id:'DIVORCIADO',name:'Divorciado (a)'},
        {id:'SOLTERO',name:'Soltero (a)'},
        {id:'VIUDO',name:'Viudo (a)'}
    ];

    var entryViews = ws.pais().query({}, function() {
         $scope.pais = entryViews;
         $scope.pais2 = $scope.pais;
    });

    var entryViewscomLing = ws.comLing().query({}, function() {
         $scope.comLing = entryViewscomLing;
    });

    var entryViewsnacionalidad = ws.nacionalidad().query({}, function() {
         $scope.nacionalidad = entryViewsnacionalidad;
    });

    var entryViewsnivelEducativo = ws.nivelEducativo().query({}, function() {
         $scope.nivelEducativo = entryViewsnivelEducativo;
    });

    var entryViewsExpectativas = ws.expectativas().query({}, function() {
         $scope.expectativas = entryViewsExpectativas;
    });

    var entryViewsPuestoFuncional = ws.puestoFuncional().query({}, function() {
         $scope.puestoFuncional = entryViewsPuestoFuncional;
    });

    var entryViewsReglon = ws.reglon().query({}, function() {
         $scope.reglon = entryViewsReglon;
    });

    var entryViewsUnidadEjecutora = ws.unidadEjecutora().query({}, function() {
         $scope.unidadEjecutora = entryViewsUnidadEjecutora;
    });

    var entryViewsClasificacionSer = ws.clasificacionSer().query({}, function() {
         $scope.clasificaServicio = entryViewsClasificacionSer;
    });

    var entryViewsDepto = ws.depto().query({}, function() {
         $scope.depto = entryViewsDepto;
         $scope.depto2 = $scope.depto;
         $scope.depto3 = $scope.depto;
         $scope.depto4 = $scope.depto;
    });

    $scope.paisCheck = function() {
        if(vm.formWizard.pais==74){
            $scope.paisCheckV = false; 
        }else{
            $scope.paisCheckV = true; 
        }
    }

    $scope.paisCheck2 = function() {
        if(vm.formWizard.paisResidencia==74){
            $scope.paisCheckV2 = false; 
        }else{
            $scope.paisCheckV2 = true; 
        }
    }

    $scope.takeDepto = function(id){
        var entryViewsMuni = ws.muni(id).query({}, function() {
             $scope.muni = entryViewsMuni;
        });
    }

    $scope.takeDepto2 = function(id){
        var entryViewsMuni2 = ws.muni(id).query({}, function() {
             $scope.muni2 = entryViewsMuni2;
        });
    }

    $scope.takeDepto3 = function(id){
        var entryViewsMuni3 = ws.muni(id).query({}, function() {
             $scope.muni3 = entryViewsMuni3;
        });
    }

    $scope.takeDepto4 = function(id){
        var entryViewsMuni4 = ws.muni(id).query({}, function() {
             $scope.muni4 = entryViewsMuni4;
        });
    } 

    $scope.takeNivelEducativo = function(id){
        var entryViewsnivelEducativoPadre = ws.nivelEducativoPadre(id).query({}, function() {
             $scope.nivelEducativoPadre = entryViewsnivelEducativoPadre;
        });
    } 

    $scope.takeNivelEducativo2 = function(id){
        var entryViewsnivelEducativoPadre2 = ws.nivelEducativoPadre(id).query({}, function() {
             $scope.nivelEducativoPadre2 = entryViewsnivelEducativoPadre2;
        });
    }   

    $scope.takeReglon = function(id){
        var entryViewsPuestoNominal = ws.puestoNominal(id).query({}, function() {
             $scope.puestoNominal = entryViewsPuestoNominal;
        });
    }

    $scope.takeReglon2 = function(id){
        var entryViewsPuestoNominal2 = ws.puestoNominal(id).query({}, function() {
             $scope.puestoNominal2 = entryViewsPuestoNominal2;
        });
    }

    $scope.takeUnidadEje = function(id){
        var entryViewsDistrito = ws.distrito(id).query({}, function() {
             $scope.distrito = entryViewsDistrito;
        });
    }

    $scope.takeUnidadEje2 = function(id){
        var entryViewsDistrito2 = ws.distrito(id).query({}, function() {
             $scope.distrito2 = entryViewsDistrito2;
        });
    }

    $scope.takeUnidadEje3 = function(id){
        var entryViewsDistrito3 = ws.distrito(id).query({}, function() {
             $scope.distrito3 = entryViewsDistrito3;
        });
    }

    $scope.takeDistrito = function(id){
        var entryViewsLugarEspesifico = ws.lugarEspesifico(id).query({}, function() {
             $scope.lugarEspesifico = entryViewsLugarEspesifico;
        });
    }

    $scope.takeDistrito2 = function(id){
        var entryViewsLugarEspesifico2 = ws.lugarEspesifico(id).query({}, function() {
             $scope.lugarEspesifico2 = entryViewsLugarEspesifico2;
        });
    }

    $scope.takeDistrito3 = function(id){
        var entryViewsLugarEspesifico3 = ws.lugarEspesifico(id).query({}, function() {
             $scope.lugarEspesifico3 = entryViewsLugarEspesifico3;
        });
    }

    $scope.takeLugarE = function(id){
        var entryViewsComunidad = ws.comunidad(id).query({}, function() {
             $scope.comunidad = entryViewsComunidad;
        });
    } 

    $scope.takeClasificaSer = function(id){
        var entryViewsClasificacionSerArea = ws.clasificacionSerArea(id).query({}, function() {
             $scope.clasificacionSerArea = entryViewsClasificacionSerArea;
        });
    } 

    //MULTI
        $scope.shopArr = ioStuSalud.query();
        $scope.bundle = [];
        
    //MULTI
    //MULTI
        $scope.shopArrID = ioIdioma.query();
        $scope.bundleID = [];
        
    //MULTI

    $scope.yearsA = [];
    var yy = new Date().getFullYear();
    for (var i = 1960 ; i <= yy; i++) {
        $scope.yearsA.push({id:i,name:i});
    }

    $scope.pp = function(){
        vm.formWizard.bundle.forEach(function(value, key) {
            if(value.id==vm.formWizard.studioSaludAdd){
              vm.formWizard.bundle[key].anioEstudio = vm.formWizard.studioSaludAddYear;
              vm.formWizard.bundle[key].valor = value.valor+' ('+vm.formWizard.studioSaludAddYear+')';            
              vm.formWizard.bundle[key].fkEstudioSalud = vm.formWizard.bundle[key].id;
            }
        });
    }



    $scope.pueblo = [
        {id:'MESTIZO_O_LADINO',name:'Mestizo o Ladino'},
        {id:'MAYA',name:'Maya'},
        {id:'GARIFUNA',name:'Garifuna'},
        {id:'XINCA',name:'Xinca'},
        {id:'OTRO',name:'Otro'},
        {id:'NO_INDICA',name:'No Inca'}

    ];


$scope.escolaridad = [
    "PRIMARIA",
    "SECUNDARIA",
    "DIVERSIFICADO",
    "PREGRADO",
    "GRADO",
    "TECNICO",
    "POSGRADO",
    "DOCTORADO"
];

$scope.si = [
    "SI",
    "NO"
];


    $scope.showEscolaridad =  false;
    $scope.studiaVal = function(){
        if(vm.formWizard.estudiaActual=="SI"){
            $scope.showEscolaridad =  true;
        }else{
            $scope.showEscolaridad =  false;
        }
    }

    $scope.comisionadoSI =  false;
    $scope.verComisionado = function(){
        if(vm.formWizard.esComisinado=="SI"){
            $scope.comisionadoSI =  true;
        }else{
            $scope.comisionadoSI =  false;
        }
    }

    $scope.otroPuestoSI =  false;
    $scope.verOtroPuesto = function(){
        if(vm.formWizard.otroPuesto=="SI"){
            $scope.otroPuestoSI =  true;
        }else{
            $scope.otroPuestoSI =  false;
        }
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

    $scope.sendForm = function(){
        var studiaAc; 
        var comosionadoAc;
        if(vm.formWizard.estudiaActual=='SI'){
            studiaAc=true;
        }else{
            studiaAc=false;
        }
        if(vm.formWizard.esComisinado=='SI'){
            comosionadoAc=true;
        }else{
            comosionadoAc=false;
        }
        var ido=[];
        vm.formWizard.bundleID.forEach(function(value, key) {
            ido.push({fkIdioma:vm.formWizard.bundleID[key].id});
        });
        var salu=[];
        vm.formWizard.bundle.forEach(function(value, key) {
            salu.push({fkEstudioSalud:vm.formWizard.bundle[key].id,anioEstudio:vm.formWizard.bundle[key].anioEstudio});
        });

        if($scope.txtMRZ2_1){
          var sendJSON = {
              "cui":vm.formWizard.cui,
              "primerNombre": vm.formWizard.primerNombre,
              "segundoNombre": vm.formWizard.segundoNombre,
              "otrosNombres": vm.formWizard.tercerNombre,
              "primerApellido": vm.formWizard.primerApellido,
              "segundoApellido": vm.formWizard.segundoApellido,
              "otrosApellidos": vm.formWizard.tercerApellido,
              "fkNacionalidad":vm.formWizard.nacionalidad,
              "fkProfesion":vm.formWizard.profesion,
              "limitacionesFisicas": vm.formWizard.limitacionesFisicas,
              "sabeLeer":vm.formWizard.sabeLeer,
              "sabeEscribir":vm.formWizard.sabeEscribir,
              "fechaNacimientoTexto":vm.formWizard.fechaNacimiento,
              "fkMunicipioNacimientoNombre":vm.formWizard.municipio,
              "nacNoLibro": vm.formWizard.libro,
              "nacNoFolio": vm.formWizard.folio,
              "nacNoPartida": vm.formWizard.partida,
              "fkPueblo": vm.formWizard.pueblo,
              "fkComunidadLinguistica": vm.formWizard.comunidadLinguistica,
              "mrz": "<<<sdfsdf<<<sdfsdfd<<<",
              "noCedula": vm.formWizard.ncedula,
              "fkMunicipioCedulaNombre":vm.formWizard.municipioCedula,
              "fkMunicipioVecindadNombre":vm.formWizard.municipioVecindad,
              "huellaManoDer":$scope.chkRightThumb,
              "huellaManoIzq":$scope.chkLeftThumb,
              "huellaDedoDer":"indice",
              "huellaDedoIzq":"medio",
              "estadoCivil": vm.formWizard.estadoCivil,
              "sexo": vm.formWizard.sexo,
              "registroLaboral":{
                "anioIngreso":vm.formWizard.anioIngresoInstitucion,
                "comisionado":comosionadoAc,
                "fkExpectativa":vm.formWizard.expectativas,
                "puestos":[{"fkPuestoFuncional":670,"tipo":"PRINCIPAL","fkPuestoNominal":17,"fkComunidad":17,"fkClasificacionServicio":1131}]
              },
              "registroAcademico":{
                "ultimoGrado":vm.formWizard.gradoAprobado,
                "gradoActual":vm.formWizard.gradoAprobado2,
                "estudiaActualmente":studiaAc
              },
              "lugarResidencia":{
                "fkMunicipio":vm.formWizard.municipioResidencia,
                "direccion":vm.formWizard.detalleResidencia
              },
              "idiomas":ido,
              "dpi":{
                "noSerie":112323123,
                "fechaEmision":"12-12-2012",
                "fechaVencimiento":"12-12-2012"
              },
              "estudiosSalud":salu
        };
        }else{
          var sendJSON = {
              "cui":vm.formWizard.cui,
              "primerNombre": vm.formWizard.primerNombre,
              "segundoNombre": vm.formWizard.segundoNombre,
              "otrosNombres": vm.formWizard.tercerNombre,
              "primerApellido": vm.formWizard.primerApellido,
              "segundoApellido": vm.formWizard.segundoApellido,
              "otrosApellidos": vm.formWizard.tercerApellido,
              "fkNacionalidad":vm.formWizard.nacionalidad,
              "fkProfesion":1,
              "limitacionesFisicas": vm.formWizard.limitacionesFisicas,
              "sabeLeer":vm.formWizard.sabeLeer,
              "sabeEscribir":vm.formWizard.sabeEscribir,
              "fechaNacimiento":vm.formWizard.fechaNacimiento,
              "fkMunicipioNacimiento":vm.formWizard.municipio,
              "nacNoLibro": vm.formWizard.libro,
              "nacNoFolio": vm.formWizard.folio,
              "nacNoPartida": vm.formWizard.partida,
              "fkPueblo": vm.formWizard.pueblo,
              "fkComunidadLinguistica": vm.formWizard.comunidadLinguistica,
              "mrz": "<<<sdfsdf<<<sdfsdfd<<<",
              "noCedula": vm.formWizard.ncedula,
              "fkMunicipioCedula":vm.formWizard.municipioCedula,
              "fkMunicipioVecindad":vm.formWizard.municipioVecindad,
              "huellaManoDer":true,
              "huellaManoIzq":true,
              "huellaDedoDer":"indice",
              "huellaDedoIzq":"medio",
              "estadoCivil": vm.formWizard.estadoCivil,
              "sexo": vm.formWizard.sexo,
              "registroLaboral":{
                "anioIngreso":vm.formWizard.anioIngresoInstitucion,
                "comisionado":comosionadoAc,
                "fkExpectativa":vm.formWizard.expectativas,
                "puestos":[{"fkPuestoFuncional":670,"tipo":"PRINCIPAL","fkPuestoNominal":17,"fkComunidad":17,"fkClasificacionServicio":1131}]
              },
              "registroAcademico":{
                "ultimoGrado":vm.formWizard.gradoAprobado,
                "gradoActual":vm.formWizard.gradoAprobado2,
                "estudiaActualmente":studiaAc
              },
              "lugarResidencia":{
                "fkMunicipio":vm.formWizard.municipioResidencia,
                "direccion":vm.formWizard.detalleResidencia
              },
              "idiomas":ido,
              "dpi":{
                "noSerie":112323123,
                "fechaEmision":"12-12-2012",
                "fechaVencimiento":"12-12-2012"
              },
              "estudiosSalud":salu
        };          
        }

        

            if(update){
                $scope.entryUp = ws.UpdateRol(valor);
                EntryUp = $scope.entryUp.update(obj, function() {
                    $scope.showAlert(); 
                    $state.go("app.roles");
                }, function(error) {
                    
                });
            }else{
                $scope.entry = ws.saveHome();
                var entry = $scope.entry.save(sendJSON, function() {
                    $scope.showAlert(); 
                    $state.go("app.home");
                }, function(error) {  } 
                );   
            }   
            console.info(JSON.stringify(sendJSON));
    }

  }
})();

