<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Lista de Posts</title>
</head>

<script src='https://www.google.com/recaptcha/api.js'></script>
<link rel="shortcut icon" type="image/png" href="<c:url value='/resources/img/favicon.png'/>"/>
<link rel="stylesheet" href="<c:url value='/resources/fonts/fonts.css'/>">
<link type="text/css" href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/style.css'/>" rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>">
<script src="<c:url value='/resources/js/controllers/formCtrl.js'/>"></script>
<script src="<c:url value='/resources/js/directives/uiDirectives.js'/>"></script>
<script src="<c:url value='/resources/js/directives/ng-currency.js'/>"></script>
<link href="<c:url value='/resources/css/sticky-footer-navbar.css'/>" rel="stylesheet">

<body>
<header>
    <!-- Navbar fixa -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">

        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item">
                    <a class="nav-link" href="Hello.html">Home</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="listaFuncionarios.priv">Funcionarios <span class="sr-only">(atual)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="listaPais.priv">Pais</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="listaFilhos.priv"> Filho </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="listaPosts.priv"> Posts </a>
                </li>

            </ul>
            <form class="form-inline mt-2 mt-md-0" action="sair.priv" method="post">
                <%--<input class="form-control mr-sm-2" type="text" placeholder="Pesquisa" aria-label="Search">--%>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit" >Logout</button>
            </form>
        </div>
    </nav>
</header>


<div class="container">
    <div class="container"><br><br>
        <h1 align="center">Criar novo Funcionario: </h1>
    </div>

    <form action="criaFuncionario.priv" method="post">

        <div class="form-group">
            <label for="nome" class="col-form-label">Nome:</label>
            <input type="text" class="form-control" name="nome" id="nome" required>
        </div>

        <div class="form-group">
            <label for="login" class="col-form-label">Login:</label>
            <input type="text" class="form-control" name="login" id="login" required>
        </div>



        <div class="form-group">
            <label for="senha" class="col-form-label">Senha:</label>
            <input type="password" class="form-control" name="senhaStr" id="senha" required>
        </div>

        <button type="submit" class="btn btn-primary">Cadastrar</button>
    </form>

    <h2>Lista de Funcionarios: </h2>

    <table class="table table-striped">
        <tr>
            <th>Nome</th>
            <th>Login</th>
            <th>Editar</th>
            <th>Remover</th>
        </tr>
        <c:forEach items="${funcionarios}" var="f">
            <tr>
                <td>${f.nome}</td>
                <td>${f.login}</td>
                <td>
                    <a href="editaFuncionario.priv?&id=${f.id}"> Editar </a>
                </td>
                <td>
                    <a href="removeFuncionario.priv?&id=${f.id}"> Remover </a>
                </td>
            </tr>


            <div class="modal fade" id="modalavaliacao" tabindex="-1" role="dialog" aria-labelledby="myModalavaliacao">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalavaliacao">Realizar Alteraçao</h4>

                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" action="criaFuncionario.priv" method="post">
                                <input type="hidden" name="id1" id="id" value="${f.id}">
                                <div class="form-group">
                                    <label class="control-label" >Nome:</label>
                                    <input class="form-control" name="nome" id="nome" value="${f.nome}"/>

                                    <label class="control-label" >Login:</label>
                                    <input class="form-control" name="login" id="login" value="${f.login}"/>

                                </div>
                                <button type="submit" class="btn btn-success">Alterar</button>

                            </form>
                        </div>
                        <div class="modal-footer">

                        </div>
                    </div>
                </div>
            </div>

        </c:forEach>
    </table>
</div>

<!--modal alterar inicio-->
<script>
    function setaDadosModalAltera(id, nome, login) {
        document.getElementById('id1').value = id;
        document.getElementById('nome1').value = nome;
        document.getElementById('login1').value = login;
    }
</script>

<div class="modal fade" id="modalavaliacao" tabindex="-1" role="dialog" aria-labelledby="myModalavaliacao">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalavaliacao">Realizar Alteraçao</h4>

            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="criaFuncionario.priv" method="post">
                    <input type="hidden" name="id1" id="id1" value="id1">
                    <div class="form-group">
                        <label class="control-label" >Nome:</label>
                        <input class="form-control" name="nome1" id="nome1" value="nome1"/>

                        <label class="control-label" >Login:</label>
                        <input class="form-control" name="login1" id="login1" value="login1"/>

                    </div>
                    <button type="submit" class="btn btn-success">Alterar</button>

                </form>
            </div>
            <div class="modal-footer">

            </div>
        </div>
    </div>
</div>
<!--modal alterar fim-->






<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-85149828-1', 'auto');
    ga('send', 'pageview');
</script>

<script src='https://www.google.com/recaptcha/api.js?render=6LcJLWMUAAAAAE-ElPwTVKp8eadAyKShR5oiqHyz'></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

<script  crossorigin="anonymous" src="<c:url value='https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo'/>"></script>
<script>window.jQuery || document.write('<script src="<c:url value='/resources/js/bootstrap.min.js'/>"><\/script>')</script>
<script src="<c:url value='/resources/js/vendor/popper.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

</body>
</html>
