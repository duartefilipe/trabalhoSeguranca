<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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
                    <a class="nav-link"href="listaPosts.priv"> Posts </a>
                </li>

            </ul>
            <form class="form-inline mt-2 mt-md-0" action="sair.priv" method="post">
                <%--<input class="form-control mr-sm-2" type="text" placeholder="Pesquisa" aria-label="Search">--%>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit" >Logout</button>
            </form>
        </div>
    </nav>
</header>

<main role="main" class="container">
    <h1 class="mt-5" align="center">Avisos da Escola</h1>
    <p class="lead" align="center">Aqui voce encontrara os avisos da Escola papo de Anjo.</p>



            <c:forEach items="${posts}" var="p">
                <div class="thumbnail" style=" background-color: #e0e0e0">

                    <h5 align="center">${p.titulo}</h5>
                    <hr>
                    <h6 align="center">${p.texto}</h6>

                </div>
            </c:forEach>




</main>

<footer class="footer">
    <div class="container">
        <span class="text-muted">Filipe Duarte | @2018.</span>
    </div>
</footer>

    <%--<h1>Menu</h1>--%>

    <%--<ul>--%>
        <%--<li><a href="listaFuncionarios.priv"> Funcionarios </a></li>--%>
        <%--<li><a href="listaPais.priv"> Pais </a></li>--%>
        <%--<li><a href="#"> Filho </a></li>--%>
        <%--<li><a href="listaPosts.priv"> Posts </a></li>--%>
    <%--</ul>--%>

<!-- Modal -->

<script>
    function setaDadosModalAltera(idpost, titulo, texto) {
        document.getElementById('idpost').value = idpost;
        document.getElementById('titulo').value = titulo;
        document.getElementById('texto').value = texto;
    }
</script>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Alterar</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="login.html" method="post">
                    <input type="hidden" name="idpost" id="idpost" value="idpost">
                    <div class="form-group">
                        <label class="col-form-label">Titulo:</label>
                        <input type="text" class="form-control" name="titulo" id="titulo" value="titulo">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">Texto:</label>
                        <input type="text" class="form-control" name="texto" id="texto" value="texto">
                    </div>


                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Alterar</button>

                    </div>

                </form>
            </div>

        </div>
    </div>
</div>


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
