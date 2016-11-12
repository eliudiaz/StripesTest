(function ()
{
    'use strict';

    angular
        .module('app.home')
        .controller('homeController', homeController);

    /** @ngInject */
    function homeController($scope,$state,$timeout,$mdDialog,workSpace,localStorageService,ws){
       var vm = this;

       vm.basicForm = {};
       vm.basicForm2 = {};


  $scope.filtroDData = [{id:"=",name:"="},{id:"<>",name:"<>"},{id:"between",name:"between"}];
  $scope.filtroDData2 = [{id:'=',name:'='},{id:'<>',name:'<>'}];

      $scope.showReglon2 = false;
      $scope.showanioIngreso2 = false;

      $scope.getdataAPI = [
        {
        "id": 1,
        "pnombre": "Amilcar",
        "snombre": "Josue",
        "papellido": "Gil",
        "sapellido": "Gonzalez",
        "apellidoCasado": "-",
        "cui": "0000000022",
        "sexo": "Masculino",
        "estado_civil": "Casado",
        "nacionalidad": "Guatemalteco",
        "limitacion_fisica": "Ninguna",
        "leer": "SI",
        "escribir": "SI",
        "fch_nac": "15/05/1986",
        "edad": 30,
        "pais_residencia": "Guatemala",
        "dep_residencia": "Guatemala",
        "muni_residencia": "Guatemala",
        "deta_lugar_residencia": "Capital",
        "pueblo": "Capital",
        "comunidad_linguistica": "Espano",
        "idiomas": "Español,Ingles",
        "ultimo_grado_aprobado": "",
        "grado_aprobado": "Ingenieria Sistemas",
        "estudia_actualmente": "SI",
        "grado_estudia_actual": "Post Grado",
        "otros_estudios_salud": "",
        "anio_ingreso_institucion": "10/10/2016",
        "puesto_acuerdo_funciones": "Jefe Desarrolo",
        "reglon_presupuestario": "024",
        "puesto_nominal": "Jefe Desarrolo",
        "unidad_ejecutora": "A234-1"
      },
      {
        "id": 2,
        "pnombre": "Allan",
        "snombre": "Santiago",
        "papellido": "Gil",
        "sapellido": "Gonzalez",
        "apellidoCasado": "-",
        "cui": "00000323022",
        "sexo": "Masculino",
        "estado_civil": "Soltero",
        "nacionalidad": "Guatemalteco",
        "limitacion_fisica": "Ninguna",
        "leer": "SI",
        "escribir": "SI",
        "fch_nac": "15/05/1986",
        "edad": 28,
        "pais_residencia": "Guatemala",
        "dep_residencia": "Guatemala",
        "muni_residencia": "Guatemala",
        "deta_lugar_residencia": "Capital",
        "pueblo": "Capital",
        "comunidad_linguistica": "Espano",
        "idiomas": "Español,Ingles",
        "ultimo_grado_aprobado": "",
        "grado_aprobado": "Ingenieria Sistemas",
        "estudia_actualmente": "SI",
        "grado_estudia_actual": "Post Grado",
        "otros_estudios_salud": "",
        "anio_ingreso_institucion": "10/10/2016",
        "puesto_acuerdo_funciones": "Jefe Desarrolo",
        "reglon_presupuestario": "024",
        "puesto_nominal": "Jefe Desarrolo",
        "unidad_ejecutora": "A234-1"
      }
      ]

      $scope.users = [
      {
        "id": 1,
        "pnombre": "Amilcar",
        "snombre": "Josue",
        "papellido": "Gil",
        "sapellido": "Gonzalez",
        "apellidoCasado": "-",
        "cui": "0000000022",
        "sexo": "Masculino",
        "estado_civil": "Casado",
        "nacionalidad": "Guatemalteco",
        "limitacion_fisica": "Ninguna",
        "leer": "SI",
        "escribir": "SI",
        "fch_nac": "15/05/1986",
        "edad": 30,
        "pais_residencia": "Guatemala",
        "dep_residencia": "Guatemala",
        "muni_residencia": "Guatemala",
        "deta_lugar_residencia": "Capital",
        "pueblo": "Capital",
        "comunidad_linguistica": "Espano",
        "idiomas": "Español,Ingles",
        "ultimo_grado_aprobado": "",
        "grado_aprobado": "Ingenieria Sistemas",
        "estudia_actualmente": "SI",
        "grado_estudia_actual": "Post Grado",
        "otros_estudios_salud": "",
        "anio_ingreso_institucion": "10/10/2016",
        "puesto_acuerdo_funciones": "Jefe Desarrolo",
        "reglon_presupuestario": "024",
        "puesto_nominal": "Jefe Desarrolo",
        "unidad_ejecutora": "A234-1"
      },
      {
        "id": 2,
        "pnombre": "Allan",
        "snombre": "Santiago",
        "papellido": "Gil",
        "sapellido": "Gonzalez",
        "apellidoCasado": "-",
        "cui": "00000323022",
        "sexo": "Masculino",
        "estado_civil": "Soltero",
        "nacionalidad": "Guatemalteco",
        "limitacion_fisica": "Ninguna",
        "leer": "SI",
        "escribir": "SI",
        "fch_nac": "15/05/1986",
        "edad": 28,
        "pais_residencia": "Guatemala",
        "dep_residencia": "Guatemala",
        "muni_residencia": "Guatemala",
        "deta_lugar_residencia": "Capital",
        "pueblo": "Capital",
        "comunidad_linguistica": "Espano",
        "idiomas": "Español,Ingles",
        "ultimo_grado_aprobado": "",
        "grado_aprobado": "Ingenieria Sistemas",
        "estudia_actualmente": "SI",
        "grado_estudia_actual": "Post Grado",
        "otros_estudios_salud": "",
        "anio_ingreso_institucion": "10/10/2016",
        "puesto_acuerdo_funciones": "Jefe Desarrolo",
        "reglon_presupuestario": "024",
        "puesto_nominal": "Jefe Desarrolo",
        "unidad_ejecutora": "A234-1"
      },{
        "id": 3,
        "pnombre": "Mery",
        "snombre": "Amparo",
        "papellido": "Gil",
        "sapellido": "Gonzalez",
        "apellidoCasado": "-",
        "cui": "0002340022",
        "sexo": "Femenino",
        "estado_civil": "Soltera",
        "nacionalidad": "Guatemalteco",
        "limitacion_fisica": "Ninguna",
        "leer": "SI",
        "escribir": "SI",
        "fch_nac": "15/05/1986",
        "edad": 25,
        "pais_residencia": "Guatemala",
        "dep_residencia": "Guatemala",
        "muni_residencia": "Guatemala",
        "deta_lugar_residencia": "Capital",
        "pueblo": "Capital",
        "comunidad_linguistica": "Espano",
        "idiomas": "Español,Ingles",
        "ultimo_grado_aprobado": "",
        "grado_aprobado": "Ingenieria Sistemas",
        "estudia_actualmente": "SI",
        "grado_estudia_actual": "Post Grado",
        "otros_estudios_salud": "",
        "anio_ingreso_institucion": "10/10/2016",
        "puesto_acuerdo_funciones": "Jefe Desarrolo",
        "reglon_presupuestario": "024",
        "puesto_nominal": "Jefe Desarrolo",
        "unidad_ejecutora": "A234-1"
      },{
       "id": 4,
       "pnombre": "Mynor",
       "snombre": "Raul",
       "papellido": "Mendoza",
       "sapellido": "Duarte",
       "apellidoCasado": "-",
       "cui": "0105040782",
       "sexo": "Masculino",
       "estado_civil": "Casado",
       "nacionalidad": "costarricense",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "20/10/1976",
       "edad": 39,
       "pais_residencia": "Costa Rica",
       "dep_residencia": "San Jose",
       "muni_residencia": "San Jose",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Español,Ingles",
       "ultimo_grado_aprobado": "",
       "grado_aprobado": "Licenciatura en ciencias economicas",
       "estudia_actualmente": "NO",
       "grado_estudia_actual": "",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "10/11/2012",
       "puesto_acuerdo_funciones": "Gerente de finanzas",
       "reglon_presupuestario": "024",
       "puesto_nominal": "Gerente de finanzas",
       "unidad_ejecutora": "A514-2"
     }


,{
       "id": 5,
       "pnombre": "Sara",
       "snombre": "Abigail",
       "papellido": "Mendez",
       "sapellido": "Suarez",
       "apellidoCasado": "Juarez",
       "cui": "0159200483",
       "sexo": "Femenino",
       "estado_civil": "Casada",
       "nacionalidad": "mexicana",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "10/07/1968",
       "edad": 48,
       "pais_residencia": "Mexico",
       "dep_residencia": "Distrito Federal",
       "muni_residencia": "Distrito Federal",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Español",
       "ultimo_grado_aprobado": "",
       "grado_aprobado": "Licenciatura en ciencias economicas",
       "estudia_actualmente": "NO",
       "grado_estudia_actual": "",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "11/11/2013",
       "puesto_acuerdo_funciones": "Periodista",
       "reglon_presupuestario": "022",
       "puesto_nominal": "Periodista",
       "unidad_ejecutora": "A315-2"
     }

,{
       "id": 6,
       "pnombre": "Nathalia",
       "snombre": "Andrea",
       "papellido": "Sanchez",
       "sapellido": "Valle",
       "apellidoCasado": "",
       "cui": "0859700434",
       "sexo": "Femenino",
       "estado_civil": "Soltera",
       "nacionalidad": "mexicana",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "10/07/1989",
       "edad": 27,
       "pais_residencia": "Mexico",
       "dep_residencia": "Morelos",
       "muni_residencia": "Cuernavaca",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Español, Ingles",
       "ultimo_grado_aprobado": "Diversificado",
       "grado_aprobado": "Secretaria Bilingue",
       "estudia_actualmente": "NO",
       "grado_estudia_actual": "",
       "otros_estudios_salud": "Asma",
       "anio_ingreso_institucion": "30/01/2015",
       "puesto_acuerdo_funciones": "Recepcionista",
       "reglon_presupuestario": "022",
       "puesto_nominal": "Recepcionista",
       "unidad_ejecutora": "A485-1"
     }

,{
       "id": 7,
       "pnombre": "William",
       "snombre": "Ernesto",
       "papellido": "Mejia",
       "sapellido": "Paz",
       "apellidoCasado": "",
       "cui": "0822670164",
       "sexo": "Masculino",
       "estado_civil": "Soltero",
       "nacionalidad": "salvadoreño",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "20/03/1959",
       "edad": 57,
       "pais_residencia": "El Salvador",
       "dep_residencia": "Sata Tecla",
       "muni_residencia": "Sata Tecla",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Español",
       "ultimo_grado_aprobado": "Universidad",
       "grado_aprobado": "Ingenieria Industrial",
       "estudia_actualmente": "SI",
       "grado_estudia_actual": "Post grado",
       "otros_estudios_salud": "Gastritis",
       "anio_ingreso_institucion": "10/03/2016",
       "puesto_acuerdo_funciones": "Gerente Industrial",
       "reglon_presupuestario": "029",
       "puesto_nominal": "Gerente Industrial",
       "unidad_ejecutora": "A795-5"
     }

,{
       "id": 8,
       "pnombre": "Gustavo",
       "snombre": "Ernesto",
       "papellido": "Bonilla",
       "sapellido": "Perez",
       "apellidoCasado": "",
       "cui": "0640309375",
       "sexo": "Masculino",
       "estado_civil": "Soltero",
       "nacionalidad": "salvadoreño",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "18/05/1977",
       "edad": 39,
       "pais_residencia": "El Salvador",
       "dep_residencia": "San Salvador",
       "muni_residencia": "San Salvador",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Español, Ingles",
       "ultimo_grado_aprobado": "Universidad",
       "grado_aprobado": "Derecho",
       "estudia_actualmente": "SI",
       "grado_estudia_actual": "Maestria",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "27/02/2010",
       "puesto_acuerdo_funciones": "Abogado y Notario",
       "reglon_presupuestario": "029",
       "puesto_nominal": "Abogado y Notario",
       "unidad_ejecutora": "A665-4"
     }


,{
       "id": 9,
       "pnombre": "Ivanna",
       "snombre": "Alejandra",
       "papellido": "Ruiz",
       "sapellido": "Lopez",
       "apellidoCasado": "",
       "cui": "0239755431",
       "sexo": "Femenino",
       "estado_civil": "Divorciada",
       "nacionalidad": "guatemalteca",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "23/09/1992",
       "edad": 24,
       "pais_residencia": "Mexico",
       "dep_residencia": "Chiapas",
       "muni_residencia": "Chiapas",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Español, Ingles",
       "ultimo_grado_aprobado": "Diversificado",
       "grado_aprobado": "Perito Contador",
       "estudia_actualmente": "NO",
       "grado_estudia_actual": "",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "25/04/2012",
       "puesto_acuerdo_funciones": "Contadora",
       "reglon_presupuestario": "022",
       "puesto_nominal": "Contadora",
       "unidad_ejecutora": "A235-2"
     }

     ,{
       "id": 10,
       "pnombre": "Julia",
       "snombre": "Julieta",
       "papellido": "Jeronimo",
       "sapellido": "Juarez",
       "apellidoCasado": "",
       "cui": "8009755005",
       "sexo": "Femenino",
       "estado_civil": "Divorciada",
       "nacionalidad": "mexicana",
       "limitacion_fisica": "invalida",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "17/07/1997",
       "edad": 19,
       "pais_residencia": "Mexico",
       "dep_residencia": "Chihuahua",
       "muni_residencia": "Chihuahua",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Español, Ingles",
       "ultimo_grado_aprobado": "Diversificado",
       "grado_aprobado": "Bachiller en computacion",
       "estudia_actualmente": "NO",
       "grado_estudia_actual": "",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "31/12/2012",
       "puesto_acuerdo_funciones": "Agente en call center",
       "reglon_presupuestario": "019",
       "puesto_nominal": "Agente en call center",
       "unidad_ejecutora": "A178-1"
     }

     ,{
       "id": 11,
       "pnombre": "Akemi",
       "snombre": "Fujita",
       "papellido": "Azuma",
       "sapellido": "Ito",
       "apellidoCasado": "",
       "cui": "07703083335",
       "sexo": "Masculino",
       "estado_civil": "Soltero",
       "nacionalidad": "japones",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "08/02/1978",
       "edad": 38,
       "pais_residencia": "Japon",
       "dep_residencia": "Okinawa",
       "muni_residencia": "Okinawa",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "japones",
       "idiomas": "japones, Ingles",
       "ultimo_grado_aprobado": "Universidad",
       "grado_aprobado": "Ingenieria en Sistemas",
       "estudia_actualmente": "SI",
       "grado_estudia_actual": "Maestria",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "22/06/2009",
       "puesto_acuerdo_funciones": "Gerente en IT",
       "reglon_presupuestario": "029",
       "puesto_nominal": "Gerente en IT",
       "unidad_ejecutora": "A325-9"
     }

       ,{
       "id": 12,
       "pnombre": "Madoka",
       "snombre": "Sayoko",
       "papellido": "Usui",
       "sapellido": "Takumi",
       "apellidoCasado": "",
       "cui": "17708083591",
       "sexo": "Femenino",
       "estado_civil": "Soltero",
       "nacionalidad": "japones",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "09/08/1976",
       "edad": 40,
       "pais_residencia": "Japon",
       "dep_residencia": "Tokio",
       "muni_residencia": "Tokio",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "japones",
       "idiomas": "japones, Ingles",
       "ultimo_grado_aprobado": "Universidad",
       "grado_aprobado": "Psicologia Clinica",
       "estudia_actualmente": "NO",
       "grado_estudia_actual": "",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "13/04/2015",
       "puesto_acuerdo_funciones": "Psicologa",
       "reglon_presupuestario": "029",
       "puesto_nominal": "Psicologa",
       "unidad_ejecutora": "A211-9"
     }

       ,{
       "id": 13,
       "pnombre": "Jaime",
       "snombre": "Alejandro",
       "papellido": "Alvarez",
       "sapellido": "Lopez",
       "apellidoCasado": "",
       "cui": "57231083882",
       "sexo": "Femenino",
       "estado_civil": "Soltero",
       "nacionalidad": "guatemlateco",
       "limitacion_fisica": "Ciego",
       "leer": "NO",
       "escribir": "NO",
       "fch_nac": "02/02/1998",
       "edad": 18,
       "pais_residencia": "Guatemala",
       "dep_residencia": "Izabal",
       "muni_residencia": "Izabal",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Puerto Barrios",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Espanol",
       "ultimo_grado_aprobado": "",
       "grado_aprobado": "",
       "estudia_actualmente": "NO",
       "grado_estudia_actual": "",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "",
       "puesto_acuerdo_funciones": "",
       "reglon_presupuestario": "",
       "puesto_nominal": "",
       "unidad_ejecutora": ""
     }

       ,{
       "id": 14,
       "pnombre": "Lucas",
       "snombre": "Rafael",
       "papellido": "Obando",
       "sapellido": "Ipalala",
       "apellidoCasado": "-",
       "cui": "3105289180",
       "sexo": "Masculino",
       "estado_civil": "Casado",
       "nacionalidad": "costarricense",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "29/08/1992",
       "edad": 24,
       "pais_residencia": "Costa Rica",
       "dep_residencia": "San Jose",
       "muni_residencia": "San Jose",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Español,Ingles",
       "ultimo_grado_aprobado": "Universidad",
       "grado_aprobado": "Administracion de empresas",
       "estudia_actualmente": "NO",
       "grado_estudia_actual": "",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "14/11/2015",
       "puesto_acuerdo_funciones": "Administrador de empresas",
       "reglon_presupuestario": "029",
       "puesto_nominal": "Administrador de empresas",
       "unidad_ejecutora": "A237-7"
       }

       ,{
       "id": 15,
       "pnombre": "Jorge",
       "snombre": "Aaron",
       "papellido": "Reyes",
       "sapellido": "Rodriguez",
       "apellidoCasado": "-",
       "cui": "2205259137",
       "sexo": "Masculino",
       "estado_civil": "Soltero",
       "nacionalidad": "guatemlateco",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "11/01/1992",
       "edad": 24,
       "pais_residencia": "Guatemala",
       "dep_residencia": "Guatemala",
       "muni_residencia": "Guatemala",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Español,Ingles",
       "ultimo_grado_aprobado": "Universidad",
       "grado_aprobado": "Psicologia Industrial",
       "estudia_actualmente": "NO",
       "grado_estudia_actual": "",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "24/10/2010",
       "puesto_acuerdo_funciones": "Recursos Humanos",
       "reglon_presupuestario": "029",
       "puesto_nominal": "Recursos Humanos",
       "unidad_ejecutora": "A3GS35-1"
       }

       ,{
       "id": 16,
       "pnombre": "Seina",
       "snombre": "Kura",
       "papellido": "Hayashi",
       "sapellido": "Gushiken",
       "apellidoCasado": "",
       "cui": "33761183590",
       "sexo": "Femenino",
       "estado_civil": "Soltero",
       "nacionalidad": "japones",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "18/06/1982",
       "edad": 34,
       "pais_residencia": "Japon",
       "dep_residencia": "Tokio",
       "muni_residencia": "Tokio",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "japones",
       "idiomas": "japones, Ingles, Espanol",
       "ultimo_grado_aprobado": "Universidad",
       "grado_aprobado": "Psicologia",
       "estudia_actualmente": "NO",
       "grado_estudia_actual": "",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "01/03/2014",
       "puesto_acuerdo_funciones": "Psicologa",
       "reglon_presupuestario": "017",
       "puesto_nominal": "Psicologa",
       "unidad_ejecutora": "A831-9"
     }

,{
       "id": 17,
       "pnombre": "Diego",
       "snombre": "Armando",
       "papellido": "Roblez",
       "sapellido": "Morales",
       "apellidoCasado": "-",
       "cui": "1505040731",
       "sexo": "Masculino",
       "estado_civil": "Divorciado",
       "nacionalidad": "Nicaraguense",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "16/10/1965",
       "edad": 51,
       "pais_residencia": "Nicaragua",
       "dep_residencia": "Managua",
       "muni_residencia": "Managua",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Español,Ingles",
       "ultimo_grado_aprobado": "Universidad",
       "grado_aprobado": "Mercadotecnia",
       "estudia_actualmente": "NO",
       "grado_estudia_actual": "",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "14/12/2012",
       "puesto_acuerdo_funciones": "Gerente de Publicidad",
       "reglon_presupuestario": "024",
       "puesto_nominal": "Gerente de Publicidad",
       "unidad_ejecutora": "A512-1"
     }

,{
       "id": 18,
       "pnombre": "Kevin",
       "snombre": "Andres",
       "papellido": "Paniagua",
       "sapellido": "Dieguez",
       "apellidoCasado": "-",
       "cui": "3102040292",
       "sexo": "Masculino",
       "estado_civil": "Divorciado",
       "nacionalidad": "panameno",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "13/05/1996",
       "edad": 20,
       "pais_residencia": "Panama",
       "dep_residencia": "Panama",
       "muni_residencia": "Panama",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Español,Ingles, frances",
       "ultimo_grado_aprobado": "Diversificado",
       "grado_aprobado": "Perito en Mercadotecnia y Publicidad",
       "estudia_actualmente": "SI",
       "grado_estudia_actual": "2do. ano Mercadotecnia",
       "otros_estudios_salud": "sinusitis",
       "anio_ingreso_institucion": "23/07/2011",
       "puesto_acuerdo_funciones": "Auxiliar Administrativo",
       "reglon_presupuestario": "024",
       "puesto_nominal": "Auxiliar Administrativo",
       "unidad_ejecutora": "A617-3"
     }

     ,{
       "id": 19,
       "pnombre": "Sergio",
       "snombre": "Giovanni",
       "papellido": "Godinez",
       "sapellido": "Lopez",
       "apellidoCasado": "-",
       "cui": "2702890205",
       "sexo": "Masculino",
       "estado_civil": "Casado",
       "nacionalidad": "guatemlateco",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "15/03/1989",
       "edad": 27,
       "pais_residencia": "Guatemala",
       "dep_residencia": "Guatemala",
       "muni_residencia": "Guatemala",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Español,Ingles, frances",
       "ultimo_grado_aprobado": "Universidad",
       "grado_aprobado": "Licenciatura en Mercadotecnia",
       "estudia_actualmente": "SI",
       "grado_estudia_actual": "post grado",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "15/07/2009",
       "puesto_acuerdo_funciones": "Mercadologo",
       "reglon_presupuestario": "029",
       "puesto_nominal": "Mercadologo",
       "unidad_ejecutora": "A325-1"
     }

       
       ,{
       "id": 20,
       "pnombre": "Daniela",
       "snombre": "Cristina",
       "papellido": "Godinez",
       "sapellido": "Lopez",
       "apellidoCasado": "Gil",
       "cui": "2702890205",
       "sexo": "Femenino",
       "estado_civil": "Casada",
       "nacionalidad": "guatemalteca",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "15/01/1995",
       "edad": 21,
       "pais_residencia": "Guatemala",
       "dep_residencia": "Guatemala",
       "muni_residencia": "Guatemala",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Español,Ingles",
       "ultimo_grado_aprobado": "Universidad",
       "grado_aprobado": "Ingenieria en Sistemas",
       "estudia_actualmente": "SI",
       "grado_estudia_actual": "post grado",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "31/01/2015",
       "puesto_acuerdo_funciones": "Gerente de IT",
       "reglon_presupuestario": "029",
       "puesto_nominal": "Gerente de IT",
       "unidad_ejecutora": "A234-1"
     }

       ,{
       "id": 21,
       "pnombre": "Daniel",
       "snombre": "Eduardo",
       "papellido": "Rodriguez",
       "sapellido": "Monte Negro",
       "apellidoCasado": "-",
       "cui": "29128902014",
       "sexo": "Masculino",
       "estado_civil": "Soltero",
       "nacionalidad": "guatemlateco",
       "limitacion_fisica": "Ninguna",
       "leer": "SI",
       "escribir": "SI",
       "fch_nac": "19/03/1990",
       "edad": 26,
       "pais_residencia": "Guatemala",
       "dep_residencia": "Guatemala",
       "muni_residencia": "Guatemala",
       "deta_lugar_residencia": "Capital",
       "pueblo": "Capital",
       "comunidad_linguistica": "Espanol",
       "idiomas": "Español,Ingles, frances",
       "ultimo_grado_aprobado": "Universidad",
       "grado_aprobado": "Auditoria",
       "estudia_actualmente": "NO",
       "grado_estudia_actual": "",
       "otros_estudios_salud": "",
       "anio_ingreso_institucion": "02/05/2014",
       "puesto_acuerdo_funciones": "Auditor",
       "reglon_presupuestario": "017",
       "puesto_nominal": "Auditor",
       "unidad_ejecutora": "A523-4"
     }
    ]; 


        //*************************
        // GRID OPTION
        $scope.gridOptions = {
            enableColResize: true,
            rowSelection: 'single',
            rowDeselection: true,
            columnDefs: $scope.columnDefs,
        };  

      $scope.rows = [];
      $scope.columnDefs = [];
      $scope.columnDefs.push(
          {headerName: "ID", checkboxSelection: true, width: 50,
              cellRenderer: function(params) {
                  if (params.data !== undefined) {
                      return params.node.id;
                  } else {
                      return '<img src="../assets/images/logos/LogoMenu.png">'
                  }
              }
          }
      );

      $scope.puestoFuncional = [
        "ADMINISTRADOR",
        "ADMINISTRADOR CENTRO DE ATENCIÓN MÉDICA PERMANENTE (CAP)",
        "ADMINISTRADOR DE HOSPITAL",
        "ADMINISTRADOR DE PROGRAMA",
        "AGENTE DE RESGUARDO Y VIGILANCIA",
        "ASISTENTE QUIMICO FARMACEUTICO",
       " ALBAÑIL",
        "ANALISTA",
       " ANALISTA DE RECURSOS HUMANOS",
        "ANALISTA ADMINISTRATIVO FINANCIERO",
        "ANALISTA CONCEPTUAL",
        "ANALISTA DE  VIATICOS",
        "ANALISTA DE ACCIONES DE PERSONAL",
        "ANALISTA DE ACTAS",
       " ANALISTA DE CAJA FISCAL",
        "ANALISTA DE CAPACITACION"

    ];

      $scope.betweenOK = function(id){
        if(id==1){
          if(vm.basicForm2.filtroReglon=="between"){
            $scope.showReglon2 = true;
          }else{
            $scope.showReglon2 = false;
          }
        }else{
          if(vm.basicForm2.filtroanioIngreso=="between"){
            $scope.showanioIngreso2 = true;
          }else{
            $scope.showanioIngreso2 = false;
          }
        }
          
      }

     

      $scope.columnDefs.push({ headerName: "Primer Nombre", field: "primerNombre", filter: 'text', filterParams: { apply: true } });
      $scope.columnDefs.push({ headerName: "Segundo nombre", field: "segundoNombre", filter: 'text', filterParams: { apply: true } });
      $scope.columnDefs.push({ headerName: "Primer apellido", field: "primerApellido", filter: 'text', filterParams: { apply: true } });
      $scope.columnDefs.push({ headerName: "Segundo apellido", field: "segundoApellido", filter: 'text', filterParams: { apply: true } });
      $scope.columnDefs.push({ headerName: "Apellido de Casado", field: "otrosApellidos", filter: 'text', filterParams: { apply: true } });
      $scope.columnDefs.push({ headerName: "Cui", field: "cui", filter: 'text', filterParams: { apply: true } });
      $scope.columnDefs.push({ headerName: "Sexo", field: "sexo", filter: 'text', filterParams: { apply: true } });
      $scope.columnDefs.push({ headerName: "Estado civil", field: "estadoCivil", filter: 'text', filterParams: { apply: true } });
      $scope.columnDefs.push({ headerName: "Limitaciones fisicas", field: "limitacionesFisicas", filter: 'text', filterParams: { apply: true } });
      $scope.columnDefs.push({ headerName: "Sabe leer", field: "sabeLeer", filter: 'text', filterParams: { apply: true } });
      $scope.columnDefs.push({ headerName: "Sabe escribir", field: "sabeEscribir", filter: 'text', filterParams: { apply: true } });
      $scope.columnDefs.push({ headerName: "Fecha Nacimiento", field: "fechaNacimiento", filter: 'text', filterParams: { apply: true } });
      $scope.columnDefs.push({ headerName: "Edad", field: "edad", filter: 'text', filterParams: { apply: true } });
      
      $timeout(function() {
        $scope.gridOptions.api.setColumnDefs($scope.columnDefs);
        
        var entryViewsAll = ws.allPersonas().query({}, function() {
           $scope.gridOptions.api.setRowData(entryViewsAll);
         });


      }, 300);


      $scope.callApi = function(){
         // HERE CALL API
         $scope.get = {
              primer_nombre: vm.basicForm.primerNombre ? vm.basicForm.primerNombre : '',
              segundo_nombre: vm.basicForm.segundoNombre ? vm.basicForm.segundoNombre : '',
              primer_apellido: vm.basicForm.primerApellido ? vm.basicForm.primerApellido: '' ,
              segundo_apellido: vm.basicForm.segundoApellido ? vm.basicForm.segundoApellido: '' ,
              apellido_casada: vm.basicForm.apellidoCasada ? vm.basicForm.apellidoCasada: '',
              sexo: vm.basicForm.sexo ? vm.basicForm.sexo : '' ,
              fecha_nacimiento_inicio: vm.basicForm.fechaDesde ? vm.basicForm.fechaDesde : '',
              fecha_nacimiento_fin: vm.basicForm.fechaHasta ? vm.basicForm.fechaHasta : '',
              edad: vm.basicForm.edad ? vm.basicForm.edad : '' ,
              departamento: vm.basicForm.departamento ? vm.basicForm.departamento : '',
              municipio: vm.basicForm.municipio ? vm.basicForm.municipio : '',
              direccion: vm.basicForm.detalleResidencia ? vm.basicForm.detalleResidencia : '' ,
              pueblo: vm.basicForm.pueblo ? vm.basicForm.pueblo : '',
              comunidad_linguistica: vm.basicForm.ComunidadLin ? vm.basicForm.ComunidadLin : '',
              idiomas: vm.basicForm.idiomas ? vm.basicForm.idiomas : '' 
          };

          var entryViews = ws.busquedaPersonas($scope.get.primer_nombre,
                                               $scope.get.segundo_nombre,
                                               $scope.get.primer_apellido,
                                               $scope.get.segundo_apellido,
                                               $scope.get.apellido_casada,
                                               $scope.get.sexo,
                                               $scope.get.fecha_nacimiento_inicio,
                                               $scope.get.fecha_nacimiento_fin,
                                               $scope.get.edad,
                                               $scope.get.departamento,
                                               $scope.get.municipio,
                                               $scope.get.direccion,
                                               $scope.get.pueblo,
                                               $scope.get.comunidad_linguistica,
                                               $scope.get.idiomas
                                               ).get({}, function() {
              $scope.gridOptions.api.setRowData(entryViews.content); 
          });


       
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

    $scope.reglonPresupuestario = [
    "011",
    "021",
    "022",
    "029",
    "031",
    "036",
    "081",
    "181",
    "182",
    "183",
    "184",
    "185"
];


	  $scope.deptoMuni = {
    "Alta Verapaz": [
        "Cahabón",
        "Chahal",
        "Chisec",
        "Cobán",
        "Fray Bartolomé de las Casas",
        "Lanquín",
        "Panzós",
        "Raxruha",
        "San Cristóbal Verapaz",
        "San Juan Chamelco",
        "San Pedro Carchá",
        "Santa Cruz Verapaz",
        "Senahú",
        "Tactic",
        "Tamahú",
        "Tucurú",
        "Santa Catarina La Tinta"
    ],
    "Baja Verapaz": [
        "Cubulco",
        "Granados",
        "Purulhá",
        "Rabinal",
        "Salamá",
        "San Jerónimo",
        "San Miguel Chicaj",
        "Santa Cruz El Chol"
    ],
    "Chimaltenango": [
        "Acatenango",
        "Chimaltenango",
        "El Tejar",
        "Parramos",
        "Patzicía",
        "Patzún",
        "Pochuta",
        "San Andrés Itzapa",
        "San José Poaquil",
        "San Juan Comalapa",
        "San Martín Jilotepeque",
        "Santa Apolonia",
        "Santa Cruz Balanyá",
        "Tecpán Guatemala",
        "Yepocapa",
        "Zaragoza"
    ],
    "Chiquimula": [
        "Camotán",
        "Chiquimula",
        "Concepción Las Minas",
        "Esquipulas",
        "Ipala",
        "Jocotán",
        "Olopa",
        "Quezaltepeque",
        "San Jacinto",
        "San José La Arada",
        "San Juan Ermita"
    ],
    "El Progreso": [
        "El Jícaro",
        "Guastatoya",
        "Morazán",
        "San Agustín Acasaguastlán",
        "San Antonio La Paz",
        "San Cristóbal Acasaguastlán",
        "Sanarate"
    ],
    "Escuintla": [
        "Escuintla",
        "Guanagazapa",
        "Iztapa",
        "La Democracia",
        "La Gomera",
        "Masagua",
        "Nueva Concepción",
        "Palín",
        "San José",
        "San Vicente Pacaya",
        "Santa Lucía Cotzumalguapa",
        "Siquinalá",
        "Tiquisate"
    ],
    "Guatemala": [
        "Amatitlán",
        "Chinautla",
        "Chuarrancho",
        "Fraijanes",
        "Guatemala City",
        "Mixco",
        "Palencia",
        "Petapa",
        "San José del Golfo",
        "San José Pinula",
        "San Juan Sacatepéquez",
        "San Pedro Ayampuc",
        "San Pedro Sacatepéquez",
        "San Raymundo",
        "Santa Catarina Pinula",
        "Villa Canales"
    ],
    "Huehuetenango": [
        "Aguacatán",
        "Chiantla",
        "Colotenango",
        "Concepción Huista",
        "Cuilco",
        "Huehuetenango",
        "Ixtahuacán",
        "Jacaltenango",
        "La Democracia",
        "La Libertad",
        "Malacatancito",
        "Nentón",
        "San Antonio Huista",
        "San Gaspar Ixchil",
        "San Juan Atitán",
        "San Juan Ixcoy",
        "San Mateo Ixtatán",
        "San Miguel Acatán",
        "San Pedro Necta",
        "San Rafael La Independencia",
        "San Rafael Petzal",
        "San Sebastián Coatán",
        "San Sebastián Huehuetenango",
        "Santa Ana Huista",
        "Santa Bárbara",
        "Santa Cruz Barillas",
        "Santa Eulalia",
        "Santiago Chimaltenango",
        "Soloma",
        "Tectitán",
        "Todos Santos Cuchumatan"
    ],
    "Izabal": [
        "El Estor",
        "Livingston",
        "Los Amates",
        "Morales",
        "Puerto Barrios"
    ],
    "Jutiapa": [
        "Agua Blanca",
        "Asunción Mita",
        "Atescatempa",
        "Comapa",
        "Conguaco",
        "El Adelanto",
        "El Progreso",
        "Jalpatagua",
        "Jerez",
        "Jutiapa",
        "Moyuta",
        "Pasaco",
        "Quezada",
        "San José Acatempa",
        "Santa Catarina Mita",
        "Yupiltepeque",
        "Zapotitlán"
    ],
    "Petén": [
        "Dolores",
        "Flores",
        "La Libertad",
        "Melchor de Mencos",
        "Poptún",
        "San Andrés",
        "San Benito",
        "San Francisco",
        "San José",
        "San Luis",
        "Santa Ana",
        "Sayaxché",
        "Las Cruces"
    ],
    "Quetzaltenango": [
        "Almolonga",
        "Cabricán",
        "Cajolá",
        "Cantel",
        "Coatepeque",
        "Colomba",
        "Concepción Chiquirichapa",
        "El Palmar",
        "Flores Costa Cuca",
        "Génova",
        "Huitán",
        "La Esperanza",
        "Olintepeque",
        "Ostuncalco",
        "Palestina de Los Altos",
        "Quetzaltenango",
        "Salcajá",
        "San Carlos Sija",
        "San Francisco La Unión",
        "San Martín Sacatepéquez",
        "San Mateo",
        "San Miguel Sigüilá",
        "Sibilia",
        "Zunil"
    ],
    "Quiché": [
        "Canillá",
        "Chajul",
        "Chicamán",
        "Chiché",
        "Chichicastenango",
        "Chinique",
        "Cunén",
        "Ixcán",
        "Joyabaj",
        "Nebaj",
        "Pachalum",
        "Patzité",
        "Sacapulas",
        "San Andrés Sajcabajá",
        "San Antonio Ilotenango",
        "San Bartolomé Jocotenango",
        "San Juan Cotzal",
        "San Pedro Jocopilas",
        "Santa Cruz del Quiché",
        "Uspantán",
        "Zacualpa"
    ],
    "Retalhuleu": [
        "Champerico",
        "El Asintal",
        "Nuevo San Carlos",
        "Retalhuleu",
        "San Andrés Villa Seca",
        "San Felipe",
        "San Martín Zapotitlán",
        "San Sebastián",
        "Santa Cruz Muluá"
    ],
    "Sacatepéquez": [
        "Alotenango",
        "Antigua",
        "Ciudad Vieja",
        "Jocotenango",
        "Magdalena Milpas Altas",
        "Pastores",
        "San Antonio Aguas Calientes",
        "San Bartolomé Milpas Altas",
        "San Lucas Sacatepéquez",
        "San Miguel Dueñas",
        "Santa Catarina Barahona",
        "Santa Lucía Milpas Altas",
        "Santa María de Jesús",
        "Santiago Sacatepéquez",
        "Santo Domingo Xenacoj",
        "Sumpango"
    ],
    "San Marcos": [
        "Ayutla",
        "Catarina",
        "Comitancillo",
        "Concepción Tutuapa",
        "El Quetzal",
        "El Rodeo",
        "El Tumbador",
        "Esquipulas Palo Gordo",
        "Ixchiguan",
        "La Reforma",
        "Malacatán",
        "Nuevo Progreso",
        "Ocos",
        "Pajapita",
        "Río Blanco",
        "San Antonio Sacatepéquez",
        "San Cristóbal Cucho",
        "San José Ojetenam",
        "San Lorenzo",
        "San Marcos",
        "San Miguel Ixtahuacán",
        "San Pablo",
        "San Pedro Sacatepéquez",
        "San Rafael Pie de La Cuesta",
        "San Sibinal",
        "Sipacapa",
        "Tacaná",
        "Tajumulco",
        "Tejutla"
    ],
    "Santa Rosa": [
        "Barberena",
        "Casillas",
        "Chiquimulilla",
        "Cuilapa",
        "Guazacapán",
        "Nueva Santa Rosa",
        "Oratorio",
        "Pueblo Nuevo Viñas",
        "San Juan Tecuaco",
        "San Rafael Las Flores",
        "Santa Cruz Naranjo",
        "Santa María Ixhuatán",
        "Santa Rosa de Lima",
        "Taxisco"
    ],
    "Sololá": [
        "Concepción",
        "Nahualá",
        "Panajachel",
        "San Andrés Semetabaj",
        "San Antonio Palopó",
        "San José Chacaya",
        "San Juan La Laguna",
        "San Lucas Tolimán",
        "San Marcos La Laguna",
        "San Pablo La Laguna",
        "San Pedro La Laguna",
        "Santa Catarina Ixtahuacan",
        "Santa Catarina Palopó",
        "Santa Clara La Laguna",
        "Santa Cruz La Laguna",
        "Santa Lucía Utatlán",
        "Santa María Visitación",
        "Santiago Atitlán",
        "Sololá"
    ],
    "Suchitepéquez": [
        "Chicacao",
        "Cuyotenango",
        "Mazatenango",
        "Patulul",
        "Pueblo Nuevo",
        "Río Bravo",
        "Samayac",
        "San Antonio Suchitepéquez",
        "San Bernardino",
        "San Francisco Zapotitlán",
        "San Gabriel",
        "San José El Idolo",
        "San Juan Bautista",
        "San Lorenzo",
        "San Miguel Panán",
        "San Pablo Jocopilas",
        "Santa Bárbara",
        "Santo Domingo Suchitepequez",
        "Santo Tomas La Unión",
        "Zunilito"
    ],
    "Totonicapán": [
        "Momostenango",
        "San Andrés Xecul",
        "San Bartolo",
        "San Cristóbal Totonicapán",
        "San Francisco El Alto",
        "Santa Lucía La Reforma",
        "Santa María Chiquimula",
        "Totonicapán"
    ],
    "Zacapa": [
        "Cabañas",
        "Estanzuela",
        "Gualán",
        "Huité",
        "La Unión",
        "Río Hondo",
        "San Diego",
        "Teculután",
        "Usumatlán",
        "Zacapa"
    ]
};

$scope.clasificaServicio = [ 
    "ADMINISTRATIVO",
    "ASISTENCIAL",
    "OPERATIVO"
];

$scope.puestoNominal = [
    "JEFE TECNICO I",   
    "JEFE TECNICO II   ",
    "JEFE TECNICO PROFESIONAL I"  ,
    "JEFE TECNICO PROFESIONAL II"  ,
    "JEFE TECNICO PROFESIONAL III " ,
    "TECNICO I "  , 
    "TECNICO II  " , 
    "TECNICO III " ,  
    "TECNICO PROFESIONAL I " , 
    "TECNICO PROFESIONAL II "  ,
    "TECNICO PROFESIONAL III "  ,
    "TRABAJADOR ESPECIALIZADO I"  , 
    "TRABAJADOR ESPECIALIZADO II "  ,
    "TRABAJADOR ESPECIALIZADO III   ",
    "TRABAJADOR ESPECIALIZADO JEFE I " ,
    "TRABAJADOR ESPECIALIZADO JEFE II " ,
    "TRABAJADOR OPERATIVO II   ",
    "TRABAJADOR OPERATIVO III "  

];

$scope.pueblo = [
	"maya",
	"garifuna",
	"xinca",
	"ladino/mestizo"
];

$scope.idiomas = [
	"Achí",
	"Akateka",
	"Awakateka",
	"Ch’orti",
	"Chalchiteka",
	"Chuj",
	"Itzá",
	"Ixil",
	"Jakalteka",
	"Kaq’chikel",
	"Kiché",
	"Mam",
	"Mopán",
	"Poqomam",
	"Poqomchi’",
	"Q’anjob’al",
	"Q’eqchi’",
	"Sakapulteka",
	"Sipakapense",
	"Tektiteka",
	"Tz’utujil",
	"Uspanteka",
	"Garífuna",
	"Xinca",
	"Español",
	"Inglés"
];

	$scope.takeDepto = function(id){
        console.info('ASDF_',id);
		for(var i in $scope.deptoMuni) {
		    if(i==id){
		    	$scope.municipios = $scope.deptoMuni[id];
		    }
		}
	}

  $scope.openNave = function(id){
     id = '#'+id;
     $(id).toggleClass("openNav");
  }


  $scope.add = function(){
    workSpace.person = {};
    localStorageService.set('workSpace', workSpace);
    $state.go("app.home_add");
  }

   $scope.showAlert = function(ev) {
            $mdDialog.show(
              $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .parent(angular.element(document.body))
                .title('Error')
                .textContent('Selecciona un registro a modificar')
                .ariaLabel('Alert Dialog Demo')
                .ok('Aceptar')
                .targetEvent(ev)
            );
          };


  $scope.edit = function(){
    var ob = $scope.gridOptions.api.getSelectedRows();
    var per;
    if(ob.length>0){
      var entryViewsAll = ws.getPersona(ob[0].cui).get({}, function() {
          per = entryViewsAll;
          workSpace.person = per;
          localStorageService.set('workSpace', workSpace);
          $state.go("app.home_add");
        
          console.info(JSON.stringify(ob));
      });
      
    }else{
      $scope.showAlert(); 
    }
    
  }

}
})();
