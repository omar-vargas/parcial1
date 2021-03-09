(function (ng) {
var mod = ng.module("heroesModule", []);
    mod.constant("heroesContext", "api/heroes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/heroes/';
            $urlRouterProvider.otherwise("/heroesList");

            $stateProvider.state('heroesList', {
                url: '/heroes',
                views: {
                    'mainView': {
                        controller: 'heroesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'heroes.list.html'
                    }
                }
            }).state('heroeCreate', {
                url: '/heroes/create',
                views: {
                    'mainView': {
                        controller: 'heroesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'heroes.create.html'
                    }
                }

            }).state('heroeEdit', {
                url: '/heroes/:heroeId',
                param: {
                    heroeId: null
                },
                views: {
                    'mainView': {
                        controller: 'heroesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'heroes.create.html'
                    }
                }
            });
        }]);

})(window.angular);

