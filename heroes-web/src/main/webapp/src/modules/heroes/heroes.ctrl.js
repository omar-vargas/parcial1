(function (ng) {

    var mod = ng.module("heroesModule");

    mod.controller("heroesCtrl", ['$scope', '$state', '$stateParams', '$http', 'heroesContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de heroes está vacio
            $scope.records = {};
            // carga las heroes
            $http.get(context).then(function (response) {
                $scope.records = response.data;
            });

            // el controlador recibió un heroeId ??
            // revisa los parámetros (ver el :heroeId en la definición de la ruta)
            if ($stateParams.heroeId !== null && $stateParams.heroeId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.heroeId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                        });

                // el controlador no recibió un heroeId
            } else {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*especie Long. El valor se asigna en el backend*/,
                    name: '' /*especie String*/,
                };

                $scope.alerts = [];
            }


            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('heroesList');
                            });

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('heroesList');
                            });
                }
                ;
            };

            this.deleteRecord = function (id) {
                $http.delete(context + "/" + id);
                $state.reload('heroesList');

            };

// Código continua con las funciones de despliegue de errores


        }]);
})(window.angular);

