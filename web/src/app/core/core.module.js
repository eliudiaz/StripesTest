(function ()
{
    'use strict';
agGrid.initialiseAgGridWithAngular1(angular);

    angular
        .module('app.core',
            [
                'ngAnimate',
                'ngAria',
                'ngCookies',
                'ngMessages',
                'ngResource',
                'ngSanitize',
                'ngMaterial',
                'angular-chartist',
                'chart.js',
                'datatables',
                'gridshore.c3js.chart',
                'nvd3',
                'pascalprecht.translate',
                'timer',
                'ui.router',
                'uiGmapgoogle-maps',
                'textAngular',
                'ui.sortable',
                'ng-sortable',
                'xeditable',
                'moment-picker',
                'LocalStorageModule',
                'agGrid',
                'oi.select',
                'ui-notification'
            ]);
})();