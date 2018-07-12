app.controller("formCtrl", function ($scope, $http){
$scope.usuarios= [];
$scope.empresas=[];
$scope.roles=[];
$scope.mensagem = "";//para exibir as mensagens
$scope.laboratorios =[];

$scope.laboratioEditar="";
$scope.empresaEditar="";
$scope.usuarioEditar= "";


    $http.get('listarUsuarios.priv').then(function (retorno) {
        $scope.usuarios= retorno.data;

    });

    $http.get('listarEmpresas.priv').then(function (retorno) {

        $scope.empresas= retorno.data;

    });

    $http.get('listarLabs.priv').then(function (retorno) {
        $scope.laboratorios= retorno.data;
    });


    $http.get('buscaRoles.priv').then(function (roles) {
        $scope.roles= roles.data;
    });

    $scope.openModal = function (objeto) {
        $scope.laboratioEditar= objeto;
        $scope.empresaEditar= objeto;
        $scope.usuarioEditar= objeto;

    };

    $scope.editarUsuario = function (usuario) {
        console.log(usuario);
        $http.post('editarUsuario.priv', usuario).then(function () {
            console.info('Editado com sucesso');
            $http.get('listarUsuarios.priv').then(function (retorno) {
                $scope.usuarios= retorno.data;
            });
        });


    }

    $scope.editarLab = function (laboratorio) {
        console.log(laboratorio);
        $http.post('editarLab.priv', laboratorio).then(function () {
            console.info('Editado com sucesso');
            $http.get('listarLabs.priv').then(function (retorno) {
                $scope.laboratorios= retorno.data;
            });
        });


    }

    $scope.editarEmpresa = function (empresa) {
        $http.post('editarEmpresa.priv', empresa).then(function () {
            console.info('Editado com sucesso');
            $http.get('listarEmpresas.priv').then(function (retorno) {
                $scope.empresas= retorno.data;

            });
        });


    }

    $scope.deleteLab = function (laboratorio) {
        $http.post('deletaLab.priv', laboratorio).then(function () {
            console.log('excluido com sucesso');
            $http.get('listarLabs.priv').then(function (retorno) {
                $scope.laboratorios= retorno.data;
            });

        });
    }

    $scope.deleteEmpresa = function (empresa) {;
        $http.post('deletaEmpresa.priv', empresa).then(function () {
            console.log('excluido com sucesso');
            $http.get('listarEmpresas.priv').then(function (retorno) {
                $scope.empresas= retorno.data;
            });
        });
    }

    $scope.deleteUsuario = function (usuario) {;
        $http.post('deletaUsuario.priv', usuario).then(function () {
            console.log('excluido com sucesso');
            $http.get('listarUsuarios.priv').then(function (retorno) {
                $scope.usuarios= retorno.data;
            });
        });
    }

});

